package matcher.game.silveira.avila.com.gamematcher2.db.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
class Match(val name : String, val location : String, val date : String){

    @PrimaryKey(autoGenerate = true)
    var id : Int = 0

}
