package com.example.memefon.domain

import com.example.memefon.data.model.Category
import com.example.memefon.data.model.User

data class MemefonModel (
    var id: String,
    var likesCount: Int,
    var commentsCount: Int,
    var title: String,
    var category: Category,
    var user: User,
    var createdAt: String,
    var videoUrl: String? = "",
    var imageUrl: String? = "",
    var likedByCurrentUser: Int,
    var reportedByCurrentUser: Int
)