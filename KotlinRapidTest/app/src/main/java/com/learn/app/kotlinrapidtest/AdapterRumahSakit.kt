package com.learn.app.kotlinrapidtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterRumahSakit (val rsList: ArrayList<RumahSakit>): RecyclerView.Adapter<AdapterRumahSakit.ViewHolder>() {



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val rs: RumahSakit=rsList[position]
        holder?.nama_rs?.text = rs.nama
        holder?.telp_rs?.text = rs.telp
        holder?.alamat_rs?.text = rs.alamat
        holder?.jadwal_rs?.text = rs.jadwal


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent?.context).inflate(R.layout.list_rumah_sakit, parent, false)
        return  ViewHolder(v)

    }


    override fun getItemCount(): Int {

        return rsList.size
    }



    class  ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val nama_rs = itemView.findViewById(R.id.nama_rs) as TextView
        val telp_rs = itemView.findViewById(R.id.telp_rs) as TextView
        val alamat_rs = itemView.findViewById(R.id.alamat_rs) as TextView
        val jadwal_rs = itemView.findViewById(R.id.jadwal_rs) as TextView
    }
}