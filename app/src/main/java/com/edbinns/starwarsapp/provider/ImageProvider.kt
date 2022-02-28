package com.edbinns.starwarsapp.provider

import com.edbinns.starwarsapp.models.ImagesResponse
import com.edbinns.starwarsapp.utils.data.*
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageProvider @Inject constructor() {

    suspend fun getFilmImage(name: String) = getImageFromJson(name, getFilmsImages())


    suspend fun getPlanetImage(name: String) = getImageFromJson(name, getPlanetsImages())


    suspend fun getSpeciesImage(name: String) = getImageFromJson(name, getSpeciesImages())
    suspend fun getStartShipsImage(name: String) = getImageFromJson(name, getStartShipsImages())

    suspend fun getVehiclesImage(name: String) = getImageFromJson(name, getVehiclesImages())


    private suspend fun getImageFromJson(name: String, jsonStr: String): String {
        val images = Gson().fromJson(jsonStr, ImagesResponse::class.java)
        val item = images.filter { item -> item.name == name }[0]
        return item.image
    }
}