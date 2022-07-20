package com.example.marvelheroes.ui.details.adapter.model

/**
 * @see ViewType to hold an @see AdapterConstants.PICTURES
 */
data class PictureItemView(
    var path: String? = null,
    var extension: String? = null
): ViewType {
    override fun getViewType(): Int {
        return AdapterConstants.PICTURES
    }
}
