package com.fernando.ku.movieapp.ui_ktx

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

fun addArguments(vararg arguments: Pair<String, NavType<*>>): List<NamedNavArgument> {
    return arguments.map { (argument, navType) ->
        navArgument(argument) {
            type = navType
        }
    }
}