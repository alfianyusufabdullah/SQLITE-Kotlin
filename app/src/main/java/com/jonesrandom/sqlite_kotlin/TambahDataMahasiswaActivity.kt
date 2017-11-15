package com.jonesrandom.sqlite_kotlin

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
import kotlinx.android.synthetic.main.activity_tambah_mahasiswa.*

class TambahDataMahasiswaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_mahasiswa)

        val dbHelper = DatabaseHelper(this)

        toolbar.title = "SQLite Kotlin"

        etNama.addTextChangedListener(Watcher(inNama))
        etNim.addTextChangedListener(Watcher(inNim))

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, getSemester())
        spinnerSemester.adapter = adapter

        btnInsert.setOnClickListener {

            val nama = etNama.text.toString()
            val nim = etNim.text.toString()
            val semester = spinnerSemester.selectedItem.toString()
            val id = spinnerSemester.selectedItemId

            if (nama.isEmpty()) {
                inNama.error = "Masukan nama Mahasiswa"
                return@setOnClickListener
            }

            if (nim.isEmpty()) {
                inNim.error = "Masukan nim Mahasiswa"
                return@setOnClickListener
            }

            if (id < 1) {
                Toast.makeText(this, "Pilih semster", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val data = ModelMahasiswa()
            data.nama = nama
            data.nim = nim.toInt()
            data.semster = semester

            val stat = dbHelper.insertData(data)

            if (stat > 0) {
                spinnerSemester.setSelection(0)
                etNama.text.clear()
                etNim.text.clear()

                etNim.clearFocus()
                etNama.clearFocus()

                Toast.makeText(this, "Berhasil Menambah Data", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Gagal Menambah Data", Toast.LENGTH_SHORT).show()
            }

        }
        btnLihatData.setOnClickListener {
            startActivity(Intent(this, DaftarMahasiswaActivity::class.java))
        }

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
