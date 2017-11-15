package com.jonesrandom.sqlite_kotlin.model


import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by jonesrandom on 11/14/17.
 *
 * #JanganLupaBahagia
 *
 */

@SuppressLint("ParcelCreator")
@Parcelize
data class ModelMahasiswa(var id: Int, var nama: String, var nim: Int, var semster: String) : Parcelable {
    constructor() : this(0, "", 0, "")

}