package com.example.marvelheroes.ui.details.adapter

import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelheroes.ui.details.adapter.model.AdapterConstants
import com.example.marvelheroes.ui.details.adapter.model.PictureItemView
import com.example.marvelheroes.ui.details.adapter.model.DetailsItemView
import com.example.marvelheroes.ui.details.adapter.model.AttributesItemView
import com.example.marvelheroes.ui.details.adapter.model.ViewType
import com.example.marvelheroes.ui.details.adapter.model.ViewTypeDelegateAdapter

/**
 * Adapter to display all data regarding a Product
 */
class CharacterDetailsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var pictures: PictureItemView? = null
        set(value) {
            field = value
            value?.let { items.add(it) }
            notifyDataSetChanged()
        }
    var titleInfo: DetailsItemView? = null
        set(value) {
            field = value
            value?.let { items.add(it) }
            notifyDataSetChanged()
        }
    var attributes: List<AttributesItemView>? = null
        set(value) {
            field = value
            value?.let { items.addAll(it) }
            notifyDataSetChanged()
        }
    var items: ArrayList<ViewType> = ArrayList()
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()

    init {
        //delegateAdapters.put(PICTURES, ProductPicturesDelegateAdapter())
        //delegateAdapters.put(DETAILS, ProductTitleDelegateAdapter())
        //delegateAdapters.put(ATTRIBUTES, ProductAttributesDelegateAdapter())
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        delegateAdapters.get(viewType)!!.onCreateViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position))!!.onBindViewHolder(holder, items[position])
    }

    override fun getItemViewType(position: Int) = items[position].getViewType()

}










