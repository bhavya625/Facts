package com.example.arinspectexercise

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.arinspectexercise.model.FactsItem


class FactsAdapter :
    RecyclerView.Adapter<FactsAdapter.FactsItemViewHolder>() {

    private var factsList: List<FactsItem>? = emptyList()

    inner class FactsItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val description: TextView = view.findViewById(R.id.description)
        val thumbnail: ImageView = view.findViewById(R.id.thumbnail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactsItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_facts, parent, false)

        return FactsItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FactsItemViewHolder, position: Int) {
        val factsItem = factsList?.get(position)
        holder.title.text = factsItem?.title
        holder.description.text = factsItem?.description

        Glide.with(holder.itemView)
            .load(factsItem?.imageHref)
            .centerCrop()
            .into(holder.thumbnail)
    }

    override fun getItemCount(): Int {
        return factsList?.size ?: 0
    }

    fun setData(data: List<FactsItem>?) {
        factsList = data
        notifyDataSetChanged()
    }
}
