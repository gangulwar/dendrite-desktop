import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
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

    Window(onCloseRequest = ::exitApplication) {
//        App()
        MainChatViewPreview()
    }
}
