package matcher.game.silveira.avila.com.gamematcher2.pick

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import matcher.game.silveira.avila.com.gamematcher2.pick.fragment.TeamFragment

class TeamPagerAdapter(fm: FragmentManager, behavior: Int) :
    FragmentStatePagerAdapter(fm, behavior) {

    val frag1 = TeamFragment()
    val frag2 = TeamFragment()
    val frag3 = TeamFragment()

    override fun getItem(position: Int): Fragment {
        return when(position){
            1 -> frag1
            2 -> frag2
            3 -> frag3
            else -> frag1
        }
    }

    override fun getCount() = 3
}