package com.example.buzzebeesassignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.buzzebeesassignment.BR
import com.example.buzzebeesassignment.Contextor
import com.example.buzzebeesassignment.data.CampaignDao
import com.example.buzzebeesassignment.databinding.ItemCampaignBinding

class CampaignListAdapter(val callback: CallBack?): ListAdapter<CampaignDao, CampaignListAdapter.ViewHolder>(CampaignDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCampaignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        holder.bindItems(data)
        holder.itemView.setOnClickListener {
            if(callback != null) callback.onClickCampaign(data)
        }
    }

    class ViewHolder(val binding: ItemCampaignBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItems(data: CampaignDao){
            with(data){
                Glide.with(Contextor.getContext())
                    .load(this.imageUrl)
                    .centerCrop()
                    .into(binding.imageCampaign)
                binding.setVariable(BR.item, this)
                binding.executePendingBindings()
            }
        }

    }

    class CampaignDiffCallback : DiffUtil.ItemCallback<CampaignDao>() {
        override fun areItemsTheSame(oldItem: CampaignDao, newItem: CampaignDao): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CampaignDao, newItem: CampaignDao): Boolean {
            return oldItem.price == newItem.price
                    && oldItem.imageUrl == newItem.imageUrl
                    && oldItem.description == newItem.description
        }
    }

    interface CallBack{
        fun onClickCampaign(campaign: CampaignDao)
    }
}