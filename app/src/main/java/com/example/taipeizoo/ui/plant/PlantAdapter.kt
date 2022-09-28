package com.example.taipeizoo.ui.plant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taipeizoo.R

import com.example.taipeizoo.ui.model.PlantInfo
import com.squareup.picasso.Picasso

class PlantAdapter(var plantInfoList: ArrayList<PlantInfo>, private val listener: PlantPresenter.View) :
    RecyclerView.Adapter<PlantAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.plant_item, parent, false)
            .let { ViewHolder(it) }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plantInfo = plantInfoList[position]
        holder.bind(plantInfo, listener)
    }

    override fun getItemCount(): Int {
        return plantInfoList.size;
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.plant_img)
        val info: TextView = view.findViewById(R.id.plant_info)

        fun bind(plantInfo: PlantInfo, listener: PlantPresenter.View) {
            info.text = plantInfo.F_Brief
            Picasso.get().load(plantInfo.F_Pic01_URL)
                .into(img)
//            itemView.setOnClickListener {
//                listener.onItemClick(plantInfo)
//            }
        }
    }
}