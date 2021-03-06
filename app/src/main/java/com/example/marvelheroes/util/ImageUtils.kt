package com.example.marvelheroes.util

import android.widget.ImageView
import androidx.annotation.DrawableRes
import coil.load
import coil.request.ImageRequest
import coil.request.ImageResult
import coil.size.Scale
import com.example.marvelheroes.R

object ImageUtils {
        fun load(imageView: ImageView, url:String,
                 @DrawableRes placeholder: Int) {
            imageView.load(url) {
                crossfade(true)
                placeholder(placeholder)
                allowHardware(true)
                scale(Scale.FIT)
                listener(object : ImageRequest.Listener {
                    override fun onSuccess(
                        request: ImageRequest,
                        metadata: ImageResult.Metadata
                    ) {
                        super.onSuccess(request, metadata)
                    }

                    override fun onError(
                        request: ImageRequest,
                        throwable: Throwable
                    ) {
                        super.onError(request, throwable)
                        imageView.setImageResource(R.drawable.ic_launcher_background)
                    }
                })
            }
        }
}
