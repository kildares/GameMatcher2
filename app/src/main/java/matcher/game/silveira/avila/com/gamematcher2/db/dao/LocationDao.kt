package matcher.game.silveira.avila.com.gamematcher2.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Location

@Dao
interface LocationDao{

    @Insert
    fun insertLocation() : LiveData<List<Location>>

    @Update
    fun updateLocation(location : Location)

    @Delete
    fun deleteLocation(location : Location)

}