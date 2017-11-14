package com.jonesrandom.sqlite_kotlin.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

/**
 * Created by jonesrandom on 11/14/17.
 *
 * #JanganLupaBahagia
 *
 */
class DatabaseHandler(ctx: Context) : SQLiteOpenHelper(ctx, DATABASE_NAME, null, DATABASE_VERSION) {

    private val context: Context = ctx

    companion object {
        private val DATABASE_NAME = "DB_NAME"
        private val DATABASE_VERSION = 1

        val DATABASE_TABEL = "DB_TABEL"
        val ROW_ID = "_id"
        val ROW_NAMA = "Nama"
        val ROW_NIM = "Nim"
        val ROW_SEMESTER = "Semester"

        private val QUERY_CREATE = "CREATE TABLE IF NOT EXISTS $DATABASE_TABEL ($ROW_ID INTEGER PRIMARY KEY AUTOINCREMENT, $ROW_NAMA TEXT , $ROW_NIM TEXT , $ROW_SEMESTER TEXT)"
        private val QUERY_UPGRADE = "DROP TABLE IF EXISTS $DATABASE_TABEL"

    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0!!.execSQL(QUERY_CREATE)
        Toast.makeText(context , "DATABASE CREATED" , Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL(QUERY_UPGRADE)
        Toast.makeText(context , "DATABASE UPGRADED" , Toast.LENGTH_SHORT).show()
    }


}