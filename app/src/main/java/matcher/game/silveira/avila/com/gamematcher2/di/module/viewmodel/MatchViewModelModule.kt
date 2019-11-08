package matcher.game.silveira.avila.com.gamematcher2.di.module.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import matcher.game.silveira.avila.com.gamematcher2.di.MatchViewModelFactory
import matcher.game.silveira.avila.com.gamematcher2.di.MatchViewModelKey
import matcher.game.silveira.avila.com.gamematcher2.viewmodel.MatchViewModel

@Module
abstract class MatchViewModelModule {

    @Binds
    @IntoMap
    @MatchViewModelKey(MatchViewModel::class)
    abstract fun bindItemViewModel(itemsViewModel: MatchViewModel) : ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory : MatchViewModelFactory) : ViewModelProvider.Factory

}