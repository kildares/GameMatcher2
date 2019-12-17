package matcher.game.silveira.avila.com.gamematcher2.db.entities;

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Entity
class Match(@PrimaryKey var id : Int, var name : String, var location : String, var date : String) : Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {

        dest?.writeInt(id)
        dest?.writeString(name)
        dest?.writeString(location)
        dest?.writeString(date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Match> {
        override fun createFromParcel(parcel: Parcel): Match {
            return Match(parcel)
        }

        override fun newArray(size: Int): Array<Match?> {
            return arrayOfNulls(size)
        }
    }
}