package matcher.game.silveira.avila.com.gamematcher2.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import matcher.game.silveira.avila.com.gamematcher2.GameMatcherApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, MainActivityModule::class, MatchModule::class])
interface ApplicationComponent{

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app : Application) : Builder

        fun build() : ApplicationComponent
    }

    fun inject(app : GameMatcherApplication)
}