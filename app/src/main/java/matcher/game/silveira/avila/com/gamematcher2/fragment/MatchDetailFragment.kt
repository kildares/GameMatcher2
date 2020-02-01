package matcher.game.silveira.avila.com.gamematcher2.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import matcher.game.silveira.avila.com.gamematcher2.R
import matcher.game.silveira.avila.com.gamematcher2.activity.PlayerDetailActivity
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Match
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Player
import matcher.game.silveira.avila.com.gamematcher2.di.Injectable
import matcher.game.silveira.avila.com.gamematcher2.di.MatchViewModelFactory
import matcher.game.silveira.avila.com.gamematcher2.recyclerview.PlayersListViewAdapter
import matcher.game.silveira.avila.com.gamematcher2.viewmodel.PlayerViewModel
import javax.inject.Inject


class MatchDetailFragment : Fragment(), Injectable, PlayersListViewAdapter.ListViewAdapterInteractions {

    private lateinit var mMatchNameTextView: TextView
    private lateinit var mMatchLocationTextView: TextView
    private lateinit var mMatchDateTextView: TextView
    private lateinit var mPlayersListView: ListView
    private lateinit var mAddPlayerButton: Button
    private lateinit var mPickTeamButton: Button

    private lateinit var mPlayerViewModel: PlayerViewModel
    private lateinit var mPlayersListViewAdapter : PlayersListViewAdapter

    @Inject
    lateinit var viewModelFactory: MatchViewModelFactory

    private lateinit var mMatch : Match

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_match_detail, container, false)

        mMatchNameTextView = view.findViewById(R.id.tv_detail_match_name)
        mMatchLocationTextView = view.findViewById(R.id.tv_detail_match_location)
        mMatchDateTextView = view.findViewById(R.id.tv_detail_match_date)
        mPlayersListView = view.findViewById(R.id.lv_detail_match_players)
        mAddPlayerButton = view.findViewById(R.id.bt_detail_add_player)
        mPickTeamButton = view.findViewById(R.id.bt_detail_pick_team)

        mMatch = activity!!.intent.extras?.getParcelable(getString(R.string.key_parcelable_match))!!

        mAddPlayerButton.setOnClickListener {
            val intent = Intent(context, PlayerDetailActivity::class.java)
            intent.putExtra(getString(R.string.key_parcelable_match_id), mMatch.id)
            startActivityForResult(intent, Activity.RESULT_OK)
        }

        loadPlayers();

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mPlayerViewModel =
            ViewModelProviders.of(this, this.viewModelFactory).get(PlayerViewModel::class.java)

        loadPlayers()

        mPlayerViewModel.playerLiveData?.observe(this, Observer {
            val players: List<Player> = mPlayerViewModel.playerLiveData!!.value ?: emptyList()

            mPlayersListViewAdapter = PlayersListViewAdapter(players, this)
            mPlayersListView.adapter = mPlayersListViewAdapter
        })
    }

    fun loadPlayers() {

        if (!this::mPlayerViewModel.isInitialized) {
            mPlayerViewModel =
                ViewModelProviders.of(this, this.viewModelFactory).get(PlayerViewModel::class.java)

            mPlayerViewModel.playerLiveData?.observe(this, Observer {
                val players: List<Player> = mPlayerViewModel.playerLiveData!!.value ?: emptyList()
                mPlayersListViewAdapter.players = players
                mPlayersListViewAdapter.notifyDataSetChanged()
            })
        }

        mPlayerViewModel.loadPlayersByMatchId(mMatch.id);
    }

    override fun onPlayerSelected(player: Player) {
        Toast.makeText(this.context, "onPlayerSelected", Toast.LENGTH_LONG).show()
    }

}


