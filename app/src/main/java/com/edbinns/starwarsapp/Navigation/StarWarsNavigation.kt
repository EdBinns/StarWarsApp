package com.edbinns.starwarsapp.Navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.edbinns.starwarsapp.screens.DetailScreen
import com.edbinns.starwarsapp.screens.MainScreen
import com.edbinns.starwarsapp.viewmodel.CharactersViewModel

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun StarWarsNavigation(viewModel: CharactersViewModel = hiltViewModel()) {
    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = StarWarsScreens.MainScreen.name
    ) {
        composable(StarWarsScreens.MainScreen.name) {

            MainScreen(viewModel = viewModel, navController = navController)
        }


        composable(StarWarsScreens.DetailScreen.name) {
//            val json = backStackEntry.arguments?.getString("character")
//            val character = Gson().fromJson(json, CharacterDetail::class.java)
//            println("character $character")
            DetailScreen( viewModel = viewModel)
        }

    }
}