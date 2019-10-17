package matcher.game.silveira.avila.com.gamematcher2.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import matcher.game.silveira.avila.com.gamematcher2.R
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Match

class MatchAdapter (matches : List<Match>): RecyclerView.Adapter<MatchAdapter.MatchViewHolder>(){

    private var dataList: List<Match> = matches

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.match_item, parent, false)
        return MatchViewHolder(view)

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.tvName?.text = dataList[position].name
        holder.tvDate?.text = dataList[position].date
    }


    inner class MatchViewHolder(itemView : View)  : RecyclerView.ViewHolder(itemView)  {
        val tvName = itemView.findViewById<TextView>(R.id.tv_name)
        val tvDate = itemView.findViewById<TextView>(R.id.tv_date)
    }


}