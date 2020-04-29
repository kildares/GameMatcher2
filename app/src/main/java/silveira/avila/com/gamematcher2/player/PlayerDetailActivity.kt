package silveira.avila.com.gamematcher2.player

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import silveira.avila.com.gamematcher2.R
import silveira.avila.com.gamematcher2.db.entities.Match
import silveira.avila.com.gamematcher2.db.entities.Player
import javax.inject.Inject

class PlayerDetailActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        val player = intent.extras?.getParcelable<Player>(getString(R.string.key_parcelable_player))
        val match = intent.extras?.getParcelable<Match>(getString(R.string.key_parcelable_match))
        val fragment = supportFragmentManager.findFragmentById(R.id.fr_player_detail) as PlayerDetailFragment
        fragment.loadPlayer(player, match!!);

    }
}
