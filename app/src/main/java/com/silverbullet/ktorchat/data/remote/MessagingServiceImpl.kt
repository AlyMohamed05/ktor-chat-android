package com.silverbullet.ktorchat.data.remote

import com.silverbullet.ktorchat.data.mappers.toMessage
import com.silverbullet.ktorchat.data.remote.dto.MessageDto
import com.silverbullet.ktorchat.domain.model.Message
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class MessagingServiceImpl(
    private val client: HttpClient
) : MessagingService {

    override suspend fun getAllMessages(): List<Message> {
        return try {
            val messages: List<MessageDto> =
                client.get(MessagingService.EndPoints.GetAllMessages.url).body()
            messages.map { it.toMessage() }
        } catch (e: Exception) {
            emptyList()
        }
    }
}