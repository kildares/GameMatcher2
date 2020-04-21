package silveira.avila.com.gamematcher2.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import silveira.avila.com.gamematcher2.match.fragment.CreateMatchFragment
import silveira.avila.com.gamematcher2.match.fragment.MatchDetailFragment
import silveira.avila.com.gamematcher2.match.fragment.MatchFragment
import silveira.avila.com.gamematcher2.pick.fragment.PickTeamFragment
import silveira.avila.com.gamematcher2.player.PlayerDetailFragment

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

    @ContributesAndroidInjector
    abstract fun contributePickTeamFragment() : PickTeamFragment

}