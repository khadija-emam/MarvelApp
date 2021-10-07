package com.marvelapp

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.marvelapp.model.Thumbnail
import com.squareup.picasso.Picasso

@BindingAdapter("characterImageUrl")
fun bindCharacterImage(imgView: ImageView, imgUrl: Thumbnail?) {
    imgUrl?.let {
        Log.i("TAG", "bindCharacterImage: ${imgUrl.path}/portrait_xlarge.${imgUrl.extension} ")
        Picasso.get()
            .load("${imgUrl.path}/portrait_xlarge.${imgUrl.extension}")

            .into(imgView)

    }
}
