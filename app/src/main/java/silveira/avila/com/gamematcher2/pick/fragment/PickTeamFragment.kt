package silveira.avila.com.gamematcher2.pick.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.core.view.forEach
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import silveira.avila.com.gamematcher2.R
import silveira.avila.com.gamematcher2.db.entities.Match
import silveira.avila.com.gamematcher2.di.Injectable
import silveira.avila.com.gamematcher2.di.MatchViewModelFactory
import silveira.avila.com.gamematcher2.domain.PickTeamOptions
import silveira.avila.com.gamematcher2.domain.SportsFacade
import silveira.avila.com.gamematcher2.pick.TeamPagerAdapter
import silveira.avila.com.gamematcher2.player.viewmodel.PlayerViewModel
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [PickTeamFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PickTeamFragment : Fragment(), Injectable {

    private lateinit var viewPager: ViewPager
    private lateinit var adapter: TeamPagerAdapter
    private lateinit var tabLayout: TabLayout
    private lateinit var mMatch: Match
    private lateinit var radioGroup: RadioGroup
    private lateinit var pickButton: Button

    private lateinit var radioButtonMap: Map<RadioButton, PickTeamOptions>
    private lateinit var selectedOption: PickTeamOptions

    private lateinit var mPlayerViewModel: PlayerViewModel

    @Inject
    lateinit var viewModelFactory: MatchViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val inflater = inflater.inflate(R.layout.fragment_pick_team, container, false)

        viewPager = inflater.findViewById(R.id.vp_viewPager)

        val match =
            activity?.intent?.extras?.getParcelable<Match>(getString(R.string.key_parcelable_match))
        if (match != null) {
            mMatch = match
            loadPlayers()
        }

        adapter = TeamPagerAdapter(
            mMatch.sport,
            null,
            activity!!.supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        viewPager.adapter = adapter

        tabLayout = inflater.findViewById(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)

        radioGroup = inflater.findViewById(R.id.rg_pick_team)
        pickButton = inflater.findViewById(R.id.bt_pick_team)

        radioButtonMap = mapOf(
            Pair(inflater.findViewById(R.id.rb_all_random), PickTeamOptions.ALL_RANDOM),
            Pair(inflater.findViewById(R.id.rb_goalkeepers), PickTeamOptions.ONLY_GOALKEEPER),
            Pair(inflater.findViewById(R.id.rb_positions), PickTeamOptions.BY_POSITION)
        )

        radioGroup.setOnCheckedChangeListener { group: RadioGroup?, checkedId: Int ->

            val mutableList = mutableListOf<RadioButton>()
            group?.forEach { mutableList.add(it as RadioButton) }
            val selected = mutableList.first { it.isChecked }
            selectedOption = radioButtonMap[selected] ?: error("No option selected")
        }

        pickButton.setOnClickListener {
            if (this::selectedOption.isInitialized) {
                pickTeams()
            }
        }

        inflater.findViewById<Toolbar>(R.id.tb_pick_team).setNavigationOnClickListener {
            activity!!.finish()
        }


        return inflater
    }

    private fun loadPlayers() {
        if (!this::mPlayerViewModel.isInitialized) {
            mPlayerViewModel =
                ViewModelProviders.of(this, this.viewModelFactory).get(PlayerViewModel::class.java)
            mPlayerViewModel.playerLiveData.observe(this, Observer {
                Log.d("observed players: ", mPlayerViewModel.playerLiveData.value?.size.toString())
            })
        }
    }

    private fun pickTeams() {
        Toast.makeText(activity, "Chosen option: ${selectedOption.name}", Toast.LENGTH_LONG).show()

        val pickedTeams = SportsFacade.pickTeams(
            mMatch.sport,
            mPlayerViewModel.playerLiveData.value ?: emptyList(),
            selectedOption
        )
        adapter.updateTeams(pickedTeams)
    }

}
