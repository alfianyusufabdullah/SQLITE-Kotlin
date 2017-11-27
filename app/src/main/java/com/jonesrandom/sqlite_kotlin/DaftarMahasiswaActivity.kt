package com.jonesrandom.sqlite_kotlin

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jonesrandom.sqlite_kotlin.adapter.DaftarAdapter
import com.jonesrandom.sqlite_kotlin.adapter.OnItemClickListener
import com.jonesrandom.sqlite_kotlin.database.DatabaseHelper
import com.jonesrandom.sqlite_kotlin.model.ModelMahasiswa
import kotlinx.android.synthetic.main.activity_daftar_mahasiswa.*

class DaftarMahasiswaActivity : AppCompatActivity(), OnItemClickListener, DetailMahasiswaDialog.OnDialogItemClick {

    private var dataDaftarMahasiswa: MutableList<ModelMahasiswa> = ArrayList()
    private var positionStats = 0
    lateinit private var adapterDaftarMahasiswa: DaftarAdapter

    override fun dialogDeleteCallback(data: ModelMahasiswa) {
        this.dataDaftarMahasiswa.remove(data)
        adapterDaftarMahasiswa.notifyDataSetChanged()

        if (this.dataDaftarMahasiswa.size > 0) {
            textEmpty.visibility = View.GONE
        } else {
            textEmpty.visibility = View.VISIBLE
        }

    }

    override fun dialogEditCallback(data: ModelMahasiswa) {

        val bind = Bundle()
        bind.putParcelable("DATA", data)

        val edit = Intent(this, UpdateDataMahasiswaActivity::class.java)
        edit.putExtras(bind)
        startActivityForResult(edit, 1)
    }

    override fun onClick(data: ModelMahasiswa, position: Int) {
        DetailMahasiswaDialog.newInstance(data, this).show(supportFragmentManager, "DETAIL")
        positionStats = position
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_mahasiswa)

        dataDaftarMahasiswa = DatabaseHelper.getAllData()

        adapterDaftarMahasiswa = DaftarAdapter(dataDaftarMahasiswa, this)

        list.setHasFixedSize(true)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapterDaftarMahasiswa

        toolbars.title = "Daftar Mahasiswa"

        if (dataDaftarMahasiswa.size > 0) {
            textEmpty.visibility = View.GONE
        } else {
            textEmpty.visibility = View.VISIBLE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {

            if (data != null) {
                val dataMahasiswa: ModelMahasiswa = data.extras.getParcelable("DATA")
                dataDaftarMahasiswa[positionStats] = dataMahasiswa
                adapterDaftarMahasiswa.notifyDataSetChanged()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        DatabaseHelper.closeDatabase()
    }
}
