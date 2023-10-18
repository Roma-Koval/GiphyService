package com.example.giphyservice.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.giphyservice.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var binding: FragmentDetailBinding? = null
    private val getBinding get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        val url = requireArguments().getString("url")
        Glide.with(this).load(url).into(getBinding.fragmentImageView)

        return getBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}