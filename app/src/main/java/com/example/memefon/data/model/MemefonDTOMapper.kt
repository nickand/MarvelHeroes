package com.example.memefon.data.model

import com.example.memefon.domain.MemefonModel
import com.example.memefon.util.ModelMapper
import javax.inject.Inject

/**
 * Transform @see CharacterDTO to @see Character and the other way around.
 */
class MemefonDTOMapper @Inject constructor(): ModelMapper<Memefon, List<MemefonModel>> {
    override fun mapToModel(model: Memefon): List<MemefonModel> {
        return model.data?.mapNotNull {
            MemefonModel(
                id = it?._id ?: "",
                likesCount = it?.likesCount ?: 0,
                commentsCount = it?.commentsCount ?: 0,
                title = it?.title ?: "",
                category = Category(it?.category?.name),
                user = User(it?.user?.username, it?.user?.photo),
                createdAt = it?.createdAt ?: "",
                videoUrl = it?.videoUrl,
                imageUrl = it?.imageUrl,
                likedByCurrentUser = it?.likedByCurrentUser ?: 0,
                reportedByCurrentUser = it?.reportedByCurrentUser ?: 0
            )
        } ?: emptyList()
    }
}
