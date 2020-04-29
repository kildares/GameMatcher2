package silveira.avila.com.gamematcher2.player.listview

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import androidx.core.view.children
import silveira.avila.com.gamematcher2.R
import silveira.avila.com.gamematcher2.util.ListViewAdapterInteractions

class PositionAdapter(private val gamePositions : List<String>, private val adapterInteractions: ListViewAdapterInteractions) : BaseAdapter() {

    val selectedPositions = mutableSetOf<String>()

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = adapterInteractions.getLayoutInflater().inflate(R.layout.item_position, parent, false)
        val positionCheckBox = view.findViewById<CheckBox>(R.id.cb_position)
        positionCheckBox.text = gamePositions[position]

        positionCheckBox.setOnCheckedChangeListener { _, b ->

            if(keyPosition == null){
                keyPosition = position
                selectedPositions.clear()
                if(b) {
                    selectedPositions.add(gamePositions[position])
                }
                parent!!.children.filter { it != positionCheckBox && it is CheckBox}.forEach { (it as CheckBox).isChecked = false }
                keyPosition = null
            }
        }

        return view
    }

    override fun getItem(position: Int): String {
        return gamePositions[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return gamePositions.size
    }

    companion object{
        var keyPosition : Int? = null
    }
}