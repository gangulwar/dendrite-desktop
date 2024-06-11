package presentation.viewmodel

import androidx.lifecycle.ViewModel
import data.repository.Repository
import di.MyKoinComponent
import kotlinx.coroutines.flow.*
import utils.CurrentUser
import utils.ViewModelProvider
import java.time.LocalDateTime

data class Message(val sender: User, val message: String, val time: LocalDateTime)
data class User(val name: String, val avatar: String)

enum class ChatMessageType {
    SENT,
    RECEIVED,
    JOINED,
    LEAVED
}


class ChatViewModel(private val repository: Repository) : ViewModel() {

    private val _roomName = MutableStateFlow<String>("")
    val roomName: StateFlow<String> = _roomName

    private val _chatsList = MutableStateFlow<List<Pair<Message, ChatMessageType>>>(emptyList())
    val chatsList: StateFlow<List<Pair<Message, ChatMessageType>>> = _chatsList

    private val _connectedUsers = MutableStateFlow<List<User>>(emptyList())
    val connectedUsers: StateFlow<List<User>> = _connectedUsers

    val connectedUsersCount: Flow<Int> = _connectedUsers.map { it.size }

    init {
        println("ViewModel Setup")
    }

    fun setUpViewModel(ip: String, port: Int) {
        repository.setViewModel(MyKoinComponent().chatViewModel, ip, port, repository)
    }

    fun addMessage(message: Message) {
        _chatsList.value += Pair(message, ChatMessageType.RECEIVED)

        println("add Message() from ViewModel")
        for (a in _chatsList.value) {
            println("${a.first.sender}:${a.second}")
        }
    }

    fun newUserJoined(user: User) {
        println(user.name)
        _connectedUsers.value += user
    }

    fun userLeaved(user: User) {
        _connectedUsers.value -= user
    }

    fun setRoomName(name: String) {
        _roomName.value = name
        CurrentUser.roomName = name
    }

    fun sendMessage(message: String) {
        repository.sendMessage(message)
        _chatsList.value += Pair(
            Message(
                User(
                    CurrentUser.name,
                    CurrentUser.avatar
                ),
                message,
                LocalDateTime.now()
            ), ChatMessageType.SENT
        )
    }
}
