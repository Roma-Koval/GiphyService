package com.example.giphyservice.ui.details

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
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
        Glide.with(this@DetailFragment).load(url).into(binding.detailImageView)

        binding.shareButton.setOnClickListener {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, url)
                type = "text/plain"
            }
            startActivity(Intent.createChooser(shareIntent, null))
        }

        val title = arguments?.getString(TITLE)
        binding.gifTitle.text = title

        val name = arguments?.getString(NAME)
        binding.displayName.text = name

        val username = arguments?.getString(USERNAME)
        val finalUsername = "@$username"
        binding.username.text = finalUsername

        val avatarUrl = arguments?.getString(AVATAR_URL)
        Glide.with(this@DetailFragment).load(avatarUrl).into(binding.avatar)

        val isVerified = arguments?.getBoolean(IS_VERIFIED)
        binding.verifiedIcon.isVisible = isVerified!!

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val URL_KEY = "url"
        private const val TITLE = "title"
        private const val NAME= "name"
        private const val USERNAME = "username"
        private const val AVATAR_URL = "avatarUrl"
        private const val IS_VERIFIED = "isVerified"
        const val DetailFragment_NAME = "DetailFragment"

        fun newInstance(
            url: String,
            title: String,
            name: String,
            username: String,
            avatarUrl: String,
            isVerified: Boolean
        ) = DetailFragment().apply {
            arguments = bundleOf(
                URL_KEY to url,
                TITLE to title,
                NAME to name,
                USERNAME to username,
                AVATAR_URL to avatarUrl,
                IS_VERIFIED to isVerified
            )
        }
    }
}
