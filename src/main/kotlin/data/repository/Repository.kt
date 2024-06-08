package data.repository

import androidx.lifecycle.ViewModel
import data.modal.ChatMessage
import presentation.viewmodel.ChatViewModel
import presentation.viewmodel.Message
import presentation.viewmodel.User

interface Repository {
    fun setViewModel(viewModel: ChatViewModel, ip: String, port: Int, repository: Repository)
    fun onUserJoined(chatMessage: ChatMessage)
    fun onChatMessage(chatMessage: ChatMessage)
    fun onLeave(chatMessage: ChatMessage)
    fun onRoomNameRecieved(chatMessage: ChatMessage)
    fun sendMessage(message: String)
}