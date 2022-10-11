package com.silverbullet.ktorchat.navigation

sealed class Screen(val route: String){

    object UsernameScreen: Screen("username_screen_route")
    object ChatScreen: Screen("chat_screen_route")
}
