package matcher.game.silveira.avila.com.gamematcher2.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import matcher.game.silveira.avila.com.gamematcher2.R
import matcher.game.silveira.avila.com.gamematcher2.activity.CreateMatchActivity
import matcher.game.silveira.avila.com.gamematcher2.activity.MatchDetailActivity
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Match
import matcher.game.silveira.avila.com.gamematcher2.di.Injectable
import matcher.game.silveira.avila.com.gamematcher2.di.MatchViewModelFactory
import matcher.game.silveira.avila.com.gamematcher2.recyclerview.MatchAdapter
import matcher.game.silveira.avila.com.gamematcher2.viewmodel.MatchViewModel
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
        recyclerView = view.findViewById(R.id.rv_items)
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

        matchAdapter = MatchAdapter(emptyList(), this)
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

    inner class MatchItemTouchHelperCallback(
        val adapter: MatchAdapter,
        val mViewModel: MatchViewModel,
        val context: Context
    ) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            if (viewHolder.adapterPosition == 10) return 0
            return super.getMovementFlags(recyclerView, viewHolder)
        }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition

            AlertDialog.Builder(context).setTitle(getString(R.string.alert_dialog_remove_match_title)).setPositiveButton(
                getString(R.string.alert_dialog_remove_match_positive)
            ) { dialogInterface: DialogInterface, i: Int ->
                mViewModel.removeMatch(position)
            }.setNegativeButton(getString(R.string.alert_dialog_remove_match_negative)) { dialogInterface: DialogInterface, i: Int ->
                adapter.notifyItemChanged(viewHolder.adapterPosition)
            }.create().show()
        }

        override fun onChildDraw(
            canvas: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
        ) {
            val background = ColorDrawable()
            val backgroundColor = Color.parseColor("#f44336")
            val deleteIcon = ContextCompat.getDrawable(context, R.drawable.ic_delete_white_24)
            val intrinsicWidth = deleteIcon!!.intrinsicWidth
            val intrinsicHeight = deleteIcon.intrinsicHeight

            val itemView = viewHolder.itemView
            val itemHeight = itemView.bottom - itemView.top

            val isCancelled = dX == 0f && !isCurrentlyActive
            if (isCancelled) {
                clearCanvas(
                    canvas,
                    itemView.right + dX,
                    itemView.top.toFloat(),
                    itemView.right.toFloat(),
                    itemView.bottom.toFloat()
                )
                super.onChildDraw(
                    canvas,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }

            // Draw the red delete background
            background.color = backgroundColor
            background.setBounds(
                itemView.right + dX.toInt(),
                itemView.top,
                itemView.right,
                itemView.bottom
            )
            background.draw(canvas)

            // Calculate position of delete icon
            val iconTop = itemView.top + (itemHeight - intrinsicHeight) / 2
            val iconMargin = (itemHeight - intrinsicHeight) / 2
            val iconLeft = itemView.right - iconMargin - intrinsicWidth
            val iconRight = itemView.right - iconMargin
            val iconBottom = iconTop + intrinsicHeight

            // Draw the delete icon
            deleteIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
            deleteIcon.draw(canvas)

            super.onChildDraw(
                canvas,
                recyclerView,
                viewHolder,
                dX,
                dY,
                actionState,
                isCurrentlyActive
            )
        }

        private fun clearCanvas(c: Canvas?, left: Float, top: Float, right: Float, bottom: Float) {
            val clearPaint = Paint().apply { xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR) }
            c?.drawRect(left, top, right, bottom, clearPaint)
        }
    }

}


