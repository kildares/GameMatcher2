package silveira.avila.com.gamematcher2.di.module.activity

import dagger.Module
import dagger.android.ContributesAndroidInjector
import silveira.avila.com.gamematcher2.match.CreateMatchActivity
import silveira.avila.com.gamematcher2.di.module.FragmentBuildersModule

@Module
abstract class CreateMatchActivityModule{

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity() : CreateMatchActivity
}