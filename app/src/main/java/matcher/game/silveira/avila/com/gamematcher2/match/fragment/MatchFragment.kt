package matcher.game.silveira.avila.com.gamematcher2.match.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import matcher.game.silveira.avila.com.gamematcher2.R
import matcher.game.silveira.avila.com.gamematcher2.match.CreateMatchActivity
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Match
import matcher.game.silveira.avila.com.gamematcher2.di.Injectable
import matcher.game.silveira.avila.com.gamematcher2.di.MatchViewModelFactory
import matcher.game.silveira.avila.com.gamematcher2.match.MatchDetailActivity
import matcher.game.silveira.avila.com.gamematcher2.match.recyclerview.MatchAdapter
import matcher.game.silveira.avila.com.gamematcher2.match.recyclerview.MatchItemTouchHelperCallback
import matcher.game.silveira.avila.com.gamematcher2.match.viewmodel.MatchViewModel
import javax.inject.Inject

class MatchFragment : Fragment(), Injectable, MatchAdapter.MatchOnClickListener {

    private lateinit var recyclerView: RecyclerView;
    private lateinit var matchAdapter: MatchAdapter
    private lateinit var mItemTouchHelper: ItemTouchHelper

    @Inject
    lateinit var viewModelFactory: MatchViewModelFactory

    lateinit var fab: FloatingActionButton

    private lateinit var viewModel: MatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.match_fragment, container, false)
        recyclerView = view.findViewById(R.id.rv_matches)
        fab = view.findViewById(R.id.fab_add_match)

        fab.setOnClickListener {
            startCreateMatchActivity()
        }

        return view
    }

    private fun startCreateMatchActivity() {
        val intent = Intent(activity, CreateMatchActivity::class.java)
        startActivity(intent)
    }

    private fun startMatchDetailActivity(position: Int) {
        val intent = Intent(activity, MatchDetailActivity::class.java)

        intent.putExtra(getString(R.string.key_parcelable_match), matchAdapter.dataList[position])
        viewModel.matchLiveData.value?.get(position)
        startActivity(intent)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProviders.of(this, this.viewModelFactory).get(MatchViewModel::class.java)

        prepareList()

        prepareListEvents()

    }

    private fun prepareListEvents() {
        mItemTouchHelper = ItemTouchHelper(
            MatchItemTouchHelperCallback(
                adapter = matchAdapter,
                mViewModel = viewModel,
                context = activity as Context
            )
        )
        mItemTouchHelper.attachToRecyclerView(recyclerView)

    }

    private fun prepareList() {
        recyclerView.layoutManager = GridLayoutManager(
            activity,
            1,
            GridLayoutManager.VERTICAL,
            false
        )

        matchAdapter =
            MatchAdapter(
                emptyList(),
                this
            )
        recyclerView.adapter = matchAdapter

        viewModel.matchLiveData.observe(this, Observer {
            val matches: List<Match> = viewModel.matchLiveData.value ?: emptyList()
            matchAdapter.updateDataList(matches)
        })

    }

    override fun onMatchClicked(id: Int) {
        startMatchDetailActivity(id)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Toast.makeText(activity, "onActivityResult Fragment", Toast.LENGTH_LONG).show()
    }


}


