package di

import data.repository.ClientRepository
import data.repository.Repository
import org.koin.dsl.module
import presentation.viewmodel.ChatViewModel

val repositoryModule = module {
    single<Repository> { ClientRepository() }
//    single<Repository> { ServerRepository() }
}

val viewModelModule = module {
    single { ChatViewModel(get()) }
}
