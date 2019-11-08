package matcher.game.silveira.avila.com.gamematcher2.di.module.activity

import dagger.Module
import dagger.android.ContributesAndroidInjector
import matcher.game.silveira.avila.com.gamematcher2.activity.CreateMatchActivity
import matcher.game.silveira.avila.com.gamematcher2.di.module.FragmentBuildersModule

@Module
abstract class CreateMatchActivityModule{

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity() : CreateMatchActivity
}