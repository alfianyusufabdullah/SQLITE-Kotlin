package com.jonesrandom.sqlite_kotlin.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jonesrandom.sqlite_kotlin.R
import com.jonesrandom.sqlite_kotlin.model.ModelMahasiswa

/**
 * Created by jonesrandom on 11/14/17.
 *
 * #JanganLupaBahagia
 *
 */
class DaftarAdapter(data: MutableList<ModelMahasiswa>, listener: OnItemClickListener) : RecyclerView.Adapter<DaftarHolder>() {

    private val datas = data
    private val listeners = listener

    override fun onBindViewHolder(holder: DaftarHolder?, position: Int) {
        holder?.bind(datas[position], listeners, position)
    }

    override fun getItemCount(): Int = datas.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DaftarHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.row_daftar, parent, false)
        return DaftarHolder(view)
    }
}