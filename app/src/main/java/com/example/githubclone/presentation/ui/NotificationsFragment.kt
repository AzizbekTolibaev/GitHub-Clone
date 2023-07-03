package com.example.githubclone.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.githubclone.R
import com.example.githubclone.databinding.FragmentNotificationsBinding

class NotificationsFragment: Fragment(R.layout.fragment_notifications)  {
    private lateinit var binding: FragmentNotificationsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNotificationsBinding.bind(view)


        binding.lottieAnimation.playAnimation()
    }
}