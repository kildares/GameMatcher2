package matcher.game.silveira.avila.com.gamematcher2.db.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Match::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("matchId"),
        onDelete = ForeignKey.CASCADE
    )]
)
class Player(@PrimaryKey(autoGenerate = true) var id: Int, var name: String, @ColumnInfo(index =  true) var matchId : Int,
             var positions : String) :
    Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(matchId)
        parcel.writeString(positions)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Player> {
        override fun createFromParcel(parcel: Parcel): Player {
            return Player(parcel)
        }

        override fun newArray(size: Int): Array<Player?> {
            return arrayOfNulls(size)
        }
    }

}