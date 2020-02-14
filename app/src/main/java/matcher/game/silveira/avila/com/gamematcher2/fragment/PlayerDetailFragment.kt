package matcher.game.silveira.avila.com.gamematcher2.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders

import matcher.game.silveira.avila.com.gamematcher2.R
import matcher.game.silveira.avila.com.gamematcher2.activity.MatchDetailActivity
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Player
import matcher.game.silveira.avila.com.gamematcher2.di.Injectable
import matcher.game.silveira.avila.com.gamematcher2.di.MatchViewModelFactory
import matcher.game.silveira.avila.com.gamematcher2.viewmodel.PlayerViewModel
import javax.inject.Inject


class PlayerDetailFragment : Fragment(), Injectable {

    private lateinit var mConfirmButton : Button
    private lateinit var mPlayerName : EditText
    private lateinit var mPlayerPosition : EditText
    private var mMatchId : Int = 0
    private var mPlayer : Player? = null

    private lateinit var mPlayerViewModel : PlayerViewModel

    @Inject
    lateinit var viewModelFactory : MatchViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_player_detail, container, false)

        mConfirmButton = view.findViewById(R.id.bt_player_detail_confirm)
        mPlayerName = view.findViewById(R.id.et_detail_player_name)
        mPlayerPosition = view.findViewById(R.id.et_detail_player_position)

        mConfirmButton.setOnClickListener{
            if(validPlayerInfo()){

                if(mPlayer == null){
                    mPlayerViewModel.addPlayer(mPlayerName.text.toString(), mPlayerPosition.text.toString(), mMatchId)
                } else {
                    mPlayerViewModel.updatePlayer(mPlayer!!.id, mPlayerName.text.toString(), mPlayerPosition.text.toString(), mMatchId)
                }

                val intent = Intent(context, MatchDetailActivity::class.java)
                activity!!.setResult(Activity.RESULT_OK, intent)
                activity!!.finish()
            } else {
                Toast.makeText(context, "Invalid player information", Toast.LENGTH_LONG).show()
            }
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mPlayerViewModel = ViewModelProviders.of(this, this.viewModelFactory).get(PlayerViewModel::class.java)
    }

    private fun validPlayerInfo() : Boolean {
        return !mPlayerName.text.toString().isBlank() && !mPlayerPosition.text.toString().isBlank()
    }

    fun loadPlayer(player: Player?, matchId : Int) {

        mMatchId = matchId

        mPlayer = player
        mPlayerName.setText(player?.name)
        mPlayerPosition.setText(player?.position)
    }

}
