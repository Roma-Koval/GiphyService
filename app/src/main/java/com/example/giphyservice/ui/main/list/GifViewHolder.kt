package com.example.giphyservice.ui.main.list

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphyservice.data.model.Gif
import com.example.giphyservice.databinding.ItemLayoutBinding

// ViewHolder - служить для оптимізації ресурсів і являється контейнером
// для всіх елементів які входять в список
// Glide - забезпечує підгрузку гіфок
class GifViewHolder(
    private val itemBinding: ItemLayoutBinding,
    private val listener: GifsAdapter.OnItemClickListener
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(data: Gif) {
        Glide.with(itemBinding.root.context).load(data.images.originalImage.url)
            .into(itemBinding.ivGif)
        itemBinding.root.setOnClickListener {
            listener.onItemClick(data)
        }
    }
}