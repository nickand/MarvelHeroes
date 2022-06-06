package com.example.marvelheroes.data.model

/**
 * Data transfer objects to hold the details of a Character
 */
data class CharacterDTO(
    var id: Int? = null,
    var name: String? = null,
    var description: String? = null,
    var modified: String? = null,
    var thumbnail: ThumbnailDTO? = ThumbnailDTO(),
    var resourceURI: String? = null,
    var comics: ComicsDTO? = ComicsDTO(),
    var series: SeriesDTO? = SeriesDTO(),
    var stories: StoriesDTO? = StoriesDTO(),
    var events: EventsDTO? = EventsDTO(),
    var urls: ArrayList<UrlsDTO> = arrayListOf()
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