package com.edbinns.starwarsapp.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edbinns.starwarsapp.models.CharacterDetail
import com.edbinns.starwarsapp.provider.ImageProvider
import com.edbinns.starwarsapp.repository.CharactersRepository
import com.edbinns.starwarsapp.utils.Constants
import com.edbinns.starwarsapp.utils.Resource
import com.edbinns.starwarsapp.utils.getIdFromURL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: CharactersRepository,
    private val provider: ImageProvider
) : ViewModel() {

    val charactersList: MutableState<List<CharacterDetail>> = mutableStateOf(emptyList())
    private val characterImage: MutableState<String> = mutableStateOf("")
    val isLoading: MutableState<Boolean> = mutableStateOf(false)
    val characterItem: MutableState<CharacterDetail?> = mutableStateOf(null)
    val endReached: MutableState<Boolean> = mutableStateOf(false)
    val planetsImage: MutableState<String> = mutableStateOf("")
    val filmsImages: MutableState<MutableList<String>> = mutableStateOf(mutableListOf())
    val vehiclesImages:  MutableState<MutableList<String>> = mutableStateOf(mutableListOf())
    val starshipImages: MutableState<MutableList<String>> = mutableStateOf(mutableListOf())

    private var curPage = 1
    init {
        getAllCharacters()

    }

     fun getAllCharacters() {

        viewModelScope.launch {
            isLoading.value = true
            when (val response = repository.getAllCharacters(curPage)) {
                is Resource.Success -> {

                    endReached.value = curPage * Constants.PAGE_SIZE >= 82
                    val list = response.data?.map { character ->
                        getCharacterImage(character.name)
                        CharacterDetail(character, characterImage.value)
                    }
                    curPage++
                    isLoading.value = false
                    charactersList.value += list!!

                }
                is Resource.Error -> {
                    isLoading.value = false
                    println(response.message)

                }
            }
        }

    }

    fun searchCharacter(nameCharacter: String){

        viewModelScope.launch {
            when(val response = repository.searchCharacter(nameCharacter)){
                is Resource.Error -> {
                    isLoading.value = false
                    println(response.message)
                }
                is Resource.Success -> {
                    val character = response.data!!
                    getCharacterImage(character.name)
                    characterItem.value = CharacterDetail(character, characterImage.value)
                }
            }
        }

    }

    fun getCharacterPlanet(url: String) {
        viewModelScope.launch {

            val idPlanet = url.getIdFromURL()
            when(val response = repository.getCharacterPlanet(idPlanet.toString())){
                is Resource.Error -> {
                    isLoading.value = false
                    println(response.message)
                }
                is Resource.Success -> {
                    isLoading.value = false
                    planetsImage.value = provider.getPlanetImage(response.data?.name.toString())
                }
            }
        }
    }

    fun getCharacterFilms(urlList: List<String>){
        viewModelScope.launch {

            val list = mutableListOf<String>()
            urlList.forEach{ url ->
                val id = url.getIdFromURL()

                when(val response = repository.getCharacterFilms(id)){
                    is Resource.Error -> {
                        isLoading.value = false
                        println(response.message)
                    }
                    is Resource.Success -> {
                        isLoading.value = false
                        list.add(provider.getFilmImage(response.data?.title.toString()))
                    }
                }
            }

            filmsImages.value = list
        }
    }
    fun getCharacterVehicle(urlList: List<String>){
        viewModelScope.launch {

            val list = mutableListOf<String>()
            urlList.forEach{ url ->
                val id = url.getIdFromURL()

                when(val response = repository.getCharacterVehicles(id)){
                    is Resource.Error -> {
                        isLoading.value = false
                        println(response.message)
                    }
                    is Resource.Success -> {
                        isLoading.value = false
                        list.add(provider.getVehiclesImage(response.data?.name.toString()))
                    }
                }
            }

            vehiclesImages.value = list
        }
    }
    fun getCharacterStarship(urlList: List<String>){
        viewModelScope.launch {

            val list = mutableListOf<String>()
            urlList.forEach{ url ->
                val id = url.getIdFromURL()

                when(val response = repository.getCharacterStarship(id)){
                    is Resource.Error -> {
                        isLoading.value = false
                        println(response.message)
                    }
                    is Resource.Success -> {
                        isLoading.value = false
                        list.add(provider.getStartShipsImage(response.data?.name.toString()))
                    }
                }
            }

            starshipImages.value = list
        }
    }


    private suspend fun getCharacterImage(name: String) {
        when (val response = repository.getCharacterImage(name)) {
            is Resource.Success -> {
                characterImage.value = response.data!!
            }
            is Resource.Error -> {
                println("error ${response.message}")
            }
        }
    }


}