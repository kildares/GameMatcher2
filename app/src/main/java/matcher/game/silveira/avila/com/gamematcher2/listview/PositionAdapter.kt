package matcher.game.silveira.avila.com.gamematcher2.listview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import matcher.game.silveira.avila.com.gamematcher2.R

class PositionAdapter(val gamePositions : List<String>, val adapterInteractions: ListViewAdapterInteractions) : BaseAdapter() {

    val selectedPositions = mutableSetOf<String>()

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = adapterInteractions.getLayoutInflater().inflate(R.layout.item_position, parent, false)
        val positionCheckBox = view.findViewById<CheckBox>(R.id.cb_position)
        positionCheckBox.text = gamePositions[position]

        positionCheckBox.setOnCheckedChangeListener { compoundButton, b ->
            if(b && !selectedPositions.contains(gamePositions[position])){
                selectedPositions.add(gamePositions[position])
            } else if(!b && selectedPositions.contains(gamePositions[position])){
                selectedPositions.remove(gamePositions[position])
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

    interface ListViewAdapterInteractions {
        fun getLayoutInflater(): LayoutInflater
    }

}