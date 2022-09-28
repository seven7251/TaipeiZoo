package com.example.taipeizoo.ui.area

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taipeizoo.R
import com.example.taipeizoo.ui.model.AreaInfo
import com.squareup.picasso.Picasso

class AreaAdapter(var areaInfoList: ArrayList<AreaInfo>, private val listener: AreaPresenter.View) :
    RecyclerView.Adapter<AreaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AreaAdapter.ViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.area_item, parent, false)
            .let { ViewHolder(it) }
    }

    override fun onBindViewHolder(holder: AreaAdapter.ViewHolder, position: Int) {
        val areaInfo = areaInfoList[position]
        holder.bind(areaInfo, listener)
    }

    override fun getItemCount(): Int {
        return areaInfoList.size;
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.area_img)
        val info: TextView = view.findViewById(R.id.area_info)

        fun bind(areaInfo: AreaInfo, listener: AreaPresenter.View) {
            info.text = areaInfo.e_name
            Picasso.get().load(areaInfo.e_pic_url).into(img)
            itemView.setOnClickListener {
                listener.onItemClick(areaInfo)
            }
        }
    }
}