package com.example.giphyservice.ui.list

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphyservice.data.model.Gif
import com.example.giphyservice.R

// ViewHolder - служить для оптимізації ресурсів і являється контейнером
// для всіх елементів які входять в список
// Glide - забезпечує підгрузку гіфок
class GifViewHolder(
    itemView: View,
    private val listener: GifsAdapter.OnItemClickListener
) :
    RecyclerView.ViewHolder(itemView) {
    private val imageView = itemView.findViewById<ImageView>(R.id.ivGif)

    fun bind(data: Gif) {
        Glide.with(itemView.context).load(data.images.originalImage.url)
            .into(imageView)
        itemView.setOnClickListener {
            listener.onItemClick(data)
        }
    }
}