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

package individual.leobert.sample_lrecyclerview.samples;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Collections;
import java.util.List;

import individual.leobert.sample_lrecyclerview.R;
import individual.leobert.sample_lrecyclerview.SimpleAdapter;
import individual.leobert.sample_lrecyclerview.SimpleLRecyclerViewActivity;
import individual.leobert.uilib.lrecyclerview.LRecyclerView;
import individual.leobert.uilib.lrecyclerview.swipe.touch.OnItemMoveListener;
import individual.leobert.uilib.lrecyclerview.swipe.touch.OnItemStateChangedListener;

public class DragSortSample extends SimpleLRecyclerViewActivity {

    private LRecyclerView lRecyclerView;

    /**
     * 当Item移动的时候。
     */
    private OnItemMoveListener onItemMoveListener = new OnItemMoveListener() {
        @Override
        public boolean onItemMove(int fromPosition, int toPosition) {
            // 当Item被拖拽的时候。
            Collections.swap(mDataList, fromPosition, toPosition);
            lRecyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
            return true;// 返回true表示处理了，返回false表示你没有处理。
        }

        @Override
        public void onItemDismiss(int position) {
            // 当Item被滑动删除掉的时候，在这里是无效的，因为这里没有启用这个功能。
            // 使用Menu时就不用使用这个侧滑删除啦，两个是冲突的。
        }
    };

    /**
     * Item的拖拽/侧滑删除时，手指状态发生变化监听。
     */
    private OnItemStateChangedListener mOnItemStateChangedListener = new OnItemStateChangedListener() {
        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder,int actionState) {

            if (actionState == OnItemStateChangedListener.ACTION_STATE_DRAG) {
                setTitle("状态：拖拽");

                // 拖拽的时候背景就透明了，这里我们可以添加一个特殊背景。
                viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(getBaseContext(),
                        R.color.white_pressed));
            } else if (actionState == OnItemStateChangedListener.ACTION_STATE_SWIPE) {
                setTitle("状态：滑动删除");
            } else if (actionState == OnItemStateChangedListener.ACTION_STATE_IDLE) {
                setTitle("状态：手指松开");

                // 在手松开的时候还原背景。
                ViewCompat.setBackground(viewHolder.itemView,
                        ContextCompat.getDrawable(getBaseContext(),
                                R.drawable.select_white));
            }
        }};

    private List<String> mDataList;

    @Override
    protected void setup(final LRecyclerView lRecyclerView) {
        this.lRecyclerView = lRecyclerView;
        lRecyclerView.setPullRefreshEnabled(false);

        mDataList = genData("item",15,MODE_APPEND);

        SimpleAdapter adapter = new SimpleAdapter(mDataList);

        //new LRecyclerView.DividerItemDecoration(getDrawable(R.drawable.divider)));
        lRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        lRecyclerView.setAdapter(adapter);
        lRecyclerView.setLongPressDragEnabled(true);

        lRecyclerView.setOnItemMoveListener(this.onItemMoveListener);
        lRecyclerView.setOnItemStateChangedListener(this.mOnItemStateChangedListener);

    }



    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_type, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();
        if (menuId == R.id.type_linear) {
            lRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            lRecyclerView.getAdapter().notifyDataSetChanged();
            return true;
        }

        if (menuId == R.id.type_grid) {
            lRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            lRecyclerView.getAdapter().notifyDataSetChanged();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
