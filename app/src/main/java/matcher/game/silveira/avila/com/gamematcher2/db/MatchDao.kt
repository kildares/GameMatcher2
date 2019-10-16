package matcher.game.silveira.avila.com.gamematcher2.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MatchDao {

    @Query(value = "SELECT * FROM `Match`")
    fun findAll() : LiveData<List<Match>>


    @Insert
    fun insertMatch(data : List<Match>)


    @Update
    fun updateMatch(data : Match)
}
