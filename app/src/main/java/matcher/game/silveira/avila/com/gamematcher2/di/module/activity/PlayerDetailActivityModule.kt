package matcher.game.silveira.avila.com.gamematcher2.di.module.activity

import dagger.Module
import dagger.android.ContributesAndroidInjector
import matcher.game.silveira.avila.com.gamematcher2.player.PlayerDetailActivity
import matcher.game.silveira.avila.com.gamematcher2.di.module.FragmentBuildersModule

@Module
abstract class PlayerDetailActivityModule{

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity() : PlayerDetailActivity

}

