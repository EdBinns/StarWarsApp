package com.edbinns.starwarsapp.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.edbinns.starwarsapp.components.DetailCharacter
import com.edbinns.starwarsapp.viewmodel.CharactersViewModel

@Composable
fun DetailScreen( viewModel: CharactersViewModel) {

    val characterDetail =viewModel.characterItem.value

    viewModel.getCharacterPlanet(characterDetail?.character?.homeworld.toString())
    val planetsImage by remember {
        viewModel.planetsImage
    }


    characterDetail?.character?.films?.let { viewModel.getCharacterFilms(it) }
    val films by remember {
        viewModel.filmsImages
    }

    characterDetail?.character?.vehicles?.let { viewModel.getCharacterVehicle(it) }
    val vehicles by remember {
        viewModel.vehiclesImages
    }

    characterDetail?.character?.starships?.let { viewModel.getCharacterStarship(it) }
    val starships by remember {
        viewModel.starshipImages
    }

    characterDetail?.let {
        DetailCharacter(
            characterDetail = characterDetail,
            homeworldList = mutableListOf(planetsImage),
            films = films,
            vehicles = vehicles,
            starships = starships
        )
    }
}