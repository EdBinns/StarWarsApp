package com.edbinns.starwarsapp.models

import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val characters: List<Character>
)