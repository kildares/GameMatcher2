package matcher.game.silveira.avila.com.gamematcher2.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import matcher.game.silveira.avila.com.gamematcher2.CreateMatchActivity
import matcher.game.silveira.avila.com.gamematcher2.MainActivity

@Module
abstract class CreateMatchActivityModule{

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity() : CreateMatchActivity
}