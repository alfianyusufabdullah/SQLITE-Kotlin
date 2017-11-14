package com.jonesrandom.sqlite_kotlin

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Toast
import com.jonesrandom.sqlite_kotlin.database.DatabaseAdapter
import com.jonesrandom.sqlite_kotlin.model.ModelMahasiswa
import kotlinx.android.synthetic.main.activity_edit.*

class EditMahasiswa : AppCompatActivity() {

    var data = ModelMahasiswa()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val dbAdapter = DatabaseAdapter(this)
        bindView()

        etNamaEdit.addTextChangedListener(Watcher(inNamaEdit))
        etNimEdit.addTextChangedListener(Watcher(inNimEdit))

        btnEdit.setOnClickListener {

            val nama = etNamaEdit.text.toString()
            val nim = etNimEdit.text.toString()
            val semester = spinnerSemesterEdit.selectedItem.toString()
            val id = spinnerSemesterEdit.selectedItemId

            if (nama.isEmpty()) {
                inNamaEdit.error = "Masukan Nama Mahasiswa"
                return@setOnClickListener
            }

            if (nim.isEmpty()) {
                inNimEdit.error = "Masukan Nim Mahasiswa"
                return@setOnClickListener
            }

            if (id < 1) {
                Toast.makeText(this, "Pilih Semester", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            data.Nama = nama
            data.Nim = nim.toInt()
            data.Semester = semester

            val stat = dbAdapter.updateData(data)

            if (stat > 0) {
                val bind = Bundle()
                bind.putParcelable("DATA", data)

                val intent = Intent()
                intent.putExtras(bind)

                setResult(Activity.RESULT_OK, intent)
            }
        }

        toolbarEdit.title = "Update Data Mahasiswa"
    }

    fun bindView() {
        val bind = intent.extras
        data = bind.getParcelable("DATA")

        etNamaEdit.setText(data.Nama)
        etNimEdit.setText(data.Nim.toString())

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, getSemester())
        spinnerSemesterEdit.adapter = adapter
        spinnerSemesterEdit.setSelection(adapter.getPosition(data.Semester))

    }

    private fun getSemester(): List<String> {
        return listOf("SEMESTER", "SEMESTER 1", "SEMESTER 2", "SEMESTER 3", "SEMESTER 4", "SEMESTER 5", "SEMESTER 6", "SEMESTER 7")
    }

    private class Watcher(textinput: TextInputLayout) : TextWatcher {

        val input = textinput

        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            input.isErrorEnabled = false
        }
    }
}
