package matcher.game.silveira.avila.com.gamematcher2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class CreateMatchActivity : AppCompatActivity() {


    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_match)
    }
}
