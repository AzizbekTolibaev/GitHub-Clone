package com.example.githubclone.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.githubclone.MainActivity
import com.example.githubclone.R
import com.example.githubclone.databinding.FragmentHomeBinding
import com.example.githubclone.utils.LocalStorage

class HomeFragment: Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.findNavController()

        binding.linearLayoutRepository.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_repositoriesFragment)
        }

        binding.btnSearch.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_searchFragment)
        }
    }
}
