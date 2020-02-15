package matcher.game.silveira.avila.com.gamematcher2.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Team

@Dao
interface TeamDao{

    @Insert
    fun insertLocation(team : Team)

    @Update
    fun updateLocation(location : Team)

    @Delete
    fun deleteLocation(location : Team)

}