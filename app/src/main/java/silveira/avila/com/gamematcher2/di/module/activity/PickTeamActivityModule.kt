package silveira.avila.com.gamematcher2.di.module.activity

import dagger.Module
import dagger.android.ContributesAndroidInjector
import silveira.avila.com.gamematcher2.pick.PickTeamActivity
import silveira.avila.com.gamematcher2.di.module.FragmentBuildersModule

@Module
abstract class PickTeamActivityModule{

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity() : PickTeamActivity

}

