package com.example.marvelheroes.data.model

import com.example.marvelheroes.util.ModelMapper
import javax.inject.Inject

/**
 * Transform @see CharacterDTO to @see Character and the other way around.
 */
class CharacterDTOMapper @Inject constructor(): ModelMapper<CharacterDTO, Character> {
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
                        model.comics?.items?.firstOrNull()?.resourceURI,
                        model.comics?.items?.firstOrNull()?.name
                    )
                ),
                model.comics?.returned
            ),
            series = Series(
                model.series?.available, model.series?.collectionURI,
                arrayListOf(
                    Items(
                        model.series?.items?.firstOrNull()?.resourceURI,
                        model.series?.items?.firstOrNull()?.name
                    )
                )
            ),
            stories = Stories(
                model.stories?.available,
                model.stories?.collectionURI,
                arrayListOf(
                    Items(
                        model.stories?.items?.firstOrNull()?.resourceURI,
                        model.stories?.items?.firstOrNull()?.name
                    )
                ),
                model.stories?.returned
            ),
            events = Events(
                model.events?.available,
                model.events?.collectionURI,
                arrayListOf(
                    Items(
                        model.events?.items?.firstOrNull()?.resourceURI,
                        model.events?.items?.firstOrNull()?.name
                    )
                ),
                model.events?.returned
            ),
            urls = arrayListOf(
                Urls(
                    model.urls?.firstOrNull()?.type,
                    model.urls?.firstOrNull()?.url
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
                        domainModel.comics?.items?.firstOrNull()?.resourceURI,
                        domainModel.comics?.items?.firstOrNull()?.name
                    )
                ),
                domainModel.comics?.returned
            ),
            series = SeriesDTO(
                domainModel.series?.available, domainModel.series?.collectionURI,
                arrayListOf(
                    ItemsDTO(
                        domainModel.series?.items?.firstOrNull()?.resourceURI,
                        domainModel.series?.items?.firstOrNull()?.name
                    )
                )
            ),
            stories = StoriesDTO(
                domainModel.stories?.available,
                domainModel.stories?.collectionURI,
                arrayListOf(
                    ItemsDTO(
                        domainModel.stories?.items?.firstOrNull()?.resourceURI,
                        domainModel.stories?.items?.firstOrNull()?.name
                    )
                ),
                domainModel.stories?.returned
            ),
            events = EventsDTO(
                domainModel.events?.available,
                domainModel.events?.collectionURI,
                arrayListOf(
                    ItemsDTO(
                        domainModel.events?.items?.firstOrNull()?.resourceURI,
                        domainModel.events?.items?.firstOrNull()?.name
                    )
                ),
                domainModel.events?.returned
            ),
            urls = arrayListOf(
                UrlsDTO(
                    domainModel.urls.firstOrNull()?.type,
                    domainModel.urls.firstOrNull()?.url
                )
            )
        )
    }
}
