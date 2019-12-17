package matcher.game.silveira.avila.com.gamematcher2.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Match

@Dao
interface MatchDao {

    @Query(value = "SELECT * FROM `Match`")
    fun findAll() : LiveData<List<Match>>

    @Insert
    fun insertMatch(data : List<Match>)

    @Update
    fun updateMatch(data : Match)

    @Delete
    fun deleteMatch(data : Match)

    @Query(value = "SELECT MAX(id) FROM `Match`")
    fun findMaxId() : Int

}
