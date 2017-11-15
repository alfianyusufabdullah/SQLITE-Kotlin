package com.jonesrandom.sqlite_kotlin.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.jonesrandom.sqlite_kotlin.model.ModelMahasiswa

/**
 * Created by jonesrandom on 11/14/17.
 *
 * #JanganLupaBahagia
 *
 */
class DatabaseHelper(ctx: Context) : SQLiteOpenHelper(ctx, DATABASE_NAME, null, DATABASE_VERSION) {

    private val context: Context = ctx
    private val database : SQLiteDatabase = writableDatabase

    companion object {
        private val DATABASE_NAME = "DB_NAME"
        private val DATABASE_VERSION = 1

        val DATABASE_TABEL = "DB_TABEL"
        val ROW_ID = "_id"
        val ROW_NAMA = "nama"
        val ROW_NIM = "nim"
        val ROW_SEMESTER = "semster"

        private val QUERY_CREATE = "CREATE TABLE IF NOT EXISTS $DATABASE_TABEL ($ROW_ID INTEGER PRIMARY KEY AUTOINCREMENT, $ROW_NAMA TEXT , $ROW_NIM TEXT , $ROW_SEMESTER TEXT)"
        private val QUERY_UPGRADE = "DROP TABLE IF EXISTS $DATABASE_TABEL"

    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(QUERY_CREATE)
        Toast.makeText(context , "DATABASE CREATED" , Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL(QUERY_UPGRADE)
        Toast.makeText(context , "DATABASE UPGRADED" , Toast.LENGTH_SHORT).show()
    }

    fun insertData(modelMahasiswa: ModelMahasiswa): Long {

        val values = ContentValues()
        values.put(DatabaseHelper.ROW_NAMA, modelMahasiswa.nama)
        values.put(DatabaseHelper.ROW_NIM, modelMahasiswa.nim)
        values.put(DatabaseHelper.ROW_SEMESTER, modelMahasiswa.semster)

        return database.insert(DatabaseHelper.DATABASE_TABEL, null, values)

    }

    fun updateData(modelMahasiswa: ModelMahasiswa): Int {
        val values = ContentValues()
        values.put(DatabaseHelper.ROW_NAMA, modelMahasiswa.nama)
        values.put(DatabaseHelper.ROW_NIM, modelMahasiswa.nim)
        values.put(DatabaseHelper.ROW_SEMESTER, modelMahasiswa.semster)

        return database.update(DatabaseHelper.DATABASE_TABEL, values, "${DatabaseHelper.ROW_ID} = ${modelMahasiswa.id}", null)
    }

    fun getAllData(): MutableList<ModelMahasiswa> {

        val data: MutableList<ModelMahasiswa> = ArrayList()
        val cursor = database.rawQuery("SELECT * FROM ${DatabaseHelper.DATABASE_TABEL}", null)
        cursor.use { cur ->

            if (cursor.moveToFirst()) {

                do {

                    val mahasiswa = ModelMahasiswa()
                    mahasiswa.id = cur.getInt(cur.getColumnIndex(DatabaseHelper.ROW_ID))
                    mahasiswa.nama = cur.getString(cur.getColumnIndex(DatabaseHelper.ROW_NAMA))
                    mahasiswa.nim = cur.getInt(cur.getColumnIndex(DatabaseHelper.ROW_NIM))
                    mahasiswa.semster = cur.getString(cur.getColumnIndex(DatabaseHelper.ROW_SEMESTER))

                    data.add(mahasiswa)

                } while (cursor.moveToNext())
            }
        }
        return data
    }

    fun deleteData(id: Int): Int {
        val stat = database.delete(DatabaseHelper.DATABASE_TABEL, "${DatabaseHelper.ROW_ID} = $id", null)
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