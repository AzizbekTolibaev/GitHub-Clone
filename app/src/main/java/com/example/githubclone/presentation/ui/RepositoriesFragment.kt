package com.example.githubclone.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.githubclone.MainActivity
import com.example.githubclone.R
import com.example.githubclone.databinding.FragmentRepositoriesBinding
import com.example.githubclone.presentation.adapters.repositoriesfragmentadapter.RepositoryAdapter
import com.example.githubclone.presentation.viewmodels.RepositoriesViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoriesFragment: Fragment(R.layout.fragment_repositories) {
    private lateinit var binding: FragmentRepositoriesBinding
    private val adapter = RepositoryAdapter()
    private val viewModel by viewModel<RepositoriesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRepositoriesBinding.bind(view)

        initVariables()
        initListener()
        initObservers()

    }

    private fun initVariables() {
        lifecycleScope.launch {
            viewModel.getUserRepositories()
        }

        binding.rcView.adapter = adapter

        binding.constraintLayout.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun initListener() {
        binding.swipeRefresh.setOnRefreshListener {
            lifecycleScope.launch {
                viewModel.getUserRepositories()
            }
        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initObservers() {

        viewModel.successLiveData.observe(requireActivity()) {
            adapter.submitList(it)
            Log.d("QQQQ", "List: $it")
            binding.swipeRefresh.isRefreshing = false
            binding.constraintLayout.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        }

        viewModel.messageLiveData.observe(requireActivity()) {
            binding.swipeRefresh.isRefreshing = false
            binding.constraintLayout.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
            Log.d("QQQQ", "MessageLiveData")
        }

        viewModel.errorLiveData.observe(requireActivity()) {
            binding.swipeRefresh.isRefreshing = false
            binding.constraintLayout.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
            Log.d("QQQQ", "ErrorLiveData")
        }
    }
}
