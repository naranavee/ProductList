package com.example.apicallingrecyclerview.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apicallingrecyclerview.R
import com.example.apicallingrecyclerview.model.DataModelItem

class RecyclerAdapter(private val list: ArrayList<DataModelItem>, private val context: Context) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val idTxt: TextView = itemView.findViewById(R.id.id_txt)
        val titleTxt: TextView = itemView.findViewById(R.id.title_txt)
        val categoryTxt: TextView = itemView.findViewById(R.id.category_txt)
        val descriptionTxt: TextView = itemView.findViewById(R.id.description_txt)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.design_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]

        holder.idTxt.text = currentItem.id.toString()
        holder.titleTxt.text = currentItem.title
        holder.categoryTxt.text = currentItem.category
        holder.descriptionTxt.text = currentItem.description
    }
}