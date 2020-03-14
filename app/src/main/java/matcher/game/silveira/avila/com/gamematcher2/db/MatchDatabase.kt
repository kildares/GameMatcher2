package matcher.game.silveira.avila.com.gamematcher2.db;

import android.content.Context
import androidx.room.Database;
import androidx.room.Room
import androidx.room.RoomDatabase
import matcher.game.silveira.avila.com.gamematcher2.db.dao.LocationDao
import matcher.game.silveira.avila.com.gamematcher2.db.dao.MatchDao
import matcher.game.silveira.avila.com.gamematcher2.db.dao.PlayerDao
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Location
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Match
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Player
import javax.inject.Singleton

@Database(entities = [Match::class, Player::class, Location::class], version = 2, exportSchema = false)
@Singleton
abstract class MatchDatabase : RoomDatabase(){

    abstract fun matchDao() : MatchDao

    abstract fun playerDao() : PlayerDao

    abstract fun locationDao() : LocationDao


    companion object : SingletonHolder<MatchDatabase, Context>({
        Room.databaseBuilder(it.applicationContext,
            MatchDatabase::class.java, "match.db").fallbackToDestructiveMigration()
            .build()
    })

}
