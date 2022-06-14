package com.example.marvelheroes.data.model

import com.google.gson.annotations.SerializedName

/**
 * Data transfer objects to hold the details of a Character
 */
data class CharacterDTO(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("modified") var modified: String? = null,
    @SerializedName("thumbnail") var thumbnail: ThumbnailDTO? = ThumbnailDTO(),
    @SerializedName("resourceURI") var resourceURI: String? = null,
    @SerializedName("comics") var comics: ComicsDTO? = ComicsDTO(),
    @SerializedName("series") var series: SeriesDTO? = SeriesDTO(),
    @SerializedName("stories") var stories: StoriesDTO? = StoriesDTO(),
    @SerializedName("events") var events: EventsDTO? = EventsDTO(),
    @SerializedName("urls") var urls: ArrayList<UrlsDTO> = arrayListOf()
)

data class ThumbnailDTO (
    var path: String? = null,
    var extension: String? = null
)

data class ItemsDTO(
    var resourceURI: String? = null,
    var name: String? = null
)

data class ComicsDTO(
    var available: Int? = null,
    var collectionURI: String? = null,
    var items: ArrayList<ItemsDTO> = arrayListOf(),
    var returned: Int? = null
)

data class SeriesDTO(
    var available: Int? = null,
    var collectionURI: String? = null,
    var items: ArrayList<ItemsDTO> = arrayListOf(),
    var returned: Int? = null
)

data class StoriesDTO(
    var available: Int? = null,
    var collectionURI: String? = null,
    var items: ArrayList<ItemsDTO> = arrayListOf(),
    var returned: Int? = null
)

data class EventsDTO(
    var available: Int? = null,
    var collectionURI: String? = null,
    var items: ArrayList<ItemsDTO> = arrayListOf(),
    var returned: Int? = null
)

data class UrlsDTO(
    var type: String? = null,
    var url: String? = null
)