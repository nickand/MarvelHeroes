package com.example.memefon.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.memefon.extensions.setRoundCorners
import com.example.memefon.util.ImageUtils
import com.example.memefon.R
import com.example.memefon.databinding.ItemViewCharacterBinding
import com.example.memefon.domain.MemefonModel
import com.example.memefon.util.formatAsSimple
import com.example.memefon.util.toDateWithTimeZoneAsGmt

/**
 * Adapter to display search results.
 */
class MemefonListAdapter(
    private val onItemClicked: (MemefonModel) -> Unit
) : RecyclerView.Adapter<MemefonListAdapter.ResultViewHolder>()  {

    var memefonList: List<MemefonModel?>? = emptyList()
        set(value) {
            val diffCallback = MemefonDiffCallback(value, field)
            DiffUtil.calculateDiff(diffCallback).dispatchUpdatesTo(this)
            field = value
        }

    inner class ResultViewHolder(
        view: ItemViewCharacterBinding,
        onItemClicked: (Int) -> Unit
    ) : RecyclerView.ViewHolder(view.root) {

        private val binding = ItemViewCharacterBinding.bind(view.root)
        val title = binding.memeTitle
        val username = binding.memeUsername
        val date = binding.dateLabel
        val memeImage = binding.memeImage
        val usernameImage = binding.memeUsernameImage
        val category = binding.memeCategory

        init {
            itemView.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        return ResultViewHolder(
            ItemViewCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ) { memefonList?.get(it)?.let(onItemClicked) }
    }

    override fun getItemCount(): Int {
        return memefonList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.title.text = memefonList?.get(position)?.title
        holder.username.text = memefonList?.get(position)?.user?.username
        ImageUtils.load(
            imageView = holder.usernameImage,
            placeholder = R.drawable.ic_thor,
            url = memefonList?.get(position)?.user?.photo.orEmpty()
        )
        holder.usernameImage.setRoundCorners(R.dimen.radius_size_xxxxl)
        holder.category.text = memefonList?.get(position)?.category?.name
        holder.date.text = memefonList?.get(position)?.createdAt?.toDateWithTimeZoneAsGmt()?.formatAsSimple()
        ImageUtils.load(
            imageView = holder.memeImage,
            placeholder = R.drawable.ic_thor,
            url = memefonList?.get(position)?.imageUrl.orEmpty()
        )
        holder.memeImage.scaleType = ImageView.ScaleType.MATRIX
        holder.memeImage.setRoundCorners(R.dimen.radius_size_s)
    }
}

class MemefonDiffCallback<out T: List<*>>(private val oldList: T?, private val newList: T?): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList?.size ?: 0

    override fun getNewListSize(): Int = newList?.size ?: 0

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList?.get(oldItemPosition) === newList?.get(newItemPosition)

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList?.get(oldItemPosition) == newList?.get(newItemPosition)

}
