package com.example.marvelheroes.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
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

    var characterList: List<Character?>? = emptyList()
        set(value) {
            val diffCallback = DiffCallback(value, field)
            DiffUtil.calculateDiff(diffCallback).dispatchUpdatesTo(this)
            field = value
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
        holder.price.text = """$ ${decimalFormat.format(characterList?.get(position)?.comics?.available)}"""
        ImageUtils.load(
            imageView = holder.image,
            placeholder = R.drawable.ic_image,
            url = characterList?.get(position)?.thumbnail?.run { "$path.$extension" }.orEmpty()
        )
        holder.image.setRoundCorners(R.dimen.radius_size_s)
    }
}

class DiffCallback<out T: List<*>>(private val oldList: T?, private val newList: T?): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList?.size ?: 0

    override fun getNewListSize(): Int = newList?.size ?: 0

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList?.get(oldItemPosition) === newList?.get(newItemPosition)

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList?.get(oldItemPosition) == newList?.get(newItemPosition)

}
