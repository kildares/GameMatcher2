package matcher.game.silveira.avila.com.gamematcher2.activity

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import matcher.game.silveira.avila.com.gamematcher2.di.AppInjector
import javax.inject.Inject

class GameMatcherApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector : DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector;

    override fun onCreate() {
        super.onCreate()

        AppInjector.init(this)
    }
}