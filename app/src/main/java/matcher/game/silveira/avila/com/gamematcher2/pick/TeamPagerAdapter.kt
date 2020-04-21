package matcher.game.silveira.avila.com.gamematcher2.pick

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import matcher.game.silveira.avila.com.gamematcher2.domain.Team
import matcher.game.silveira.avila.com.gamematcher2.pick.fragment.TeamFragment

class TeamPagerAdapter(private var teams : List<Team>?, fm: FragmentManager, behavior: Int) : FragmentStatePagerAdapter(fm, behavior) {

    override fun getItem(position: Int): Fragment {
        return TeamFragment(teams?.get(position))
    }

    override fun getCount() = teams?.size ?: 0
}