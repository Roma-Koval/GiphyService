package com.example.giphyservice.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.giphyservice.data.model.Gif
import com.example.giphyservice.R

// Adapter - оброблює дані і зв'язує зі списком
//
class GifsAdapter(
    var mListener: OnItemClickListener,
    val context: Context,
) : RecyclerView.Adapter<GifViewHolder>() {
    val gifs: MutableList<Gif> = mutableListOf()

    fun updateGifs(gif: List<Gif>) {
        gifs.clear()
        gifs.addAll(gif)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(gif: Gif)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        return GifViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false), mListener
        )
    }

    override fun getItemCount(): Int = gifs.size

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        val data = gifs[position]
        holder.bind(data)
    }
}
