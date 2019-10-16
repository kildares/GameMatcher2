package matcher.game.silveira.avila.com.gamematcher2.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import matcher.game.silveira.avila.com.gamematcher2.fragment.MatchViewModel

@Module
abstract class MatchDetailViewModelModule {

    @Binds
    @IntoMap
    @MatchViewModelKey(MatchViewModel::class)
    abstract fun bindItemViewModel(itemsViewModel: MatchViewModel) : ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory : MatchViewModelFactory) : ViewModelProvider.Factory

}