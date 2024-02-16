package com.example.giphyservice.ui.main.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.giphyservice.databinding.ItemLayoutBinding
import com.example.giphyservice.ui.Gif

// Adapter - оброблює дані і зв'язує зі списком
//
class GifsAdapter(
    var mListener: OnItemClickListener
) : RecyclerView.Adapter<GifViewHolder>() {

    private val gifs: MutableList<Gif> = mutableListOf()

    fun updateGifs(gif: List<Gif>) {
        gifs.clear()
        gifs.addAll(gif)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(gif: Gif)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        val itemBinding =
            ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GifViewHolder(itemBinding, mListener)
    }

    override fun getItemCount(): Int = gifs.size

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        val data = gifs[position]
        holder.bind(data)
    }
}
