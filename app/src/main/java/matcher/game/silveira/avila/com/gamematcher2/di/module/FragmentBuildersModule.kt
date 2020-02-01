package matcher.game.silveira.avila.com.gamematcher2.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import matcher.game.silveira.avila.com.gamematcher2.fragment.CreateMatchFragment
import matcher.game.silveira.avila.com.gamematcher2.fragment.MatchDetailFragment
import matcher.game.silveira.avila.com.gamematcher2.fragment.MatchFragment
import matcher.game.silveira.avila.com.gamematcher2.fragment.PlayerDetailFragment

@Module
abstract class FragmentBuildersModule{

    @ContributesAndroidInjector
    abstract fun contributeItemsFragment() : MatchFragment

    @ContributesAndroidInjector
    abstract fun contributeCreateMatchFragment() : CreateMatchFragment

    @ContributesAndroidInjector
    abstract fun contributeMatchDetailFragment() : MatchDetailFragment

    @ContributesAndroidInjector
    abstract fun contributePlayerDetailFragment() : PlayerDetailFragment

}