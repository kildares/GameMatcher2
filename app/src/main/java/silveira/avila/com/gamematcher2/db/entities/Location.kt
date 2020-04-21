package silveira.avila.com.gamematcher2.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Location(@PrimaryKey val id : Int, val address : String, val number : String, var complement : String){

}