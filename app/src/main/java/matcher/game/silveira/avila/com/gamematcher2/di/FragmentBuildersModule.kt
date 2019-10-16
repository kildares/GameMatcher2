package matcher.game.silveira.avila.com.gamematcher2.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import matcher.game.silveira.avila.com.gamematcher2.fragment.MatchFragment

@Module
abstract class FragmentBuildersModule{

    @ContributesAndroidInjector
    abstract fun contributeItemsFragment() : MatchFragment

}