package com.example.marvelheroes.data.model

import com.google.gson.annotations.SerializedName

/**
 * Data transfer object to hold the result of a search
 */
data class CharacterListDTO(
    @SerializedName("results")
    val results: List<CharacterDTO>
)