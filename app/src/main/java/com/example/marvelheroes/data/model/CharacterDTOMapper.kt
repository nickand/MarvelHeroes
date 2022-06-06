package com.example.marvelheroes.data.model

import com.example.marvelheroes.data.*
import com.example.marvelheroes.util.ModelMapper

/**
 * Transform @see CharacterDTO to @see Character and the other way around.
 */
class CharacterDTOMapper : ModelMapper<CharacterDTO, Character> {
    override fun mapToModel(model: CharacterDTO): Character {
        return Character(
            id = model.id,
            name = model.name,
            description = model.description,
            modified = model.modified,
            thumbnail = Thumbnail(model.thumbnail?.path, model.thumbnail?.extension),
            resourceURI = model.resourceURI,
            comics = Comics(
                model.comics?.available, model.comics?.collectionURI,
                arrayListOf(
                    Items(
                        model.comics?.items?.get(0)?.resourceURI,
                        model.comics?.items?.get(0)?.name
                    )
                ),
                model.comics?.returned
            ),
            series = Series(
                model.series?.available, model.series?.collectionURI,
                arrayListOf(
                    Items(
                        model.series?.items?.get(0)?.resourceURI,
                        model.series?.items?.get(0)?.name
                    )
                )
            ),
            stories = Stories(
                model.stories?.available,
                model.stories?.collectionURI,
                arrayListOf(
                    Items(
                        model.stories?.items?.get(0)?.resourceURI,
                        model.stories?.items?.get(0)?.name
                    )
                ),
                model.stories?.returned
            ),
            events = Events(
                model.events?.available,
                model.events?.collectionURI,
                arrayListOf(
                    Items(
                        model.events?.items?.get(0)?.resourceURI,
                        model.events?.items?.get(0)?.name
                    )
                ),
                model.events?.returned
            ),
            urls = arrayListOf(
                Urls(
                    model.urls[0].type,
                    model.urls[0].url
                )
            )
        )
    }

    override fun mapFromModel(domainModel: Character): CharacterDTO {
        return CharacterDTO(
            id = domainModel.id,
            name = domainModel.name,
            description = domainModel.description,
            modified = domainModel.modified,
            thumbnail = ThumbnailDTO(domainModel.thumbnail?.path, domainModel.thumbnail?.extension),
            resourceURI = domainModel.resourceURI,
            comics = ComicsDTO(
                domainModel.comics?.available, domainModel.comics?.collectionURI,
                arrayListOf(
                    ItemsDTO(
                        domainModel.comics?.items?.get(0)?.resourceURI,
                        domainModel.comics?.items?.get(0)?.name
                    )
                ),
                domainModel.comics?.returned
            ),
            series = SeriesDTO(
                domainModel.series?.available, domainModel.series?.collectionURI,
                arrayListOf(
                    ItemsDTO(
                        domainModel.series?.items?.get(0)?.resourceURI,
                        domainModel.series?.items?.get(0)?.name
                    )
                )
            ),
            stories = StoriesDTO(
                domainModel.stories?.available,
                domainModel.stories?.collectionURI,
                arrayListOf(
                    ItemsDTO(
                        domainModel.stories?.items?.get(0)?.resourceURI,
                        domainModel.stories?.items?.get(0)?.name
                    )
                ),
                domainModel.stories?.returned
            ),
            events = EventsDTO(
                domainModel.events?.available,
                domainModel.events?.collectionURI,
                arrayListOf(
                    ItemsDTO(
                        domainModel.events?.items?.get(0)?.resourceURI,
                        domainModel.events?.items?.get(0)?.name
                    )
                ),
                domainModel.events?.returned
            ),
            urls = arrayListOf(
                UrlsDTO(
                    domainModel.urls[0].type,
                    domainModel.urls[0].url
                )
            )
        )
    }
}