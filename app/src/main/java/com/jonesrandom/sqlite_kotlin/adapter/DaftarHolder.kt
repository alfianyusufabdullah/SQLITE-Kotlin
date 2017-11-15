package com.jonesrandom.sqlite_kotlin.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.jonesrandom.sqlite_kotlin.model.ModelMahasiswa
import kotlinx.android.synthetic.main.row_daftar.view.*

/**
 * Created by jonesrandom on 11/14/17.
 *
 * #JanganLupaBahagia
 *
 */
class DaftarHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun bind(data: ModelMahasiswa, listener: OnItemClickListener , position : Int) = with(itemView) {

        rowAv.text = data.nama.substring(0, 1).capitalize()
        rowNama.text = data.nama
        rowNim.text = data.nim.toString()

        setOnClickListener { listener.onClick(data , position) }

    }
}