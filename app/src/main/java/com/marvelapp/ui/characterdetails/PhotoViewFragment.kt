package com.marvelapp.ui.characterdetails

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.marvelapp.R
import com.marvelapp.bindCharacterImage
import com.marvelapp.databinding.ViewPhotoDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoViewFragment:DialogFragment() {
    lateinit var binding: ViewPhotoDialogBinding
    val args by navArgs<PhotoViewFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.view_photo_dialog, container, false)
        binding.lifecycleOwner = this
        setPhoto()
        setGravity()

    return binding.root
    }

    private fun setPhoto(){
        bindCharacterImage(binding.photoImg,args.img)
    }
         private fun setGravity(){
        val window: Window? = dialog!!.window

        // set "origin" to top left corner, so to speak

        // set "origin" to top left corner, so to speak
        window?.setGravity(Gravity.CENTER)


    }
}