package matcher.game.silveira.avila.com.gamematcher2.db.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
class Match(@PrimaryKey val id : Int, val name : String, val date : String)
