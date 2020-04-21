package silveira.avila.com.gamematcher2.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import silveira.avila.com.gamematcher2.activity.GameMatcherApplication
import silveira.avila.com.gamematcher2.di.module.activity.*
import silveira.avila.com.gamematcher2.di.module.dao.MatchModule
import silveira.avila.com.gamematcher2.di.module.dao.PlayerModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, MainActivityModule::class, MatchModule::class, PlayerModule::class, CreateMatchActivityModule::class, MatchDetailActivityModule::class, PlayerDetailActivityModule::class, PickTeamActivityModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: GameMatcherApplication)
}