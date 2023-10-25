package com.example.giphyservice.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.giphyservice.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance(url: String): DetailFragment {
            val detailFragment = DetailFragment()
            val bundle = Bundle()
            bundle.putString("url", url)
            detailFragment.arguments = bundle
            return detailFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        val url = arguments?.getString("url")
        Glide.with(this@DetailFragment).load(url).into(binding.fragmentImageView)

/*        setFragmentResultListener("requestKey") { requestKey, bundle ->
            val url = bundle.getString("url")
            Glide.with(this@DetailFragment).load(url).into(getBinding.fragmentImageView)
        }*/
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}