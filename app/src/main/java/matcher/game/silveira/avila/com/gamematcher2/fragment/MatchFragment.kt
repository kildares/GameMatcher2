package matcher.game.silveira.avila.com.gamematcher2.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import matcher.game.silveira.avila.com.gamematcher2.R
import matcher.game.silveira.avila.com.gamematcher2.di.MatchViewModelFactory
import matcher.game.silveira.avila.com.gamematcher2.di.Injectable
import matcher.game.silveira.avila.com.gamematcher2.recyclerview.MatchAdapter
import matcher.game.silveira.avila.com.gamematcher2.viewmodel.MatchViewModel
import javax.inject.Inject

class MatchFragment : Fragment(), Injectable {

    private lateinit var recyclerView : RecyclerView;
    private lateinit var matchAdapter : MatchAdapter
    @Inject
    lateinit var viewModelFactory : MatchViewModelFactory

    private lateinit var viewModel: MatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.match_fragment, container, false)
        recyclerView = view.findViewById(R.id.rv_items)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, this.viewModelFactory).get(MatchViewModel::class.java)

        prepareList()



    }

    private fun prepareList() {
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        matchAdapter = MatchAdapter(emptyList())
        recyclerView.adapter = matchAdapter
    }

}
