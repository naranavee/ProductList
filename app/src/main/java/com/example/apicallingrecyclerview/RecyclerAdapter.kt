package com.example.apicallingrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(val list:ArrayList<DataModelItem>,val context: Context):RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {



        val idTxt:TextView=itemView.findViewById(R.id.id_txt)


        val titleTxt:TextView=itemView.findViewById(R.id.title_txt)

        val categoryTxt:TextView=itemView.findViewById(R.id.category_txt)

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



            idTxt.text= currentItem.id.toString()

            titleTxt.text=currentItem.title

            categoryTxt.text=currentItem.category

            descriptionTxt.text= currentItem.description




        }





    }
}