package com.silverbullet.ktorchat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.silverbullet.ktorchat.navigation.KtorChatNavHost
import com.silverbullet.ktorchat.ui.theme.KtorChatTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KtorChatTheme {
                val navController = rememberNavController()
                KtorChatNavHost(navController = navController)
            }
        }
    }
}