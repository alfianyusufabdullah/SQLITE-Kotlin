package com.jonesrandom.sqlite_kotlin.adapter

import com.jonesrandom.sqlite_kotlin.model.ModelMahasiswa

/**
 * Created by jonesrandom on 11/14/17.
 *
 * #JanganLupaBahagia
 *
 */
interface OnItemClickListener {
    fun onClick(data : ModelMahasiswa , position : Int)
}