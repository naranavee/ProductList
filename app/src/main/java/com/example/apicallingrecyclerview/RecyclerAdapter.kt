package com.example.apicallingrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val list:ArrayList<DataModelItem>, val context: Context):RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        val userIdTxt:TextView=itemView.findViewById(R.id.user_id_txt)

        val titleTxt:TextView=itemView.findViewById(R.id.title_txt)

        val authorTxt:TextView=itemView.findViewById(R.id.author_txt)

        val yearTxt:TextView=itemView.findViewById(R.id.year_txt)

        val descriptionTxt:TextView=itemView.findViewById(R.id.description_txt)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view= LayoutInflater.from(parent.context).inflate(R.layout.design_layout,parent,false)

        return ViewHolder(view)


    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem=list[position]

        holder.apply {

            userIdTxt.text= currentItem.id.toString()

            yearTxt.text= currentItem.year.toString()

            titleTxt.text=currentItem.title

            authorTxt.text= currentItem.author

            descriptionTxt.text=currentItem.description





        }





    }
}