package com.edbinns.starwarsapp.utils

fun String.getIdFromURL(): String{
    return if(this.endsWith("/")){
        this.dropLast(1).takeLastWhile { it.isDigit() }
    }else{
        this.takeLastWhile { it.isDigit() }
    }
}