package di

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import presentation.viewmodel.ChatViewModel

class MyKoinComponent : KoinComponent {
    val chatViewModel: ChatViewModel by inject()
}
