package com.example.githubclone.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.githubclone.R
import com.example.githubclone.databinding.FragmentSearchWithRepositoriesBinding
import com.example.githubclone.presentation.adapters.searchwithrepositoriesfragmentadapter.SearchWithRepositoriesAdapter
import com.example.githubclone.presentation.viewmodels.SearchWithRepositoriesViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchWithRepositoriesFragment: Fragment(R.layout.fragment_search_with_repositories) {
    private lateinit var binding: FragmentSearchWithRepositoriesBinding
    private val viewModel by viewModel<SearchWithRepositoriesViewModel>()
    private val adapter = SearchWithRepositoriesAdapter()
    private val args: SearchWithRepositoriesFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchWithRepositoriesBinding.bind(view)

        val name = args.name
        lifecycleScope.launch {
            viewModel.searchWithRepositories(name)
        }

        initObservers()

        binding.rcView.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE

        binding.rcView.adapter = adapter

        binding.rcView.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        )

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initObservers() {
        viewModel.successLiveData.observe(requireActivity()) {
            if (it?.totalCount != 0) {
                adapter.submitList(it?.items)
                binding.rcView.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            } else {
                binding.linearLayoutEmpty.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            }
        }

        viewModel.messageLiveData.observe(requireActivity()) { }

        viewModel.errorLiveData.observe(requireActivity()) { }
    }


}
