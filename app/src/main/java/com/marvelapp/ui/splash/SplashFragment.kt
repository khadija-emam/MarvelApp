package com.marvelapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.marvelapp.R
import com.marvelapp.databinding.SplashBinding


class SplashFragment: Fragment() {

    lateinit var binding: SplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.splash, container, false)
        binding.lifecycleOwner = this

        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToCharacterListFragment())
        }, 3000)

        return binding.root
    }
}