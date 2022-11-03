package com.example.memefon.data.model

data class Memefon(
    val data: List<MemefonData?>? = emptyList()
)

data class MemefonData(
    var _id: String? = "",
    var likesCount: Int? = 0,
    var commentsCount: Int? = 0,
    var title: String? = "",
    var category: Category?,
    var user: User? = User("", ""),
    var createdAt: String? = "",
    var videoUrl: String? = "",
    var imageUrl: String? = "",
    var likedByCurrentUser: Int? = 0,
    var reportedByCurrentUser: Int? = 0
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MemefonData

        if (_id != other._id) return false
        if (likesCount != other.likesCount) return false
        if (commentsCount != other.commentsCount) return false
        if (title != other.title) return false
        if (category != other.category) return false
        if (user != other.user) return false
        if (createdAt != other.createdAt) return false
        if (videoUrl != other.videoUrl) return false
        if (imageUrl != other.imageUrl) return false
        if (likedByCurrentUser != other.likedByCurrentUser) return false
        if (reportedByCurrentUser != other.reportedByCurrentUser) return false

        return true
    }

    override fun hashCode(): Int {
        var result = _id?.hashCode() ?: 0
        result = 31 * result + (likesCount ?: 0)
        result = 31 * result + (commentsCount ?: 0)
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (category?.hashCode() ?: 0)
        result = 31 * result + (user?.hashCode() ?: 0)
        result = 31 * result + (createdAt?.hashCode() ?: 0)
        result = 31 * result + (videoUrl?.hashCode() ?: 0)
        result = 31 * result + (imageUrl?.hashCode() ?: 0)
        result = 31 * result + (likedByCurrentUser ?: 0)
        result = 31 * result + (reportedByCurrentUser ?: 0)
        return result
    }
}

data class Category(var name: String? = null)
data class User(
    var username: String? = null,
    var photo: String? = null
)