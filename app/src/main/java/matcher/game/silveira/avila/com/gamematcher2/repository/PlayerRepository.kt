package matcher.game.silveira.avila.com.gamematcher2.repository

import android.util.Log
import androidx.lifecycle.LiveData
import matcher.game.silveira.avila.com.gamematcher2.db.dao.PlayerDao
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Player
import javax.inject.Inject

class PlayerRepository @Inject constructor(var playerDao: PlayerDao) {

    var playerLiveData: LiveData<List<Player>> = playerDao.findAllPlayers()

    fun getPlayersByMatchId(matchId: Int): List<Player> {
        return playerLiveData.value!!.filter { it.matchId == matchId }
    }

    fun getAllPlayers(): LiveData<List<Player>> {
        return playerLiveData
    }

    fun addPlayer(player: Player) {
        val id = playerDao.insertPlayer(player)
        player.id = id.toInt()
//        (playerLiveData.value as ArrayList).add(player)
        Log.d("addPlayer", "$id")

    }

    fun updatePlayer(player: Player) {
        val id = playerDao.updatePlayer(player)
        player.id = id
        Log.d("updatePlayer", "$id")
        //playerLiveData.value!!.filter { it.id == player.id }
    }

}