package com.example.marvelheroes.ui.details.adapter.model

/**
 * @see ViewType to hold an @see AdapterConstants.DETAILS
 */
data class TitleItemView(
    val title: String
): ViewType {
    override fun getViewType(): Int {
        return AdapterConstants.TITLE
    }
}
