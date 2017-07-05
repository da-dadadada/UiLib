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

package individual.leobert.sample_lrecyclerview;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import individual.leobert.uilib.lrecyclerview.LRecyclerView;
import individual.leobert.uilib.lrecyclerview.swipe.ISwipeMenuAdapter;
import individual.leobert.uilib.lrecyclerview.swipe.SwipeMenuAdapterHelper;

/**
 * <p><b>Package:</b> individual.leobert.sample_lrecyclerview </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> SimpleLRecyclerViewActivity </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/7/3.
 */

public abstract class SimpleLRecyclerViewActivity extends AppCompatActivity {

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lrecycler_view_samples);
        final LRecyclerView lRecyclerView =
                (LRecyclerView) findViewById(R.id.lrecyclerview);
        lRecyclerView.setPullRefreshEnabled(false);
        lRecyclerView.addItemDecoration(lRecyclerView.new DividerItemDecoration(getDrawable(R.drawable.divider)));

        setup(lRecyclerView);
    }

    protected abstract void setup(LRecyclerView lRecyclerView);

    public static final int MODE_APPEND = 0;
    private static final int MODE_FORMAT = 1;

    @IntDef({MODE_APPEND,MODE_FORMAT})
    public @interface Mode{

    }

    protected List<String> genData(String model,int count,@Mode int mode) {
      List<String> strings = new ArrayList<>();
        for (int i = 0;i<count;i++) {
            String s;
            if (mode == MODE_APPEND) {
                s = model+i;
            } else {
                s = String.format(Locale.ENGLISH,model,i);
            }
            strings.add(s);
        }
        return strings;
    }

    public class MAdapter extends SimpleAdapter
            implements ISwipeMenuAdapter {

        private SwipeMenuAdapterHelper swipeMenuAdapterHelper;

        public MAdapter(List<String> data) {
            super(data);
        }

        @Override
        protected View onCreateDecorItemView(ViewGroup viewGroup, View originView, int viewType) {
            if (swipeMenuAdapterHelper != null) {
                Log.d("lmsg", "create swipe menu");
                return swipeMenuAdapterHelper.helpCreateSwipeView(viewGroup, originView, viewType);
            } else {
                Log.d("lmsg", "swipehelper is null");
            }
            return originView;
        }

        @Override
        public void onBindViewHolder(SimpleViewHolder holder, int position) {
            if (swipeMenuAdapterHelper != null)
                swipeMenuAdapterHelper.helpOnBindViewHolder(holder, position, null);
            super.onBindViewHolder(holder, position);
        }

        @Override
        public void setSwipeMenuAdapterHelper(SwipeMenuAdapterHelper helper) {
            this.swipeMenuAdapterHelper = helper;
        }

        @Override
        public SwipeMenuAdapterHelper getSwipeMenuAdapterHelper() {
            return swipeMenuAdapterHelper;
        }
    }
}
