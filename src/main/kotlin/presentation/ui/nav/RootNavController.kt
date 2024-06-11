package presentation.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import presentation.ui.views.chat.MainChatView
import presentation.ui.views.setup.ProfileView
import presentation.ui.views.setup.RoomOptionsView

enum class Screens(val route: String) {
    StartRoom("START_ROOM_SCREEN"),
    JoinRoom("JOIN_ROOM"),
    Profile("PROFILE"),
    Chat("CHAT_SCREEN"),
}

@Composable
fun RootNavController(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screens.StartRoom.route
    ) {
        composable(route = Screens.StartRoom.route) {
            RoomOptionsView(navController = navController)
        }

        composable(route = Screens.Profile.route) {
            ProfileView(navController = navController)
        }

        composable(route = Screens.Chat.route) {
            MainChatView(navController = navController)
        }
    }

}

