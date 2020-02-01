package matcher.game.silveira.avila.com.gamematcher2.recyclerview

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import matcher.game.silveira.avila.com.gamematcher2.R
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Player
import java.lang.RuntimeException

class PlayersListViewAdapter(
    var players: List<Player>,
    val adapterInteractions: ListViewAdapterInteractions
) : BaseAdapter() {

    override fun getCount(): Int {
        return players.size
    }

    @SuppressLint("ViewHolder")
    override fun getView(i: Int, convertView: View?, parent: ViewGroup?): View {

        val view = adapterInteractions.getLayoutInflater().inflate(
            R.layout.item_player,
            parent,
            false
        )

        val position = view.findViewById<TextView>(R.id.tv_player_position)
        val name = view.findViewById<TextView>(R.id.tv_player_name)

        position.text = players[i].position
        name.text = players[i].name

        view.setOnClickListener {
            val pos = it.findViewById<TextView>(R.id.tv_player_position).text
            val playerName = it.findViewById<TextView>(R.id.tv_player_name).text

            val player = players.stream().filter { p -> p.name == playerName && p.position == pos }.findFirst()
                .orElseThrow { RuntimeException("Error validating players") }

            adapterInteractions.onPlayerSelected(player)
        }
        return view
    }

    override fun getItem(position: Int): Player {
        return players[position]
    }

    override fun getItemId(position: Int): Long {
        return players[position].id.toLong()
    }

    interface ListViewAdapterInteractions {

        fun getLayoutInflater(): LayoutInflater
        fun onPlayerSelected(player: Player)

    }
}