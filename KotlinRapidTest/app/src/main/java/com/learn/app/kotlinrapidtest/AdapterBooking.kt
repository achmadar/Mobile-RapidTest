package com.learn.app.kotlinrapidtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterBooking (val bookList: ArrayList<Booking>, var itemClickListener : OnItemClickListener): RecyclerView.Adapter<AdapterBooking.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var book: Booking=bookList[position]
        holder?.nama_rs?.text = book.nama
        holder?.jadwal_rs?.text = book.jadwal

        holder.bind(book, itemClickListener)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent?.context).inflate(R.layout.list_booking, parent, false)
        return  ViewHolder(v)

    }

    override fun getItemCount(): Int {

        return bookList.size
    }

    class  ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val nama_rs = itemView.findViewById(R.id.list_booking_nama_rs) as TextView
        val jadwal_rs = itemView.findViewById(R.id.list_booking_jadwal_rs) as TextView

        fun bind(book:Booking, clickListener : OnItemClickListener) {

            nama_rs.text = book.nama
            jadwal_rs.text = book.jadwal

            itemView.setOnClickListener {
                clickListener.onItemClick(book)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(book: Booking)
    }

}