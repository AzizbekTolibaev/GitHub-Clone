package com.example.githubclone.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.githubclone.R
import com.example.githubclone.databinding.FragmentSearchWithUsernameBinding
import com.example.githubclone.presentation.adapters.searchwithusernamefragmentadapter.SearchWithUsernameAdapter
import com.example.githubclone.presentation.viewmodels.SearchWithUsernameViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchWithUsernameFragment: Fragment(R.layout.fragment_search_with_username) {
    private lateinit var binding: FragmentSearchWithUsernameBinding
    private val adapter = SearchWithUsernameAdapter()
    private val viewModel by viewModel<SearchWithUsernameViewModel>()
    private val args: SearchWithUsernameFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchWithUsernameBinding.bind(view)

        initObservers()
        val name = args.name

        lifecycleScope.launch {
            viewModel.searchWithUsername(name)
        }

        binding.rcView.adapter = adapter

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnRefresh.setOnClickListener {
            lifecycleScope.launch {
                viewModel.searchWithUsername(name)
            }
            binding.progressBar.visibility = View.VISIBLE
            binding.btnRefresh.visibility = View.GONE
        }

        binding.progressBar.visibility = View.VISIBLE
        binding.rcView.visibility = View.GONE
    }

    private fun initObservers() {
        viewModel.successLiveData.observe(requireActivity()) {
            if (it?.totalCount != 0) {
                binding.progressBar.visibility = View.GONE
                binding.rcView.visibility = View.VISIBLE
                binding.btnRefresh.visibility = View.GONE
                adapter.submitList(it?.items)
            } else {
                binding.progressBar.visibility = View.GONE
                binding.linearLayoutEmpty.visibility = View.VISIBLE
            }
        }

        viewModel.messageLiveData.observe(requireActivity()) { }

        viewModel.errorLiveData.observe(requireActivity()) {
            binding.progressBar.visibility = View.GONE
            binding.btnRefresh.visibility = View.VISIBLE
        }
    }
}
