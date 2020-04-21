package silveira.avila.com.gamematcher2.pick

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import silveira.avila.com.gamematcher2.domain.Team
import silveira.avila.com.gamematcher2.pick.fragment.TeamFragment

class TeamPagerAdapter(var sport : String, var teams : MutableList<Team>?, fm: FragmentManager, behavior: Int) : FragmentStatePagerAdapter(fm, behavior) {

    fun updateTeams(t : List<Team>){

        if(this.teams == null){
            this.teams = t.toMutableList()
        } else {
            this.teams?.clear()
            this.teams?.addAll(t)
        }
        this.notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
        return TeamFragment(sport, teams?.get(position))
    }

    override fun getCount() = teams?.size ?: 0
}