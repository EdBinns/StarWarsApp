package com.edbinns.starwarsapp.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.edbinns.starwarsapp.components.Characters
import com.edbinns.starwarsapp.viewmodel.CharactersViewModel
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun MainScreen(viewModel: CharactersViewModel, navController: NavController){
    Characters(viewModel,navController)
}
