package com.example.marvelheroes.data.model

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
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Character

        if (id != other.id) return false
        if (name != other.name) return false
        if (description != other.description) return false
        if (modified != other.modified) return false
        if (thumbnail != other.thumbnail) return false
        if (resourceURI != other.resourceURI) return false
        if (comics != other.comics) return false
        if (series != other.series) return false
        if (stories != other.stories) return false
        if (events != other.events) return false
        if (urls != other.urls) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (modified?.hashCode() ?: 0)
        result = 31 * result + (thumbnail?.hashCode() ?: 0)
        result = 31 * result + (resourceURI?.hashCode() ?: 0)
        result = 31 * result + (comics?.hashCode() ?: 0)
        result = 31 * result + (series?.hashCode() ?: 0)
        result = 31 * result + (stories?.hashCode() ?: 0)
        result = 31 * result + (events?.hashCode() ?: 0)
        result = 31 * result + urls.hashCode()
        return result
    }
}

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
