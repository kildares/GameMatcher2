package matcher.game.silveira.avila.com.gamematcher2.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import matcher.game.silveira.avila.com.gamematcher2.MainActivity

@Module
abstract class CreateMatchActivity{

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity() : MainActivity
}