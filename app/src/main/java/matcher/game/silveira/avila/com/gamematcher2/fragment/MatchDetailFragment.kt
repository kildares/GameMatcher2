package matcher.game.silveira.avila.com.gamematcher2.fragment

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import matcher.game.silveira.avila.com.gamematcher2.R
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Match
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Player
import matcher.game.silveira.avila.com.gamematcher2.di.Injectable
import matcher.game.silveira.avila.com.gamematcher2.di.MatchViewModelFactory
import matcher.game.silveira.avila.com.gamematcher2.viewmodel.MatchViewModel
import matcher.game.silveira.avila.com.gamematcher2.viewmodel.PlayerViewModel
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MatchDetailFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MatchDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MatchDetailFragment : Fragment(), Injectable {

    private lateinit var matchNameTextView : TextView
    private lateinit var matchLocationTextView : TextView
    private lateinit var matchDateTextView : TextView
    private lateinit var playersListView : ListView
    private lateinit var addPlayerButton : Button
    private lateinit var pickTeamButton : Button

    @Inject
    lateinit var viewModelFactory: MatchViewModelFactory

    lateinit var playerViewModel : PlayerViewModel

    var matchId : Int? = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_match_detail, container, false)

        matchNameTextView = view.findViewById(R.id.tv_detail_match_name)
        matchLocationTextView = view.findViewById(R.id.tv_detail_match_location)
        matchDateTextView = view.findViewById(R.id.tv_detail_match_date)
        playersListView = view.findViewById(R.id.lv_detail_match_players)
        addPlayerButton = view.findViewById(R.id.bt_detail_add_player)
        pickTeamButton = view.findViewById(R.id.bt_detail_pick_team)

        matchId= activity!!.intent.extras?.getInt(getString(R.string.key_parcelable_match_id))!!

        loadPlayers(matchId!!);

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        playerViewModel =
            ViewModelProviders.of(this, this.viewModelFactory).get(PlayerViewModel::class.java)

//
//        playerViewModel.playerLiveData.observe(this, Observer {
//            val players: List<Player> = playerViewModel.playerLiveData.value ?: emptyList()
//            matchAdapter.updateDataList(matches)
//        })

    }

    fun loadPlayers(matchId : Int){
        playerViewModel.loadPlayersByMatchId(matchId);
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

}
