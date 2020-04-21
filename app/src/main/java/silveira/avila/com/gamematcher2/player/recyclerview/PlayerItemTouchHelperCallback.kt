package silveira.avila.com.gamematcher2.player.recyclerview

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import silveira.avila.com.gamematcher2.R
import silveira.avila.com.gamematcher2.player.viewmodel.PlayerViewModel

class PlayerItemTouchHelperCallback(
    val adapter: PlayerAdapter,
    val mViewModel: PlayerViewModel,
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

        AlertDialog.Builder(context).setTitle(context.getString(R.string.alert_dialog_remove_player_title)).setPositiveButton(
            context.getString(R.string.alert_dialog_remove_player_positive)
        ) { dialogInterface: DialogInterface, i: Int ->
            mViewModel.removePlayer(position)
        }.setNegativeButton(context.getString(R.string.alert_dialog_remove_player_negative)) { dialogInterface: DialogInterface, i: Int ->
            adapter.notifyItemChanged(viewHolder.adapterPosition)
        }.setOnCancelListener{
            adapter.notifyItemChanged(viewHolder.adapterPosition)}
            .create().show()
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
