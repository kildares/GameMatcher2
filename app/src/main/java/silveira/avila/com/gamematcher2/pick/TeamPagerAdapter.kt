package silveira.avila.com.gamematcher2.pick

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import silveira.avila.com.gamematcher2.domain.Team
import silveira.avila.com.gamematcher2.pick.fragment.TeamFragment

class TeamPagerAdapter(var sport : String, var teams : List<Team>?, fm: FragmentManager, behavior: Int) : FragmentStatePagerAdapter(fm, behavior) {

    override fun getItem(position: Int): Fragment {
        return TeamFragment(sport, teams?.get(position))
    }

    override fun getCount() = teams?.size ?: 0
}