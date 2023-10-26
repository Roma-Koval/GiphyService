package com.example.giphyservice.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.giphyservice.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        val url = arguments?.getString(URL_KEY)
        Glide.with(this@DetailFragment).load(url).into(binding.fragmentImageView)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val URL_KEY = "url"
        const val DetailFragment_NAME = "DetailFragment"

        fun newInstance(url: String) = DetailFragment().apply {
            arguments = bundleOf(URL_KEY to url)
        }
    }
}
