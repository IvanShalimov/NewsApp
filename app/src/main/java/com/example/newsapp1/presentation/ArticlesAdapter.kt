package com.example.newsapp1.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp1.R
import com.example.newsapp1.presentation.models.ArticleItem

class ArticlesAdapter: RecyclerView.Adapter<ArticleViewHolder>() {

    var items: List<ArticleItem> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_item_layout, parent, false)

        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.populate(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class ArticleViewHolder(private val root:View): RecyclerView.ViewHolder(root) {

    fun populate(articleItem: ArticleItem) {
        itemView.findViewById<TextView>(R.id.article_name).text = articleItem.title
        itemView.findViewById<TextView>(R.id.article_description).text = articleItem.description
        itemView.findViewById<TextView>(R.id.article_author).text = articleItem.author
        itemView.findViewById<TextView>(R.id.article_date).text = articleItem.publishedAt
        Glide.with(root.context)
            .load(articleItem.thumbnail)
            .into(itemView.findViewById<ImageView>(R.id.article_thumbnail))
    }
}