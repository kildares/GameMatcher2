package matcher.game.silveira.avila.com.gamematcher2.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
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
            startActivityForResult(intent, resources.getInteger(R.integer.player_added_ok))
        }

        mPickTeamButton.setOnClickListener {
            Log.d("Players", mPlayerViewModel.playerLiveData.value?.size.toString())
        }

        loadPlayers()
        loadMatchData()

        return view
    }

    private fun loadMatchData() {

        val date = LocalDate.of(
            Integer.valueOf(mMatch.date.substring(4)),
            Integer.valueOf(mMatch.date.substring(2, 4)),
            Integer.valueOf(mMatch.date.substring(0, 2)))
            .format(DateTimeFormatter.ofPattern("dd/MM/yy"))

        mMatchNameTextView.text = mMatch.name
        mMatchDateTextView.text = date
        mMatchLocationTextView.text = mMatch.location
    }

    fun loadPlayers() {

        if (!this::mPlayerViewModel.isInitialized) {
            mPlayerViewModel =
                ViewModelProviders.of(this, this.viewModelFactory).get(PlayerViewModel::class.java)

            mPlayersListViewAdapter = PlayersListViewAdapter(mPlayerViewModel.playerLiveData.value.orEmpty(), this)
            mPlayersListView.adapter = mPlayersListViewAdapter

            mPlayerViewModel.playerLiveData.observe(this, Observer {
                Log.d("observed players: ", mPlayerViewModel.playerLiveData.value?.size.toString())
                mPlayersListViewAdapter.players = it?.filter { it.matchId == mMatch.id} ?: emptyList()
                mPlayersListViewAdapter.notifyDataSetChanged()
            })
        }
    }

    override fun onPlayerSelected(player: Player) {

        val intent = Intent(context, PlayerDetailActivity::class.java)
        intent.putExtra(getString(R.string.key_parcelable_match_id), mMatch.id)
        intent.putExtra(getString(R.string.key_parcelable_player), player)
        startActivityForResult(intent, Activity.RESULT_OK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

}


