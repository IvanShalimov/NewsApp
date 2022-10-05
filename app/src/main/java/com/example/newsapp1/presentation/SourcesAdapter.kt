package com.example.newsapp1.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp1.R
import com.example.newsapp1.presentation.models.SourceItem

class SourcesAdapter(): RecyclerView.Adapter<SourceViewHolder>() {

    var items: List<SourceItem> = mutableListOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    lateinit var listener: (item:SourceItem) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.source_item_layout, parent, false)

        return SourceViewHolder(view)
    }

    override fun onBindViewHolder(holder: SourceViewHolder, position: Int) {
        holder.populate(items[position], listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class SourceViewHolder(private val root: View): RecyclerView.ViewHolder(root){

    fun populate(sourceItem: SourceItem, listener: (item:SourceItem) -> Unit) {
        val name =  itemView.findViewById<TextView>(R.id.source_name)
        name.text = sourceItem.name
        val description =  itemView.findViewById<TextView>(R.id.source_description)
        description.text = sourceItem.description
        val url =  itemView.findViewById<TextView>(R.id.source_url)
        url.text = sourceItem.url
        itemView.findViewById<View>(R.id.source_cell).setOnClickListener { listener(sourceItem) }
    }
}