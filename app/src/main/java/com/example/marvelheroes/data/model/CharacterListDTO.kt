package com.example.marvelheroes.data.model

import com.google.gson.annotations.SerializedName

/**
 * Data transfer object to hold the result of a search
 */
data class CharacterListDTO(
    @SerializedName("data")
    val data: WrapperResults?
)

data class WrapperResults(
    @SerializedName("results")
    val results: List<CharacterDTO?>?
    )
