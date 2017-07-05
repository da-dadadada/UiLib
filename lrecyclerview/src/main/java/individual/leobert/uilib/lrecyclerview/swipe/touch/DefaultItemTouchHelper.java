/*
 * MIT License
 *
 * Copyright (c) 2017 leobert-lan
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package individual.leobert.uilib.lrecyclerview.swipe.touch;


import android.support.v7.widget.helper.CompatItemTouchHelper;

public class DefaultItemTouchHelper extends CompatItemTouchHelper {

    private DefaultItemTouchHelperCallback mDefaultItemTouchHelperCallback;

    /**
     * Create default item touch helper.
     */
    public DefaultItemTouchHelper() {
        this(new DefaultItemTouchHelperCallback());
    }

    /**
     * @param callback the behavior of ItemTouchHelper.
     */
    private DefaultItemTouchHelper(DefaultItemTouchHelperCallback callback) {
        super(callback);
        mDefaultItemTouchHelperCallback = (DefaultItemTouchHelperCallback) getCallback();
    }

    /**
     * Set OnItemMoveListener.
     *
     * @param onItemMoveListener {@link OnItemMoveListener}.
     */
    public void setOnItemMoveListener(OnItemMoveListener onItemMoveListener) {
        mDefaultItemTouchHelperCallback.setOnItemMoveListener(onItemMoveListener);
    }

    /**
     * Get OnItemMoveListener.
     *
     * @return {@link OnItemMoveListener}.
     */
    public OnItemMoveListener getOnItemMoveListener() {
        return mDefaultItemTouchHelperCallback.getOnItemMoveListener();
    }

    /**
     * Set OnItemMovementListener.
     *
     * @param onItemMovementListener {@link OnItemMovementListener}.
     */
    public void setOnItemMovementListener(OnItemMovementListener onItemMovementListener) {
        mDefaultItemTouchHelperCallback.setOnItemMovementListener(onItemMovementListener);
    }

    /**
     * Get OnItemMovementListener.
     *
     * @return {@link OnItemMovementListener}.
     */
    public OnItemMovementListener getOnItemMovementListener() {
        return mDefaultItemTouchHelperCallback.getOnItemMovementListener();
    }

    /**
     * Set can long press drag.
     *
     * @param canDrag drag true, otherwise is can't.
     */
    public void setLongPressDragEnabled(boolean canDrag) {
        mDefaultItemTouchHelperCallback.setLongPressDragEnabled(canDrag);
    }

    /**
     * Get can long press drag.
     *
     * @return drag true, otherwise is can't.
     */
    public boolean isLongPressDragEnabled() {
        return mDefaultItemTouchHelperCallback.isLongPressDragEnabled();
    }


    /**
     * Set can long press swipe.
     *
     * @param canSwipe swipe true, otherwise is can't.
     */
    public void setItemViewSwipeEnabled(boolean canSwipe) {
        mDefaultItemTouchHelperCallback.setItemViewSwipeEnabled(canSwipe);
    }

    /**
     * Get can long press swipe.
     *
     * @return swipe true, otherwise is can't.
     */
    public boolean isItemViewSwipeEnabled() {
        return this.mDefaultItemTouchHelperCallback.isItemViewSwipeEnabled();
    }

    /**
     * Set OnItemStateChangedListener.
     *
     * @param onItemStateChangedListener {@link OnItemStateChangedListener}.
     */
    public void setOnItemStateChangedListener(OnItemStateChangedListener onItemStateChangedListener) {
        this.mDefaultItemTouchHelperCallback.setOnItemStateChangedListener(onItemStateChangedListener);
    }

    /**
     * Get OnItemStateChangedListener.
     *
     * @return {@link OnItemStateChangedListener}.
     */
    public OnItemStateChangedListener getOnItemStateChangedListener() {
        return this.mDefaultItemTouchHelperCallback.getOnItemStateChangedListener();
    }

}
