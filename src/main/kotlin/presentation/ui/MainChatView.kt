package presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import presentation.viewmodel.ChatMessageType
import utils.ViewModelProvider
import javax.swing.text.View

@Composable
fun MainChatView(navController: NavController) {
    val connectedUsersCount by ViewModelProvider.ChatViewModel.connectedUsersCount.collectAsState(initial = 0)
    val chatList by ViewModelProvider.ChatViewModel.chatsList.collectAsState()


    LaunchedEffect(Unit) {
        while (true) {
            delay(5000)
            ViewModelProvider.ChatViewModel.sendMessage("Hello World!!!")
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("MainChatView")

        Text("$connectedUsersCount")


        LazyColumn {
            items(chatList) {
                when (it.second) {
                    ChatMessageType.RECEIVED -> {
                        Text("${it.first.sender}:${it.first.message}", color = Color.Black)
                    }

                    ChatMessageType.SENT -> {
                        Text("${it.first.sender}:${it.first.message}", color = Color.Blue)
                    }
                }
            }
        }
    }
}