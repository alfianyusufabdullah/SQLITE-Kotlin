package com.jonesrandom.sqlite_kotlin.database

/**
 * Created by jonesrandom on 11/25/17.
 *
 * #JanganLupaBahagia
 *
 */
class DatabaseConstan {

    companion object {
        val DATABASE_NAME = "DB_NAME"
        val DATABASE_VERSION = 1

        val DATABASE_TABEL = "DB_TABEL"
        val ROW_ID = "_id"
        val ROW_NAMA = "nama"
        val ROW_NIM = "nim"
        val ROW_SEMESTER = "semster"

        val QUERY_CREATE = "CREATE TABLE IF NOT EXISTS $DATABASE_TABEL ($ROW_ID INTEGER PRIMARY KEY AUTOINCREMENT, $ROW_NAMA TEXT , $ROW_NIM TEXT , $ROW_SEMESTER TEXT)"
        val QUERY_UPGRADE = "DROP TABLE IF EXISTS $DATABASE_TABEL"
    }
}