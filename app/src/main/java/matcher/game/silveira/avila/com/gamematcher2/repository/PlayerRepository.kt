package matcher.game.silveira.avila.com.gamematcher2.repository

import androidx.lifecycle.LiveData
import matcher.game.silveira.avila.com.gamematcher2.db.dao.PlayerDao
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Match
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Player
import javax.inject.Inject

class PlayerRepository @Inject constructor(playerDao: PlayerDao) {

    var playerDao : PlayerDao = playerDao

    fun getPlayerLiveDataList(matchId : Int) : LiveData<List<Player>> {
        return playerDao.findAllPlayersByMatchId(matchId)
    }


}