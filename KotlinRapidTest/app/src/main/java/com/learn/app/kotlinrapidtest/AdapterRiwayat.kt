package com.learn.app.kotlinrapidtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterRiwayat(val rwList: ArrayList<Riwayat>): RecyclerView.Adapter<AdapterRiwayat.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val riwayat: Riwayat=rwList[position]
        holder?.textViewNama?.text = riwayat.nama
        holder?.textViewStatus?.text = riwayat.status
        holder?.textViewKeterangan?.text = riwayat.keterangan

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent?.context).inflate(R.layout.list_riwayat, parent, false)
        return  ViewHolder(v)

    }


    override fun getItemCount(): Int {
        return rwList.size
    }



    class  ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val textViewNama = itemView.findViewById(R.id.riwayat_nama) as TextView
        val textViewStatus = itemView.findViewById(R.id.riwayat_status) as TextView
        val textViewKeterangan = itemView.findViewById(R.id.riwayat_keterangan) as TextView
    }

}