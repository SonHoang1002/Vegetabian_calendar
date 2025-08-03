package com.hts.vegetabiancalendar.route

sealed class MyRoute(val route: String) {
    data object Home : MyRoute("home")
    data object Profile : MyRoute("profile")
    data object Settings : MyRoute("setting")
}