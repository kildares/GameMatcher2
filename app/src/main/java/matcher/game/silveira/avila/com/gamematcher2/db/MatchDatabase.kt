package matcher.game.silveira.avila.com.gamematcher2.db;

import androidx.room.Database;
import androidx.room.RoomDatabase
import matcher.game.silveira.avila.com.gamematcher2.db.dao.LocationDao
import matcher.game.silveira.avila.com.gamematcher2.db.dao.MatchDao
import matcher.game.silveira.avila.com.gamematcher2.db.dao.PlayerDao
import matcher.game.silveira.avila.com.gamematcher2.db.dao.TeamDao
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Location
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Match
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Player
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Team

@Database(entities = [Match::class, Player::class, Location::class, Team::class], version = 1, exportSchema = false)
abstract class MatchDatabase : RoomDatabase(){

    abstract fun matchDao() : MatchDao

    abstract fun playerDao() : PlayerDao

    abstract fun locationDao() : LocationDao

    abstract fun teamDao() : TeamDao

}
