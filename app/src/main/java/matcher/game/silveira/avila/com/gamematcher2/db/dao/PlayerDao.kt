package matcher.game.silveira.avila.com.gamematcher2.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Player

@Dao
interface PlayerDao{

    @Insert
    fun insertPlayer(player : Player) : Long

    @Update
    fun updatePlayer(location : Player) : Int

    @Delete
    fun deletePlayer(location : Player)

    @Query(value = "SELECT * FROM `Player` WHERE id = :matchId")
    fun findAllPlayersByMatchId(matchId : Int) : LiveData<List<Player>>

    @Query(value = "SELECT * FROM `Player`")
    fun findAllPlayers() : LiveData<List<Player>>
}
