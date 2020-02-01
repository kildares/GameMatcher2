package matcher.game.silveira.avila.com.gamematcher2.repository

import androidx.lifecycle.LiveData
import matcher.game.silveira.avila.com.gamematcher2.db.dao.PlayerDao
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Match
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Player
import javax.inject.Inject

class PlayerRepository @Inject constructor(var playerDao: PlayerDao) {

    fun getPlayerLiveDataList(matchId : Int) : LiveData<List<Player>> {
        return playerDao.findAllPlayersByMatchId(matchId)
    }

    fun addPlayer(player : Player) {
        playerDao.insertPlayer(player)
    }

    fun updatePlayer(player : Player) {
        playerDao.updatePlayer(player )
    }

}