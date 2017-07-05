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

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import individual.leobert.sample_lrecyclerview.R;
import individual.leobert.sample_lrecyclerview.SimpleLRecyclerViewActivity;
import individual.leobert.uilib.lrecyclerview.LRecyclerView;
import individual.leobert.uilib.lrecyclerview.swipe.Closeable;
import individual.leobert.uilib.lrecyclerview.swipe.OnSwipeMenuItemClickListener;
import individual.leobert.uilib.lrecyclerview.swipe.SwipeMenu;
import individual.leobert.uilib.lrecyclerview.swipe.SwipeMenuAdapterHelper;
import individual.leobert.uilib.lrecyclerview.swipe.SwipeMenuCreator;
import individual.leobert.uilib.lrecyclerview.swipe.SwipeMenuItem;

public class HOrderedMenuSample extends SimpleLRecyclerViewActivity {

    private LRecyclerView lRecyclerView;

    private Context getContext() {
        return this;
    }


    /**
     * 菜单创建器。在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.item_height);

            //TODO: 2017/7/3 attention：use setOrientation to control the style
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;

            swipeLeftMenu.setOrientation(SwipeMenu.VERTICAL);
            swipeRightMenu.setOrientation(SwipeMenu.VERTICAL);

            // 添加左侧的，如果不添加，则左侧不会出现菜单。
            {
                SwipeMenuItem addItem = new SwipeMenuItem(getContext())
                        .setBackgroundDrawable(R.drawable.selector_green)// 点击的背景。
                        .setImage(R.mipmap.ic_action_add) // 图标。
                        .setWidth(width) // 宽度。
                        .setHeight(height); // 高度。
                swipeLeftMenu.addMenuItem(addItem); // 添加一个按钮到左侧菜单。

                SwipeMenuItem closeItem = new SwipeMenuItem(getContext())
                        .setBackgroundDrawable(R.drawable.selector_red)
                        .setImage(R.mipmap.ic_action_close)
                        .setWidth(width)
                        .setHeight(height);

                swipeLeftMenu.addMenuItem(closeItem); // 添加一个按钮到左侧菜单。
            }

            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            {
                SwipeMenuItem deleteItem = new SwipeMenuItem(getContext())
                        .setBackgroundDrawable(R.drawable.selector_red)
                        .setImage(R.mipmap.ic_action_delete)
                        .setText("删除") // 文字，还可以设置文字颜色，大小等。。
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。

                SwipeMenuItem closeItem = new SwipeMenuItem(getContext())
                        .setBackgroundDrawable(R.drawable.selector_purple)
                        .setImage(R.mipmap.ic_action_close)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(closeItem); // 添加一个按钮到右侧菜单。

                SwipeMenuItem addItem = new SwipeMenuItem(getContext())
                        .setBackgroundDrawable(R.drawable.selector_green)
                        .setText("添加")
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(addItem); // 添加一个按钮到右侧菜单。
            }
        }
    };

    /**
     * 菜单点击监听。
     */
    private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {

        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。

            if (direction == LRecyclerView.RIGHT_DIRECTION) {
                Toast.makeText(getBaseContext(), "list第" +
                        getAdjustPosition(adapterPosition) + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            } else if (direction == LRecyclerView.LEFT_DIRECTION) {
                Toast.makeText(getBaseContext(), "list第" +
                        getAdjustPosition(adapterPosition) + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            }
        }
    };

    private int getAdjustPosition(int adapterPosition) {
        return adapterPosition - lRecyclerView.getLHeadersCount();
    }

    @Override
    protected void setup(LRecyclerView lRecyclerView) {
        this.lRecyclerView = lRecyclerView;

        lRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        MAdapter mAdapter = new MAdapter(genData("item",10,MODE_APPEND));

        SwipeMenuAdapterHelper swipeMenuAdapterHelper = new SwipeMenuAdapterHelper();
        swipeMenuAdapterHelper.setSwipeMenuCreator(swipeMenuCreator);
        swipeMenuAdapterHelper.setSwipeMenuItemClickListener(menuItemClickListener);
        mAdapter.setSwipeMenuAdapterHelper(swipeMenuAdapterHelper);

        lRecyclerView.setAdapter(mAdapter);
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
