package individual.leobert.uilib.lrecyclerview.swipe;

import individual.leobert.uilib.lrecyclerview.LRecyclerView;

public interface OnSwipeMenuItemClickListener {

    /**
     * Invoke when the menu item is clicked.
     *
     * @param closeable       closeable.
     * @param adapterPosition adapterPosition.
     * @param menuPosition    menuPosition.
     * @param direction       can be {@link LRecyclerView#LEFT_DIRECTION}, {@link LRecyclerView#RIGHT_DIRECTION}.
     */
    void onItemClick(Closeable closeable, int adapterPosition,
                     int menuPosition, @LRecyclerView.DirectionMode int direction);

}