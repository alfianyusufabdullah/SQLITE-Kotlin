package com.jonesrandom.sqlite_kotlin

import android.app.Application
import com.jonesrandom.sqlite_kotlin.database.DatabaseHelper

/**
 * Created by jonesrandom on 11/25/17.
 *
 * #JanganLupaBahagia
 *
 */

class MyApplication: Application(){

    override fun onCreate() {
        super.onCreate()
        DatabaseHelper.initDatabaseInstance(this)
    }

}
