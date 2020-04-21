package matcher.game.silveira.avila.com.gamematcher2.pick.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import matcher.game.silveira.avila.com.gamematcher2.R
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Match
import matcher.game.silveira.avila.com.gamematcher2.pick.TeamPagerAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [PickTeamFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PickTeamFragment : Fragment() {

    private lateinit var viewPager : ViewPager
    private lateinit var adapter : TeamPagerAdapter
    private lateinit var tabLayout : TabLayout
    private lateinit var mMatch : Match
    private lateinit var radioGroup : RadioGroup
    private lateinit var pickButton : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val inflater = inflater.inflate(R.layout.fragment_pick_team, container, false)

        radioGroup = inflater.findViewById(R.id.rg_pick_team)
        pickButton = inflater.findViewById(R.id.bt_pick_team)

        viewPager = inflater.findViewById(R.id.vp_viewPager)

        val match = activity?.intent?.extras?.getParcelable<Match>(getString(R.string.key_parcelable_match))
        if(match != null){
            mMatch = match
        }

        adapter = TeamPagerAdapter(null, activity!!.supportFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        viewPager.adapter = adapter

        tabLayout = inflater.findViewById(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)

        return inflater
    }

}
