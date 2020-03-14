package matcher.game.silveira.avila.com.gamematcher2.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import matcher.game.silveira.avila.com.gamematcher2.R
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Match
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Player
import matcher.game.silveira.avila.com.gamematcher2.di.Injectable
import matcher.game.silveira.avila.com.gamematcher2.di.MatchViewModelFactory
import matcher.game.silveira.avila.com.gamematcher2.listview.PositionAdapter
import matcher.game.silveira.avila.com.gamematcher2.domain.SportsFacade
import matcher.game.silveira.avila.com.gamematcher2.viewmodel.PlayerViewModel
import javax.inject.Inject


class PlayerDetailFragment : Fragment(), Injectable, PositionAdapter.ListViewAdapterInteractions {

    private lateinit var mConfirmButton: Button
    private lateinit var mPlayerName: EditText
    private lateinit var mPlayerDetailTitle2: TextView
    private lateinit var mPositions: ListView
    private lateinit var mPositionsAdapter: PositionAdapter

    private var mMatch: Match? = null
    private var mPlayer: Player? = null

    private lateinit var mPlayerViewModel: PlayerViewModel

    @Inject
    lateinit var viewModelFactory: MatchViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_player_detail, container, false)

        mConfirmButton = view.findViewById(R.id.bt_player_detail_confirm)
        mPlayerName = view.findViewById(R.id.et_detail_player_name)
        mPlayerDetailTitle2 = view.findViewById(R.id.tv_title_player_detail_2)

        mPositions = view.findViewById(R.id.rv_positions)

        mConfirmButton.setOnClickListener {
            if (validPlayerInfo()) {

                val selectedPositions = mPositionsAdapter.selectedPositions

                if (mPlayer == null) {
                    mPlayerViewModel.addPlayer(
                        mPlayerName.text.toString(),
                        SportsFacade.convertPositionsToStr(mMatch!!.sport, selectedPositions),
                        mMatch!!.id
                    )
                } else {
                    mPlayerViewModel.updatePlayer(
                        mPlayer!!.id,
                        mPlayerName.text.toString(),
                        SportsFacade.convertPositionsToStr(mMatch!!.sport, selectedPositions),
                        mMatch!!.id
                    )
                }

                activity!!.setResult(Activity.RESULT_OK)
                activity!!.finish()
            } else {
                Toast.makeText(context, "Invalid player information", Toast.LENGTH_LONG).show()
            }
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mPlayerViewModel =
            ViewModelProviders.of(this, this.viewModelFactory).get(PlayerViewModel::class.java)
    }

    private fun validPlayerInfo(): Boolean {
        return !mPlayerName.text.toString().isBlank() && mPositionsAdapter.selectedPositions.isNotEmpty()
    }

    fun loadPlayer(player: Player?, match: Match) {

        if (player == null) {
            mPlayerDetailTitle2.text = getString(R.string.toolbar_title_add_player)
        } else {
            mPlayerDetailTitle2.text = getString(R.string.toolbar_title_edit_player)
        }

        mMatch = match
        mPlayer = player
        mPlayerName.setText(player?.name)

        mPositionsAdapter = PositionAdapter(SportsFacade.getPositions(match.sport), this)
        mPositions.adapter = mPositionsAdapter

    }

}
