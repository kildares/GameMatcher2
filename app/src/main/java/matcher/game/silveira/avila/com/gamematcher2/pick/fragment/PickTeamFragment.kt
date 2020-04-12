package matcher.game.silveira.avila.com.gamematcher2.pick.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import matcher.game.silveira.avila.com.gamematcher2.R
import matcher.game.silveira.avila.com.gamematcher2.pick.TeamPagerAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [PickTeamFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PickTeamFragment : Fragment() {

    private lateinit var viewPager : ViewPager
    private lateinit var tabLayout : TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val inflater = inflater.inflate(R.layout.fragment_pick_team, container, false)

        viewPager = inflater.findViewById(R.id.vp_viewPager)

        viewPager.adapter = TeamPagerAdapter(activity!!.supportFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)

        tabLayout = inflater.findViewById(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)

        return inflater
    }

    //TODO configurar injeção de pageViewer e TabLayout pelo Dagger2

}
