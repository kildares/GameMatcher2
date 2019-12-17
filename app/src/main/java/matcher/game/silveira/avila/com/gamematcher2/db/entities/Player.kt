package matcher.game.silveira.avila.com.gamematcher2.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Player(@PrimaryKey var id : Int, var name : String, var position : String)