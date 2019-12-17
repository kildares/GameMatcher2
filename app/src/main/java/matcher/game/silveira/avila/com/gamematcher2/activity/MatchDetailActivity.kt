package matcher.game.silveira.avila.com.gamematcher2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import matcher.game.silveira.avila.com.gamematcher2.R
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Match
import matcher.game.silveira.avila.com.gamematcher2.fragment.MatchDetailFragment
import javax.inject.Inject

class MatchDetailActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        val fragment = supportFragmentManager.findFragmentById(R.id.fr_matches) as MatchDetailFragment
        val matchId = intent.extras.getInt(getString(R.string.key_parcelable_match_id))

        fragment.loadPlayers(matchId);

    }
}
