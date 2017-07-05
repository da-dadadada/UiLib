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