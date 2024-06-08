package data.repository

import androidx.lifecycle.ViewModel
import data.modal.ChatMessage
import data.network.ChatClient
import presentation.viewmodel.ChatViewModel
import presentation.viewmodel.Message
import presentation.viewmodel.User
import utils.Avatars
import utils.CurrentUser
import java.time.LocalDateTime

class ClientRepository : Repository {
    private lateinit var viewModel: ChatViewModel
    private lateinit var chatClient: ChatClient
    private var nameToAvatar: List<User> = emptyList()
    //name to avatar


    init {
        println("ClientRepository Setup")
    }

    override fun setViewModel(viewModel: ChatViewModel, ip: String, port: Int, repository: Repository) {
        this.viewModel = viewModel
        chatClient = ChatClient(ip, port, repository)
        chatClient.start(CurrentUser.name, CurrentUser.avatar)
        println("setViewModel() of ClientRepository")
    }

    override fun onUserJoined(chatMessage: ChatMessage) {

        val user = User(
            chatMessage.sender,
            Avatars.avatarList[chatMessage.message.toInt()]
        )

        nameToAvatar += user

        viewModel.newUserJoined(
            user
        )
    }

    override fun onChatMessage(chatMessage: ChatMessage) {

//        nameToAvatar.find { it.name == chatMessage.sender }?.let {
//            Message(
//                it,
//                chatMessage.message,
//                LocalDateTime.now()
//            )
//        }?.let {
//            viewModel.addMessage(
//                it
//            )
//        }

        var avatar = nameToAvatar.find {
            it.name == chatMessage.sender
        }

        if (avatar != null) {
            viewModel.addMessage(
                Message(
                    User(chatMessage.sender, avatar.avatar),
                    chatMessage.message,
                    LocalDateTime.now()
                )
            )
        }
    }

    override fun onLeave(chatMessage: ChatMessage) {
        nameToAvatar.find { it.name == chatMessage.sender }?.let {
            viewModel.userLeaved(
                it
            )
        }
    }

    override fun onRoomNameRecieved(chatMessage: ChatMessage) {
        viewModel.setRoomName(chatMessage.message)
    }

    override fun sendMessage(message: String) {
        chatClient.sendMessage(message)
    }
}