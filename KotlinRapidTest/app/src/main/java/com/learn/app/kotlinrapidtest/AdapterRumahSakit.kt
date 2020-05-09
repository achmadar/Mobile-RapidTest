package com.learn.app.kotlinrapidtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterRumahSakit (val rsList: ArrayList<RumahSakit>, var itemClickListener : OnItemClickListener): RecyclerView.Adapter<AdapterRumahSakit.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var rs: RumahSakit=rsList[position]
        holder?.nama_rs?.text = rs.nama

        holder.bind(rs, itemClickListener)

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
        val telp_rs = itemView.findViewById(R.id.notelp_rs) as TextView
        val alamat_rs = itemView.findViewById(R.id.alamat_rs) as TextView
        val jadwal_rs = itemView.findViewById(R.id.jadwal_rs) as TextView

        fun bind(rs:RumahSakit, clickListener : OnItemClickListener) {

            nama_rs.text = rs.nama

            itemView.setOnClickListener {
                clickListener.onItemClick(rs)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(rs:RumahSakit)
    }

}

