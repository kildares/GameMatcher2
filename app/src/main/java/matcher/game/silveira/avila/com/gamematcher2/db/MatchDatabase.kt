package matcher.game.silveira.avila.com.gamematcher2.db;

import androidx.room.Database;
import androidx.room.RoomDatabase

@Database(entities = [Match::class], version = 1, exportSchema = false)
abstract class MatchDatabase : RoomDatabase(){

    abstract fun matchDao() : MatchDao

}
