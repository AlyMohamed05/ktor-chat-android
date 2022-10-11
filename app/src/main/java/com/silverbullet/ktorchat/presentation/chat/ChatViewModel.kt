package com.silverbullet.ktorchat.presentation.chat

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silverbullet.ktorchat.data.remote.ChatSocketService
import com.silverbullet.ktorchat.data.remote.MessagingService
import com.silverbullet.ktorchat.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val messagingService: MessagingService,
    private val chatSocketService: ChatSocketService,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _messageFieldText = mutableStateOf("")
    val messageFieldText: State<String> = _messageFieldText

    private val _state = mutableStateOf(ChatState())
    val state: State<ChatState> = _state

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent = _toastEvent.asSharedFlow()

    fun connect() {
        getAllMessages()
        val username = savedStateHandle.get<String>("username")?.let { username ->
            viewModelScope.launch {
                val result = chatSocketService.initSession(username)
                when (result) {
                    is Resource.Success -> {
                        chatSocketService
                            .observeMessages()
                            .collect { message ->
                                val newList = _state.value.messages.toMutableList().apply {
                                    add(0, message)
                                }
                                _state.value = _state.value.copy(messages = newList)
                            }
                    }
                    is Resource.Error -> {
                        _toastEvent.emit(result.message ?: "Unknown Error")
                    }
                    is Resource.Loading -> {

                    }
                }
            }
        }
    }

    fun onMessageTextChanged(message: String) {
        _messageFieldText.value = message
    }

    fun disconnect() {
        viewModelScope.launch {
            chatSocketService.closeSession()
        }
    }

    fun getAllMessages() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            val result = messagingService.getAllMessages()
            _state.value = _state.value.copy(
                isLoading = false,
                messages = result
            )
        }
    }

    fun sendMessage() {
        viewModelScope.launch {
            if (_messageFieldText.value.isNotBlank()) {
                chatSocketService.sendMessage(_messageFieldText.value)
                _messageFieldText.value = ""
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disconnect()
    }
}