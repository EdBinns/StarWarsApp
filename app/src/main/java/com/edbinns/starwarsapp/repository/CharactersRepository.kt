package com.edbinns.starwarsapp.repository

import com.edbinns.starwarsapp.Services.StartWarsApiServices
import com.edbinns.starwarsapp.models.*
import com.edbinns.starwarsapp.utils.Constants.CHARACTER_IMAGE_BASE_URL
import com.edbinns.starwarsapp.utils.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharactersRepository @Inject constructor(private val apiServices: StartWarsApiServices) {


    ///Opcion 1 pagination
    //https://blog.mindorks.com/pagination-in-jetpack-compose

    //opcion 2
    //https://www.youtube.com/watch?v=jrIfGAk8PyQ
    suspend fun getAllCharacters(page: Int): Resource<List<Character>> {

        val response = apiServices.getAlCharacters(page)
        if (!(response.isSuccessful) || (response.code() != 200))
            return Resource.Error("Error loading data")

        val responseList = response.body()?.characters
        return if (!responseList.isNullOrEmpty()) {
            Resource.Success(responseList)

        } else {
            Resource.Error("Error loading data x2")
        }
    }

    suspend fun searchCharacter(characterName : String): Resource<Character>{
        val response = apiServices.searchCharacter(characterName)
        if (!(response.isSuccessful) || (response.code() != 200))
            return Resource.Error("Error loading data")

        val responseList = response.body()?.characters
        return if (!responseList.isNullOrEmpty()) {
            Resource.Success(responseList[0])

        } else {
            Resource.Error("Error loading data x2")
        }

    }

    suspend fun getCharacterPlanet(idPlanet: String): Resource<Planet>{

        val response = apiServices.getCharacterPlanet(idPlanet)
        if (!(response.isSuccessful) || (response.code() != 200))
            return Resource.Error("Error loading data")

        val planet = response.body()!!
        return Resource.Success(planet)
    }
    suspend fun getCharacterFilms(idFilm: String): Resource<Film>{

        val response = apiServices.getCharacterFilms(idFilm)
        if (!(response.isSuccessful) || (response.code() != 200))
            return Resource.Error("Error loading data")

        val film = response.body()!!
        return Resource.Success(film)
    }
    suspend fun getCharacterVehicles(idVehicle: String): Resource<Vehicle>{

        val response = apiServices.getCharacterVehicles(idVehicle)
        if (!(response.isSuccessful) || (response.code() != 200))
            return Resource.Error("Error loading data")

        val value = response.body()!!
        return Resource.Success(value)
    }
    suspend fun getCharacterStarship(idStarship: String): Resource<Starship>{

        val response = apiServices.getCharacterStarship(idStarship)
        if (!(response.isSuccessful) || (response.code() != 200))
            return Resource.Error("Error loading data")

        val value = response.body()!!
        return Resource.Success(value)
    }
    suspend fun getCharacterImage(name : String) : Resource<String>{
        val response = apiServices.getCharacterImage("$CHARACTER_IMAGE_BASE_URL/characters/$name")
        if (!(response.isSuccessful) || (response.code() != 200))
            return Resource.Error("Error loading data")

        val result = response.body()?.data?.image_url
        return if (!result.isNullOrEmpty()) {
            Resource.Success(result)

        } else {
            Resource.Error("Error loading data x2")
        }
    }
}