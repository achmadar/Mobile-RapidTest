package com.learn.app.kotlinrapidtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterAntrian (val listAntrian: ArrayList<Antrian>): RecyclerView.Adapter<AdapterAntrian.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val antrian: Antrian=listAntrian[position]
        holder?.textViewNamaRs?.text = antrian.namars
        holder?.textViewKuotaRs?.text = antrian.kuotars

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent?.context).inflate(R.layout.list_antrian, parent, false)
        return  ViewHolder(v)

    }


    override fun getItemCount(): Int {

        return listAntrian.size
    }

    class  ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val textViewNamaRs = itemView.findViewById(R.id.antrian_nama_rs) as TextView
        val textViewKuotaRs = itemView.findViewById(R.id.antrian_kuota_rs) as TextView
    }
}