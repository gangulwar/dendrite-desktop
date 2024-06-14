import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import androidx.navigation.compose.rememberNavController
import di.repositoryModule
import di.viewModelModule
import presentation.ui.nav.RootNavController
import org.koin.core.context.startKoin


import presentation.ui.views.chat.MainChatViewPreview

@Composable
@Preview
fun App() {
//    var text by remember { mutableStateOf("Hello, World!") }
//
//    MaterialTheme {
//        Button(onClick = {
//            text = "Hello, Desktop!"
//        }) {
//            Text(text)
//        }
//    }

    RootNavController(rememberNavController())
}

fun main() = application {
    startKoin {
        modules(repositoryModule, viewModelModule)
    }

    val windowState = rememberWindowState()

    windowState.placement = WindowPlacement.Maximized

    Window(
        onCloseRequest = ::exitApplication,
        state = windowState,
        title = "Dendrite"
    ) {
        App()
    }
}
