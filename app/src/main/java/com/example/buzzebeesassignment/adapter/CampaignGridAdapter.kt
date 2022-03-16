package com.example.buzzebeesassignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.buzzebeesassignment.Contextor
import com.example.buzzebeesassignment.R
import com.example.buzzebeesassignment.data.CampaignDao

class CampaignGridAdapter (private val list: ArrayList<CampaignDao>, val callback: CallBack?) : RecyclerView.Adapter<CampaignGridAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(position: Int, data: CampaignDao){
            val textName: TextView = itemView.findViewById(R.id.textName)
            val textPrice: TextView = itemView.findViewById(R.id.textPrice)
            val imageCampaign: ImageView = itemView.findViewById(R.id.imageCampaign)

            textName.text = data.name
            textPrice.text = data.price

            Glide.with(Contextor.getContext())
                .load(data.imageUrl)
                .centerCrop()
                .into(imageCampaign)
        }

    }
    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CampaignGridAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_campaign, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: CampaignGridAdapter.ViewHolder, position: Int) {
        holder.bindItems(position, list[position])
        holder.itemView.setOnClickListener {
            if(callback != null){
                callback.onClickCampaign(list[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface CallBack{
        fun onClickCampaign(campaign: CampaignDao)
    }

}