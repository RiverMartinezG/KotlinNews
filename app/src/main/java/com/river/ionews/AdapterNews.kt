package com.river.ionews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AdapterNews (var list: ArrayList<News>): RecyclerView.Adapter<AdapterNews.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewToInflate = LayoutInflater.from(parent.context).inflate(R.layout.content_item, parent, false)

        return ViewHolder(viewToInflate)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AdapterNews.ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindItems(data: News) {
            val title: TextView = itemView.findViewById(R.id.title)
            val excerpt: TextView = itemView.findViewById(R.id.excerpt)
            val frontImage: ImageView = itemView.findViewById(R.id.front_img)

            title.text = data.title
            excerpt.text = data.excerpt

            Glide.with(itemView.context).load(data.frontImage).into(frontImage)

            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "Intentando leer: ${data.title}", Toast.LENGTH_LONG).show()
            }
        }
    }
}