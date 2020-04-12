package matcher.game.silveira.avila.com.gamematcher2.player.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import matcher.game.silveira.avila.com.gamematcher2.R
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Player
import matcher.game.silveira.avila.com.gamematcher2.domain.SportsFacade

class PlayerAdapter(
    var players: List<Player>,
    private val adapterInteractions: PlayerOnClickListener?,
    private val sport: String
) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    override fun onBindViewHolder(holder: PlayerViewHolder, pos: Int) {
        holder.playerPosition?.text =
            SportsFacade.convertPositionsToMutableSet(sport, players[pos].positions).first()
        holder.name?.text = players[pos].name
    }

    @SuppressLint("ViewHolder")
    inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val playerPosition = itemView.findViewById<TextView>(R.id.tv_player_position)
        val name = itemView.findViewById<TextView>(R.id.tv_player_name)

        init {
            itemView.setOnClickListener {
                val pos = it.findViewById<TextView>(R.id.tv_player_position).text
                val playerName = it.findViewById<TextView>(R.id.tv_player_name).text

                val player = players.stream().filter { p ->
                    p.name == playerName && SportsFacade.isFormalAndShownIdentical(
                        sport, p.positions,
                        pos.toString()
                    )
                }.findFirst()
                    .orElseThrow { RuntimeException("Error validating players") }

                adapterInteractions!!.onPlayerSelected(player)
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return players[position].id.toLong()
    }

    interface PlayerOnClickListener {
        fun onPlayerSelected(player: Player)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false)
        return PlayerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return players.size
    }
}