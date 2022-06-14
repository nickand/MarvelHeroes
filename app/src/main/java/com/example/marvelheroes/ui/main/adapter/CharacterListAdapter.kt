package com.example.marvelheroes.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelheroes.R
import com.example.marvelheroes.data.model.Character
import com.example.marvelheroes.databinding.ItemViewCharacterBinding
import com.example.marvelheroes.extensions.setRoundCorners
import com.example.marvelheroes.util.ImageUtils
import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * Adapter to display search results.
 */
class CharacterListAdapter(
    private val onItemClicked: (Character) -> Unit
) : RecyclerView.Adapter<CharacterListAdapter.ResultViewHolder>()  {

    var characterList: List<Character?>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ResultViewHolder(
        view: ItemViewCharacterBinding,
        onItemClicked: (Int) -> Unit
    ) : RecyclerView.ViewHolder(view.root) {

        private val binding = ItemViewCharacterBinding.bind(view.root)
        val name = binding.productNameLabel
        val price = binding.productPriceLabel
        val image = binding.productImage

        init {
            itemView.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        return ResultViewHolder(
            ItemViewCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ) { characterList?.get(it)?.let(onItemClicked) }
    }

    override fun getItemCount(): Int {
        return characterList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.name.text = characterList?.get(position)?.name
        val decimalFormat = DecimalFormat("#,###,###")
        decimalFormat.roundingMode = RoundingMode.DOWN
        holder.price.text = "$ ".plus(decimalFormat.format(characterList?.get(position)?.comics))
        ImageUtils.load(
            imageView = holder.image,
            placeholder = R.drawable.ic_image,
            url = characterList?.get(position)?.thumbnail?.path + "." + characterList?.get(position)?.thumbnail?.extension
        )
        holder.image.setRoundCorners(R.dimen.radius_size_s)
    }
}