package com.example.marvelheroes.ui.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelheroes.R
import com.example.marvelheroes.databinding.ItemDetailPictureViewBinding
import com.example.marvelheroes.extensions.setRoundCorners
import com.example.marvelheroes.ui.details.adapter.model.PictureItemView
import com.example.marvelheroes.ui.details.adapter.model.ViewType
import com.example.marvelheroes.ui.details.adapter.model.ViewTypeDelegateAdapter
import com.example.marvelheroes.util.ImageUtils

class CharacterPicturesDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return PicturesViewHolder(
            ItemDetailPictureViewBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as PicturesViewHolder
        holder.bind(item as PictureItemView)
    }

    inner class PicturesViewHolder(view: ItemDetailPictureViewBinding) :
        RecyclerView.ViewHolder(view.root) {

        private val binding = ItemDetailPictureViewBinding.bind(view.root)
        private val imageview = binding.characterPicture

        fun bind(item: PictureItemView) {
            ImageUtils.load(
                imageView = imageview,
                placeholder = R.drawable.ic_thor,
                url = item.run { "$path.$extension" }
            )
            imageview.setRoundCorners(R.dimen.radius_size_s)
        }
    }

}
