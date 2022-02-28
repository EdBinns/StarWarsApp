package com.edbinns.starwarsapp.Services

import com.edbinns.starwarsapp.models.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url


interface StartWarsApiServices {

    @GET("people/")
    suspend fun getAlCharacters(@Query("page") page : Int) : Response<CharactersResponse>


    @GET("people/")
    suspend fun searchCharacter(@Query("search") search : String) : Response<CharactersResponse>

    @GET("planets/{id}/")
    suspend fun getCharacterPlanet(@Path("id") id: String,) : Response<Planet>

    @GET("films/{id}/")
    suspend fun getCharacterFilms(@Path("id") id: String,) : Response<Film>

    @GET("vehicles/{id}/")
    suspend fun getCharacterVehicles(@Path("id") id: String,) : Response<Vehicle>

    @GET("starships/{id}/")
    suspend fun getCharacterStarship(@Path("id") id: String,) : Response<Starship>


    @GET
    suspend fun getCharacterImage(@Url url: String?): Response<CharacterImageResponse>

}