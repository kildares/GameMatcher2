package silveira.avila.com.gamematcher2.di.module.activity

import dagger.Module
import dagger.android.ContributesAndroidInjector
import silveira.avila.com.gamematcher2.activity.MainActivity
import silveira.avila.com.gamematcher2.di.module.FragmentBuildersModule

@Module
abstract class MainActivityModule{

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity() : MainActivity
}