package matcher.game.silveira.avila.com.gamematcher2.pick.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.ListView
import androidx.recyclerview.widget.DiffUtil

import matcher.game.silveira.avila.com.gamematcher2.R
import matcher.game.silveira.avila.com.gamematcher2.domain.Team
import matcher.game.silveira.avila.com.gamematcher2.pick.listview.TeamAdapter
import matcher.game.silveira.avila.com.gamematcher2.util.ListViewAdapterInteractions

/**
 * A simple [Fragment] subclass.
 * Use the [TeamFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TeamFragment(var team : Team?) : Fragment(), ListViewAdapterInteractions {

    lateinit var listView : ListView
    lateinit var listAdapter : ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_team, container, false)

        listAdapter = TeamAdapter(team?.players ?: emptyList(), this)
        listView.adapter = listAdapter

        return view
    }

}
