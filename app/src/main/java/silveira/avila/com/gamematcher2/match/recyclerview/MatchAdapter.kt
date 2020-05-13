package silveira.avila.com.gamematcher2.match.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import silveira.avila.com.gamematcher2.R
import silveira.avila.com.gamematcher2.db.entities.Match
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MatchAdapter(var dataList: List<Match>, var listener: MatchOnClickListener) :
    RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    fun updateDataList(matches: List<Match>) {
        dataList = matches
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.match_item, parent, false)
        return MatchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {

        val date = LocalDate.of(
            Integer.valueOf(dataList[position].date.substring(6)),
            Integer.valueOf(dataList[position].date.substring(3, 5)),
            Integer.valueOf(dataList[position].date.substring(0, 2))
        ).format(DateTimeFormatter.ofPattern("dd/MM/yy"))

        holder.tvName?.text = dataList[position].name
        holder.tvDate?.text = date
        holder.tvLocation?.text = dataList[position].location
    }


    inner class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.findViewById<TextView>(R.id.tv_match_name)
        val tvDate = itemView.findViewById<TextView>(R.id.tv_match_date)
        val tvLocation = itemView.findViewById<TextView>(R.id.tv_match_location)

        init {
            itemView.setOnClickListener {
                listener.onMatchClicked(adapterPosition)
            }
        }

    }

    interface MatchOnClickListener {
        fun onMatchClicked(id: Int)
    }

}