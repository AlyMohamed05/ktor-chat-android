package com.silverbullet.ktorchat.data.remote

import com.silverbullet.ktorchat.domain.model.Message
import com.silverbullet.ktorchat.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ChatSocketService {

    suspend fun initSession(
        username: String
    ): Resource<Unit>

    suspend fun sendMessage(message: String)

    fun observeMessages(): Flow<Message>

    suspend fun closeSession()

    companion object {
        const val BASE_URL = "ws://192.168.1.6:8080"
    }

    sealed class EndPoints(val url: String) {

        object ChatSocketRoute : EndPoints("$BASE_URL/chat-socket")
    }
}