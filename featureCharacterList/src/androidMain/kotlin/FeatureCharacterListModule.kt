import com.jetbrains.kmpapp.di.domainModule
import com.jetbrains.kmpapp.vm.CharacterListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureCharacterListModule = module {
    includes(domainModule)
    viewModel {
        CharacterListViewModel(get())
    }
}