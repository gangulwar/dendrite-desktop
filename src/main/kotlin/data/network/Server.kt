package data.network

import data.modal.ChatMessage
import data.modal.MessageType
import presentation.viewmodel.User
import java.io.*
import java.net.ServerSocket
import java.net.Socket
import java.util.concurrent.CopyOnWriteArrayList

class ChatServer(private val port: Int, roomName: String) {

    private val clients = CopyOnWriteArrayList<ClientHandler>()
    private val serverSocket = ServerSocket(port)
    private val roomName: String = roomName
    private var nameToAvatar: List<Pair<User, ClientHandler>> = emptyList()

    fun start() {
        println("Server started on port $port")

        while (true) {
            val clientSocket = serverSocket.accept()
            println("Client connected: ${clientSocket.inetAddress.hostAddress}")
            val clientHandler = ClientHandler(clientSocket, this)
            clients.add(clientHandler)
            Thread(clientHandler).start()
        }
    }

    fun broadcast(chatMessage: ChatMessage, sender: ClientHandler) {
        println("${chatMessage.sender}: ${chatMessage.message}")
        val messageBytes = chatMessage.toBytes()
        for (client in clients) {
            if (client != sender) {
                client.sendMessage(messageBytes)
            }
        }
    }

    fun removeClient(client: ClientHandler) {
        clients.remove(client)
    }

    inner class ClientHandler(private val socket: Socket, private val server: ChatServer) : Runnable {
        private val input = DataInputStream(socket.getInputStream())
        private val output = DataOutputStream(socket.getOutputStream())
        private var username: String? = null

        override fun run() {
            try {

                val length = input.readInt()
                val messageBytes = ByteArray(length)
                input.readFully(messageBytes)
                val chatMessage = ChatMessage.fromBytes(messageBytes)
                mapClientNameToSocket(chatMessage)

                while (true) {
                    val length = input.readInt()
                    val messageBytes = ByteArray(length)
                    input.readFully(messageBytes)
                    val chatMessage = ChatMessage.fromBytes(messageBytes)
                    println("Received message: ${chatMessage.message} from ${chatMessage.sender}")
                    server.broadcast(chatMessage, this)
                }
            } catch (e: Exception) {
                server.broadcast(
                    ChatMessage(MessageType.LEAVED, username ?: "Username", "Disconnected"),
                    this
                )
                println("Client disconnected: ${socket.inetAddress.hostAddress}")
            } finally {
                server.removeClient(this)
                socket.close()
                nameToAvatar = nameToAvatar.filterNot { it.second == this }
            }
        }

        private fun mapClientNameToSocket(chatMessage: ChatMessage) {
            username = chatMessage.sender

            server.broadcast(ChatMessage(MessageType.JOINED, username!!, chatMessage.message), this)

            sendMessage(
                ChatMessage(
                    MessageType.ROOM_NAME,
                    username!!,
                    server.roomName
                ).toBytes()
            )

            nameToAvatar += Pair(
                User(
                    username!!,
                    chatMessage.message
                ), this
            )

            nameToAvatar.forEachIndexed { _, user ->
                sendMessage(
                    ChatMessage(
                        MessageType.JOINED,
                        user.first.name,
                        user.first.avatar
                    ).toBytes()
                )
            }

            //            ClientInfo(name, avatarNumber)
//
//
//            val regex = Regex("CLIENT_CONNECTED: (.+)")
//            val matchResult = regex.find(message)
//            val clientName = matchResult?.groups?.get(1)?.value
//            username = clientName
        }

        fun sendMessage(messageBytes: ByteArray) {
            output.writeInt(messageBytes.size)
            output.write(messageBytes)
        }
    }
}

//fun main() {
//    val server = ChatServer(12345)
//    server.start()
//}

