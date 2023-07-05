package com.example.githubclone.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.githubclone.R
import com.example.githubclone.databinding.FragmentMainBinding

class MainFragment: Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        navController = (childFragmentManager.findFragmentById(R.id.fragment_container_main) as NavHostFragment).navController

        binding.bottomNavigation.setupWithNavController(navController)
    }
}
