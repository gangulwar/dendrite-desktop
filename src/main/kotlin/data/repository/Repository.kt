package data.repository

interface Repository {
    fun onUserJoined()
    fun onChatMessage()
    fun onLeave()
}