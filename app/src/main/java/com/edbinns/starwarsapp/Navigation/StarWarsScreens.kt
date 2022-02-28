package com.edbinns.starwarsapp.Navigation

import java.lang.IllegalArgumentException

enum class StarWarsScreens {
    MainScreen,
    DetailScreen;

    companion object{
        fun fromRoute(route: String?): StarWarsScreens =
            when(route?.substringBefore("/")){
                MainScreen.name -> MainScreen
                DetailScreen.name -> DetailScreen
                null -> MainScreen
                else -> throw  IllegalArgumentException("Route $route is not recognize")
            }
    }
}