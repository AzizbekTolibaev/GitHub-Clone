package com.example.githubclone.presentation.ui

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.githubclone.MainActivity
import com.example.githubclone.R
import com.example.githubclone.data.dao.entity.SearchData
import com.example.githubclone.databinding.FragmentSearchBinding
import com.example.githubclone.presentation.adapters.searchfragmentadapter.SearchAdapter
import com.example.githubclone.presentation.viewmodels.SearchViewModel
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment: Fragment(R.layout.fragment_search) {
    private lateinit var binding: FragmentSearchBinding
    private val viewModel by viewModel<SearchViewModel>()
    private val adapter = SearchAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)

        initVariables()
        initObservers()
        initListeners()
        swipeToDelete()

        binding.progressBar.visibility = View.VISIBLE
        binding.linearLayoutRcView.visibility = View.GONE

    }

    private fun initVariables() {

        lifecycleScope.launch {
            viewModel.getAllSearch()
        }

        (requireActivity() as MainActivity).hideBottomNavigation()

        binding.rcView.adapter = adapter
    }

    @SuppressLint("SetTextI18n")
    private fun initListeners() {

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.etSearch.addTextChangedListener {
            if (it?.isNotEmpty() == true) {
                binding.linearLayoutEmpty.visibility = View.GONE
                binding.linearLayoutRcView.visibility = View.GONE
                binding.linearLayoutSearchWith.visibility = View.VISIBLE

                binding.tvRepositories.text = "Repositories with \"$it\""
                binding.tvIssues.text = "Issues with \"$it\""
                binding.tvPullRequest.text = "Pull Requests with \"$it\""
                binding.tvPeople.text = "People with \"$it\""
                binding.tvOrganizations.text = "Organizations with \"$it\""
                binding.tvJumpTo.text = "Jump to \"$it\""
            } else {
                binding.linearLayoutSearchWith.visibility = View.GONE
                if (adapter.currentList.toMutableList().isNotEmpty()) {
                    binding.linearLayoutRcView.visibility = View.VISIBLE
                } else {
                    binding.linearLayoutEmpty.visibility = View.VISIBLE
                }
            }
        }
        
        adapter.setItemClickListener { 
            binding.etSearch.setText(it.name)
        }

        binding.tvPeople.setOnClickListener {
            val etSearchText = binding.etSearch.text.toString()
            lifecycleScope.launch {
                viewModel.addSearch(SearchData(0, etSearchText))
            }
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToSearchWithUsernameFragment(etSearchText))
        }

        binding.tvRepositories.setOnClickListener {
            val etSearchText = binding.etSearch.text.toString()
            lifecycleScope.launch {
                viewModel.addSearch(SearchData(0, etSearchText))
            }
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToSearchWithRepositoriesFragment(etSearchText))
        }
    }

    private fun initObservers() {
        viewModel.getAllSearchLiveData.observe(requireActivity()) {
            if (it.isNotEmpty()) {
                binding.progressBar.visibility = View.GONE
                binding.linearLayoutEmpty.visibility = View.GONE
                if (binding.linearLayoutSearchWith.visibility == View.VISIBLE) {
                    binding.linearLayoutRcView.visibility = View.GONE
                } else {
                    binding.linearLayoutRcView.visibility = View.VISIBLE
                }
                adapter.submitList(it)
            } else {
                binding.linearLayoutEmpty.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
                binding.linearLayoutRcView.visibility = View.GONE
            }
        }
    }

    private fun swipeToDelete() {
        val simpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                val deleteData = adapter.currentList.toMutableList()[position]
                adapter.removeItem(deleteData)
                adapter.notifyItemRemoved(position)

                lifecycleScope.launch {
                    viewModel.delete(deleteData)
                    viewModel.getAllSearch()
                }
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(requireContext(), R.color.thick_red_color))
                    .addSwipeLeftActionIcon(R.drawable.ic_delete)
                    .setSwipeLeftLabelColor(ContextCompat.getColor(requireContext(), R.color.white))
                    .create()
                    .decorate()
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }

        ItemTouchHelper(simpleCallback).attachToRecyclerView(binding.rcView)
    }
}
