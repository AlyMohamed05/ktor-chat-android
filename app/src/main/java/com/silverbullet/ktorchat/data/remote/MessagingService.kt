package com.silverbullet.ktorchat.data.remote

import com.silverbullet.ktorchat.domain.model.Message

interface MessagingService {

    suspend fun getAllMessages(): List<Message>

    companion object {
        const val BASE_URL = "http://192.168.1.6:8080"
    }

    sealed class EndPoints(val url: String) {

        object GetAllMessages : EndPoints("$BASE_URL/messages")
    }

}