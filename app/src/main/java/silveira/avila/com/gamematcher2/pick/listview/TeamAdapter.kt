package silveira.avila.com.gamematcher2.pick.listview

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import silveira.avila.com.gamematcher2.R
import silveira.avila.com.gamematcher2.db.entities.Player
import silveira.avila.com.gamematcher2.domain.SportsFacade
import silveira.avila.com.gamematcher2.util.ListViewAdapterInteractions

class TeamAdapter(private val sport : String, private val players : List<Player>, private val adapterInteractions: ListViewAdapterInteractions) : BaseAdapter(){

    @SuppressLint("ViewHolder")
    override fun getView(i: Int, convertView: View?, parent: ViewGroup?): View {

        val view = adapterInteractions.getLayoutInflater().inflate(R.layout.item_player, parent, false)

        val playerPosition = view.findViewById<TextView>(R.id.tv_player_position)
        val name = view.findViewById<TextView>(R.id.tv_player_name)

        name.text = players[i].name
        playerPosition.text = SportsFacade.convertPositionsToMutableSet(sport, players[i].positions).toList()[0]

        return view
    }

    override fun getItem(position: Int) = players[position]

    override fun getItemId(position: Int) = players[position].id.toLong()

    override fun getCount() = players.size


}