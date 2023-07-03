package com.example.githubclone.presentation.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.githubclone.R
import com.example.githubclone.databinding.FragmentLoginBinding
import com.example.githubclone.presentation.viewmodels.LoginViewModel
import com.example.githubclone.utils.Constants
import com.example.githubclone.utils.LocalStorage
import com.example.githubclone.utils.shPreferencePutBoolean
import com.example.githubclone.utils.shPreferencePutString
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment: Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModel<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        binding.tvPrivacy.movementMethod = LinkMovementMethod.getInstance()

        initListener()
        initObservers()
    }

    private fun initListener() {
        binding.btnSignIn.setOnClickListener {
            processLogin()
        }

    }

    override fun onResume() {
        super.onResume()
        val uri: Uri? = requireActivity().intent?.data
        if (uri != null){
            val code = uri.getQueryParameter("code")
            MainScope().launch {
                if(code != null){
                    viewModel.getAccessToken(code)
                } else if((uri.getQueryParameter("error")) != null){
                    Toast.makeText(requireContext(), "Something went wrong!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun processLogin() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(
            "https://github.com/login/oauth/authorize?client_id=${Constants.clientId}&scope=repo"))

        startActivity(intent)
    }

    private fun initObservers() {

        viewModel.successLiveData.observe(requireActivity()) {
            shPreferencePutBoolean("isUserLogged", true)
            if (it != null) {
                LocalStorage.pref.edit().putString("userToken", it).apply()
                Log.d("JJJJ", "$it")
            }
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }

        viewModel.messageLiveData.observe(requireActivity()) { }

        viewModel.errorLiveData.observe(requireActivity()) { }
    }
}
