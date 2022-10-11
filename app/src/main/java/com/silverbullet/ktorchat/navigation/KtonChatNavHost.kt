package com.silverbullet.ktorchat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.silverbullet.ktorchat.presentation.username.UsernameScreen

@Composable
fun KtorChatNavHost(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.UsernameScreen.route) {

        composable(Screen.UsernameScreen.route) {
            UsernameScreen(
                onNavigate = { route ->
                    navController.navigate(route)
                }
            )
        }

    }

}