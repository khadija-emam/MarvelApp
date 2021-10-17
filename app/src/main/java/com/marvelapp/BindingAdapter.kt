package com.marvelapp

import android.content.Context
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.marvelapp.di.API_PRIVATE
import com.marvelapp.di.API_PUBLIC
import com.marvelapp.model.Thumbnail
import com.squareup.picasso.Picasso
import java.util.concurrent.TimeUnit

@BindingAdapter("characterImageUrl")
fun bindCharacterImage(imgView: ImageView, imgUrl: Thumbnail?) {
    imgUrl?.let {
        Log.i("TAG", "bindCharacterImage: ${imgUrl.path}/portrait_xlarge.${imgUrl.extension} ")
        Picasso.get()
            .load("${imgUrl.path}/portrait_xlarge.${imgUrl.extension}")
            .error(R.drawable.ic_broken_image_24)
            .into(imgView)

    }
}

