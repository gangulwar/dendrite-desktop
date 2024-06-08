package data.network

import data.modal.ChatMessage
import data.modal.MessageType
import data.repository.Repository
import presentation.viewmodel.Message
import java.io.*
import java.net.Socket

class ChatClient(
    private val host: String,
    private val port: Int,
    private val repository: Repository
) {

    private lateinit var socket: Socket
    private lateinit var input: DataInputStream
    private lateinit var output: DataOutputStream
    private var username: String? = null
    private lateinit var ROOM_NAME: String

    fun start(name: String, avatar: String) {
        println("start() of Client")
        socket = Socket(host, port)
        input = DataInputStream(socket.getInputStream())
        output = DataOutputStream(socket.getOutputStream())

//        println("Connected to the server at $host:$port")
//
//        print("Enter your name: ")
//        username = BufferedReader(InputStreamReader(System.`in`)).readLine()
//        val avatar = BufferedReader(InputStreamReader(System.`in`)).readLine().toInt()
//
//        println("Welcome, $username!")

        username = name
        sendMessage("$avatar", MessageType.JOINED)

        Thread { receiveMessages() }.start()

//        val consoleReader = BufferedReader(InputStreamReader(System.`in`))
//        try {
//            var inputMessage: String?
//            while (consoleReader.readLine().also { inputMessage = it } != null) {
//                sendMessage(inputMessage!!)
//            }
//        } catch (e: Exception) {
//            println("Error reading from console")
//        }

    }

    private fun receiveMessages() {
        try {
            while (true) {
                val length = input.readInt()
                val messageBytes = ByteArray(length)
                input.readFully(messageBytes)
                val chatMessage = ChatMessage.fromBytes(messageBytes)

                when (chatMessage.type) {
                    MessageType.CHAT -> {
                        handelChatMessage(chatMessage)
                        repository.onChatMessage(chatMessage)
                    }

                    MessageType.JOINED -> {
                        handelJoinMessage(chatMessage)
                        repository.onUserJoined(chatMessage)
                    }

                    MessageType.LEAVED -> {
                        handelLeaveMessage(chatMessage)
                        repository.onLeave(chatMessage)
                    }

                    MessageType.ROOM_NAME -> {
                        handelRoomName(chatMessage.message)
                        repository.onRoomNameRecieved(chatMessage)
                    }
                }

                println("${chatMessage.sender}: ${chatMessage.message}")
            }
        } catch (e: Exception) {
            sendMessage("Internal Network Error", MessageType.LEAVED)
            println("Disconnected from the server")
        } finally {
            socket.close()
        }
    }

    fun sendMessage(message: String, types: MessageType = MessageType.CHAT) {
        val chatMessage: ChatMessage
        var messageBytes: ByteArray = ByteArray(0)
        when (types) {
            MessageType.CHAT -> {
                chatMessage = ChatMessage(MessageType.CHAT, username ?: "Unknown", message)
            }

            MessageType.LEAVED -> {
                chatMessage = ChatMessage(MessageType.LEAVED, username!!, message)
            }

            MessageType.JOINED -> {
                chatMessage = ChatMessage(MessageType.JOINED, username!!, message)
            }

            MessageType.ROOM_NAME -> TODO()
        }

        messageBytes = chatMessage.toBytes()
        output.writeInt(messageBytes.size)
        output.write(messageBytes)
    }

    private fun handelChatMessage(chatMessage: ChatMessage) {
        println("handelChatMessage()")
    }

    private fun handelJoinMessage(chatMessage: ChatMessage) {
        //TODO
        println("handelJoinMessage()")
    }

    private fun handelRoomName(roomName: String) {
        println("Room Name: $roomName")
        println("handelRoomName()")
    }

    private fun handelLeaveMessage(chatMessage: ChatMessage) {
        //TODO
        println("handelLeaveMessage()")
    }
}
