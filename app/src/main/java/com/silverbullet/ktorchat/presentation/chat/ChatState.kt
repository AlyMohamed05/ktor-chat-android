package com.silverbullet.ktorchat.presentation.chat

import com.silverbullet.ktorchat.domain.model.Message

data class ChatState(
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false,
)
