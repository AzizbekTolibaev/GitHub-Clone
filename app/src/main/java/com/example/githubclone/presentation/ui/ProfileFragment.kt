package com.example.githubclone.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.githubclone.MainActivity
import com.example.githubclone.presentation.adapters.profilefragmentadapter.HorizontalRepositoryAdapter
import com.example.githubclone.R
import com.example.githubclone.databinding.FragmentProfileBinding
import com.example.githubclone.presentation.viewmodels.ProfileViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private lateinit var binding: FragmentProfileBinding
    private val adapter = HorizontalRepositoryAdapter()
    private val viewModel by viewModel<ProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        initObserversProfileInfo()
        initObserversRepositories()

        binding.scrollView.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE

        lifecycleScope.launch {
            viewModel.getUserRepositories()
            viewModel.getUserProfileInfo()
        }
        binding.rcView.adapter = adapter

        binding.btnRefresh.setOnClickListener {
            lifecycleScope.launch {
                viewModel.getUserRepositories()
                viewModel.getUserProfileInfo()
            }

            binding.progressBar.visibility = View.VISIBLE
            binding.btnRefresh.visibility = View.GONE
        }
    }

    private fun initObserversRepositories() {
        viewModel.reposSuccessLiveData.observe(requireActivity()) {
            adapter.submitList(it)
        }

        viewModel.reposMessageLiveData.observe(requireActivity()) { }

        viewModel.reposErrorLiveData.observe(requireActivity()) { }
    }

    private fun initObserversProfileInfo() {
        viewModel.userProfileInfoLoadingLiveData.observe(requireActivity()) { }

        viewModel.userProfileInfoSuccessLiveData.observe(requireActivity()) {

            binding.scrollView.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
            binding.btnRefresh.visibility = View.GONE

            Picasso.get().load(it?.avatar_url).into(binding.imgProfileImage)
            binding.tvUserName.text = it?.userName
            binding.tvFollowersCount.text = it?.followers.toString()
            binding.tvFollowingCount.text = it?.following.toString()
            if (it?.bio?.isNotEmpty() == true) {
                binding.tvBio.visibility = View.VISIBLE
                binding.tvBio.text = it.bio
            } else {
                binding.tvBio.visibility = View.GONE
            }
        }

        viewModel.userProfileInfoMessageLiveData.observe(requireActivity()) { }

        viewModel.userProfileInfoErrorLiveData.observe(requireActivity()) {
            binding.progressBar.visibility = View.GONE
            binding.btnRefresh.visibility = View.VISIBLE
        }
    }
}
