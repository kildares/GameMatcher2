package matcher.game.silveira.avila.com.gamematcher2.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Team(@PrimaryKey val id : Int, val name : String)