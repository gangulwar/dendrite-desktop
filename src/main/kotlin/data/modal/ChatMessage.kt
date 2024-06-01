package data.modal

import java.io.*

data class ChatMessage(val type: MessageType, val sender: String, val message: String) : Serializable {
    fun toBytes(): ByteArray {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val objectOutputStream = ObjectOutputStream(byteArrayOutputStream)
        objectOutputStream.writeObject(this)
        objectOutputStream.flush()
        return byteArrayOutputStream.toByteArray()
    }

    companion object {
        fun fromBytes(bytes: ByteArray): ChatMessage {
            val byteArrayInputStream = ByteArrayInputStream(bytes)
            val objectInputStream = ObjectInputStream(byteArrayInputStream)
            return objectInputStream.readObject() as ChatMessage
        }
    }
}
