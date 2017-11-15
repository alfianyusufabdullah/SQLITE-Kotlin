package com.jonesrandom.sqlite_kotlin

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jonesrandom.sqlite_kotlin.database.DatabaseAdapter
import com.jonesrandom.sqlite_kotlin.model.ModelMahasiswa
import kotlinx.android.synthetic.main.dialog_detail_mahasiswa.*

/**
 * Created by jonesrandom on 11/14/17.
 *
 * #JanganLupaBahagia
 *
 */

class DetailMahasiswaDialog : BottomSheetDialogFragment() {

    private var dataMahasiswa = ModelMahasiswa()
    lateinit private var dbAdapter: DatabaseAdapter

    companion object {
        lateinit private var listeners: OnDialogItemClick

        fun newInstance(data: ModelMahasiswa, listener: OnDialogItemClick): DetailMahasiswaDialog {

            listeners = listener
            val detail = DetailMahasiswaDialog()

            val bind = Bundle()
            bind.putParcelable("DATA", data)

            detail.arguments = bind
            return detail

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dbAdapter = context?.let { DatabaseAdapter(it) }!!
        val args = arguments

        if (args != null)
            dataMahasiswa = args.getParcelable("DATA")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_detail_mahasiswa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialogNama.text = dataMahasiswa.nama.toUpperCase()
        dialogNim.text = dataMahasiswa.nim.toString()
        dialogSemester.text = dataMahasiswa.semster

        toolbarDialog.inflateMenu(R.menu.dialog_menu)
        toolbarDialog.setOnMenuItemClickListener {

            when (it.itemId) {

                R.id.dialogEdit -> {
                    listeners.dialogEditCallback(dataMahasiswa)
                    dialog.dismiss()
                }
                R.id.dialogHapus -> {
                    val build = context?.let { it1 -> AlertDialog.Builder(it1) }
                    build?.setTitle("Hapus Data")
                    build?.setMessage("Apakah Kamu Ingin Menghapus Data ${dataMahasiswa.nama.toUpperCase()}")
                    build?.setPositiveButton("HAPUS", { dialogInterface, i ->

                        val stas = dbAdapter.deleteData(dataMahasiswa.id)

                        if (stas != 0) {
                            dialog.dismiss()
                            listeners.dialogDeleteCallback(dataMahasiswa)
                        }

                    })
                    build?.setNegativeButton("BATAL", null)
                    build?.create()?.show()
                }
            }

            true
        }

    }

    interface OnDialogItemClick {
        fun dialogEditCallback(data: ModelMahasiswa)
        fun dialogDeleteCallback(data: ModelMahasiswa)
    }
}