package com.jonesrandom.sqlite_kotlin.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by jonesrandom on 11/14/17.
 *
 * #JanganLupaBahagia
 *
 */
class ModelMahasiswa(var id: Int, var nama: String, var nim: Int, var semster: String) : Parcelable {


    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString())

    constructor() : this(0, "", 0, "")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nama)
        parcel.writeInt(nim)
        parcel.writeString(semster)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModelMahasiswa> {
        override fun createFromParcel(parcel: Parcel): ModelMahasiswa {
            return ModelMahasiswa(parcel)
        }

        override fun newArray(size: Int): Array<ModelMahasiswa?> {
            return arrayOfNulls(size)
        }
    }

}