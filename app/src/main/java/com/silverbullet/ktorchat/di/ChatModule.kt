package com.silverbullet.ktorchat.di

import com.silverbullet.ktorchat.data.remote.ChatSocketService
import com.silverbullet.ktorchat.data.remote.ChatSocketServiceImpl
import com.silverbullet.ktorchat.data.remote.MessagingService
import com.silverbullet.ktorchat.data.remote.MessagingServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ChatModule {

    @Binds
    @Singleton
    abstract fun bindChatSocketService(chatSocketServiceImpl: ChatSocketServiceImpl): ChatSocketService

    @Binds
    @Singleton
    abstract fun bindMessagingService(messagingServiceImpl: MessagingServiceImpl): MessagingService
}