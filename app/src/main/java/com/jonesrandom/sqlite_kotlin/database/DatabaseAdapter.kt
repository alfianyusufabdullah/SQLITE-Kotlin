package com.jonesrandom.sqlite_kotlin.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast
import com.jonesrandom.sqlite_kotlin.model.ModelMahasiswa

/**
 * Created by jonesrandom on 11/14/17.
 *
 * #JanganLupaBahagia
 *
 */
class DatabaseAdapter(ctx: Context) {

    private val context: Context = ctx

    private var handler: DatabaseHandler = DatabaseHandler(ctx)
    private val database: SQLiteDatabase = handler.readableDatabase

    fun insertData(modelMahasiswa: ModelMahasiswa): Long {

        val values = ContentValues()
        values.put(DatabaseHandler.ROW_NAMA, modelMahasiswa.Nama)
        values.put(DatabaseHandler.ROW_NIM, modelMahasiswa.Nim)
        values.put(DatabaseHandler.ROW_SEMESTER, modelMahasiswa.Semester)

        val stat = database.insert(DatabaseHandler.DATABASE_TABEL, null, values)
        val mess: String?
        mess = if (stat > 0) {
            "Berhasil Menambahkan Data"
        } else {
            "Gagal Menambahkan Data"
        }

        Toast.makeText(context, mess, Toast.LENGTH_SHORT).show()

        return stat

    }

    fun updateData(modelMahasiswa: ModelMahasiswa): Int {
        val values = ContentValues()
        values.put(DatabaseHandler.ROW_NAMA, modelMahasiswa.Nama)
        values.put(DatabaseHandler.ROW_NIM, modelMahasiswa.Nim)
        values.put(DatabaseHandler.ROW_SEMESTER, modelMahasiswa.Semester)

        val stat = database.update(DatabaseHandler.DATABASE_TABEL, values, "${DatabaseHandler.ROW_ID} = ${modelMahasiswa.id}", null)
        val mess: String?
        mess = if (stat > 0) {
            "Berhasil Mengupdate Data"
        } else {
            "Gagal Mengupdate Data"
        }

        Toast.makeText(context, mess, Toast.LENGTH_SHORT).show()

        return stat
    }

    fun getAllData(): MutableList<ModelMahasiswa> {

        val data: MutableList<ModelMahasiswa> = ArrayList()
        val cursor = database.rawQuery("SELECT * FROM ${DatabaseHandler.DATABASE_TABEL}", null)
        cursor.use { cur ->

            if (cursor.moveToFirst()) {

                do {

                    val mahasiswa = ModelMahasiswa()
                    mahasiswa.id = cur.getInt(cur.getColumnIndex(DatabaseHandler.ROW_ID))
                    mahasiswa.Nama = cur.getString(cur.getColumnIndex(DatabaseHandler.ROW_NAMA))
                    mahasiswa.Nim = cur.getInt(cur.getColumnIndex(DatabaseHandler.ROW_NIM))
                    mahasiswa.Semester = cur.getString(cur.getColumnIndex(DatabaseHandler.ROW_SEMESTER))

                    data.add(mahasiswa)

                } while (cursor.moveToNext())
            }
        }
        return data
    }

    fun deleteData(id: Int): Int {
        val stat = database.delete(DatabaseHandler.DATABASE_TABEL, "${DatabaseHandler.ROW_ID} = $id", null)
        val mess: String?
        mess = if (stat > 0) {
            "Berhasil Menghapus dataDaftarMahasiswa"
        } else {
            "Gagal Menghapus Data"
        }

        Toast.makeText(context, mess, Toast.LENGTH_SHORT).show()

        return stat
    }

}