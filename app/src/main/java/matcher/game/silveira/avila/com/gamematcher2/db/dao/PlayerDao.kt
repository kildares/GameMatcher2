package matcher.game.silveira.avila.com.gamematcher2.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Location
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Player

@Dao
interface PlayerDao{

    @Insert
    fun insertPlayer(player : Player)

    @Update
    fun updatePlayer(location : Player)

    @Delete
    fun deletePlayer(location : Player)

}