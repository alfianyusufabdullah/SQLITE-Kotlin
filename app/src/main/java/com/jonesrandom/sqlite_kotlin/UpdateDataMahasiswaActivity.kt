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
import com.jonesrandom.sqlite_kotlin.database.DatabaseHelper
import com.jonesrandom.sqlite_kotlin.model.ModelMahasiswa
import kotlinx.android.synthetic.main.activity_update_mahasiswa.*

class UpdateDataMahasiswaActivity : AppCompatActivity() {

    var dataMahasiswa = ModelMahasiswa()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_mahasiswa)

        val dbHelper = DatabaseHelper(this)
        bindView()

        etNamaEdit.addTextChangedListener(Watcher(inNamaEdit))
        etNimEdit.addTextChangedListener(Watcher(inNimEdit))

        btnEdit.setOnClickListener {

            val nama = etNamaEdit.text.toString()
            val nim = etNimEdit.text.toString()
            val semester = spinnerSemesterEdit.selectedItem.toString()
            val id = spinnerSemesterEdit.selectedItemId

            if (nama.isEmpty()) {
                inNamaEdit.error = "Masukan nama Mahasiswa"
                return@setOnClickListener
            }

            if (nim.isEmpty()) {
                inNimEdit.error = "Masukan nim Mahasiswa"
                return@setOnClickListener
            }

            if (id < 1) {
                Toast.makeText(this, "Pilih semster", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            dataMahasiswa.nama = nama
            dataMahasiswa.nim = nim.toInt()
            dataMahasiswa.semster = semester

            val stat = dbHelper.updateData(dataMahasiswa)

            if (stat > 0) {
                val bind = Bundle()
                bind.putParcelable("DATA", dataMahasiswa)

                val intent = Intent()
                intent.putExtras(bind)

                setResult(Activity.RESULT_OK, intent)
            }
        }

        toolbarEdit.title = "Update Data Mahasiswa"
    }

    private fun bindView() {
        val bind = intent.extras
        dataMahasiswa = bind.getParcelable("DATA")

        etNamaEdit.setText(dataMahasiswa.nama)
        etNimEdit.setText(dataMahasiswa.nim.toString())

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, getSemester())
        spinnerSemesterEdit.adapter = adapter
        spinnerSemesterEdit.setSelection(adapter.getPosition(dataMahasiswa.semster))

    }

    private fun getSemester(): List<String> = listOf("SEMESTER", "SEMESTER 1", "SEMESTER 2", "SEMESTER 3", "SEMESTER 4", "SEMESTER 5", "SEMESTER 6", "SEMESTER 7")

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
