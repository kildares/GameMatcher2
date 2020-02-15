package matcher.game.silveira.avila.com.gamematcher2.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import matcher.game.silveira.avila.com.gamematcher2.activity.GameMatcherApplication
import matcher.game.silveira.avila.com.gamematcher2.di.module.activity.CreateMatchActivityModule
import matcher.game.silveira.avila.com.gamematcher2.di.module.activity.MainActivityModule
import matcher.game.silveira.avila.com.gamematcher2.di.module.activity.MatchDetailActivityModule
import matcher.game.silveira.avila.com.gamematcher2.di.module.activity.PlayerDetailActivityModule
import matcher.game.silveira.avila.com.gamematcher2.di.module.dao.MatchModule
import matcher.game.silveira.avila.com.gamematcher2.di.module.dao.PlayerModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, MainActivityModule::class, MatchModule::class, PlayerModule::class, CreateMatchActivityModule::class, MatchDetailActivityModule::class, PlayerDetailActivityModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: GameMatcherApplication)
}