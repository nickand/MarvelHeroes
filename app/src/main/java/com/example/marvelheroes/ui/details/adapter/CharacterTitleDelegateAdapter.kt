package com.example.marvelheroes.ui.details.adapter

import android.icu.text.NumberFormat
import android.icu.util.Currency
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelheroes.databinding.ItemDetailTitleViewBinding
import com.example.marvelheroes.ui.details.adapter.model.TitleItemView
import com.example.marvelheroes.ui.details.adapter.model.ViewType
import com.example.marvelheroes.ui.details.adapter.model.ViewTypeDelegateAdapter

class CharacterTitleDelegateAdapter : ViewTypeDelegateAdapter {

    //Vista del titulo del heroe

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        //mostrar binding de la vista del titulo
        //creo vista con el titulo e inflarlo
        return TitleViewHolder(
            ItemDetailTitleViewBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as TitleViewHolder
        holder.bind(item as TitleItemView)
    }

    inner class TitleViewHolder(view: ItemDetailTitleViewBinding) :
        RecyclerView.ViewHolder(view.root) {
        private val binding = ItemDetailTitleViewBinding.bind(view.root)
        private val name = binding.nameLabel

        fun bind(item: TitleItemView) {
            name.text = item.title
        }
    }
}
