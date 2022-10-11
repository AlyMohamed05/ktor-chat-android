package com.silverbullet.ktorchat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.silverbullet.ktorchat.presentation.chat.ChatScreen
import com.silverbullet.ktorchat.presentation.username.UsernameScreen

@Composable
fun KtorChatNavHost(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.UsernameScreen.route) {

        composable(Screen.UsernameScreen.route) {
            UsernameScreen(
                onNavigate = { route ->
                    navController
                        .navigate(route) {
                            popUpTo(Screen.UsernameScreen.route) { inclusive = true }
                        }
                }
            )
        }

        composable(
            Screen.ChatScreen.route + "/{username}",
            arguments = listOf(
                navArgument("username") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) {
            val username = it.arguments?.getString("username")
            ChatScreen(username)
        }

    }

}