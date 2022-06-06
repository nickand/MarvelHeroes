package com.example.marvelheroes.data

/**
 * Domain object representing a Marvel character
 */
data class Character(
    var id: Int? = null,
    var name: String? = null,
    var description: String? = null,
    var modified: String? = null,
    var thumbnail: Thumbnail?,
    var resourceURI: String? = null,
    var comics: Comics? = Comics(),
    var series: Series? = Series(),
    var stories: Stories? = Stories(),
    var events: Events? = Events(),
    var urls: ArrayList<Urls> = arrayListOf()
)

data class Thumbnail(
    var path: String? = null,
    var extension: String? = null
)

data class Items(
    var resourceURI: String? = null,
    var name: String? = null
)

data class Comics(
    var available: Int? = null,
    var collectionURI: String? = null,
    var items: ArrayList<Items> = arrayListOf(),
    var returned: Int? = null
)

data class Series(
    var available: Int? = null,
    var collectionURI: String? = null,
    var items: ArrayList<Items> = arrayListOf(),
    var returned: Int? = null
)

data class Stories(
    var available: Int? = null,
    var collectionURI: String? = null,
    var items: ArrayList<Items> = arrayListOf(),
    var returned: Int? = null
)

data class Events(
    var available: Int? = null,
    var collectionURI: String? = null,
    var items: ArrayList<Items> = arrayListOf(),
    var returned: Int? = null
)

data class Urls(
    var type: String? = null,
    var url: String? = null
)