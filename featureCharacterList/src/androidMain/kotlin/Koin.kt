import com.jetbrains.kmpapp.di.dataModule
import com.jetbrains.kmpapp.di.domainModule
import com.jetbrains.kmpapp.di.useCases.GetCharacterPageUseCase
import com.jetbrains.kmpapp.vm.CharacterListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureCharacterListModule = module {
    includes(domainModule)
    viewModel {
        CharacterListViewModel(get())
    }
}