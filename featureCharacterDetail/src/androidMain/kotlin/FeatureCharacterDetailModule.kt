import com.jetbrains.kmpapp.CharacterDetailViewModel
import com.jetbrains.kmpapp.di.domainModule
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureCharacterDetailModule = module {
    includes(domainModule)
    viewModel { param ->
        CharacterDetailViewModel(characterId = param.get(), get(), get())
    }
}