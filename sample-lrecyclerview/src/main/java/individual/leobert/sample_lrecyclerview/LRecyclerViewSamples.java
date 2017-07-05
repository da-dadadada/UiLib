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

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import java.util.Arrays;
import java.util.List;

import individual.leobert.sample_lrecyclerview.samples.DragSortSample;
import individual.leobert.sample_lrecyclerview.samples.HOrderedMenuSample;
import individual.leobert.sample_lrecyclerview.samples.MenuInCardSample;
import individual.leobert.sample_lrecyclerview.samples.PtrSample;
import individual.leobert.sample_lrecyclerview.samples.VOrderedMenuSample;
import individual.leobert.uilib.lrecyclerview.LRecyclerView;

public class LRecyclerViewSamples extends SimpleLRecyclerViewActivity {
    private List<String> samples = Arrays.asList(
            "1. ptr samples",
            "2. horizontal ordered menu",
            "3. vertical ordered menu",
            "4. menu in CardView",
            "5. drag to sort",
            "more to add"

    );

    private List<Class<? extends AppCompatActivity>> sampleActivities
            = Arrays.asList(
            PtrSample.class,
            HOrderedMenuSample.class,
            VOrderedMenuSample.class,
            MenuInCardSample.class,
            DragSortSample.class,
            Todo.class
    );

    private void start(Class<? extends AppCompatActivity> aClass) {
        Intent intent = new Intent(this, aClass);
        startActivity(intent);
    }

    private OnItemClickListener onItemClickListener
            = new OnItemClickListener() {
        @Override
        public void onClick(int position) {
            int adjPosition = position - lRecyclerView.getLHeadersCount();
            start(sampleActivities.get(adjPosition));
        }
    };

    private LRecyclerView lRecyclerView;

    @Override
    protected void setup(LRecyclerView lRecyclerView) {
        if (samples.size() != sampleActivities.size()) {
            throw new IllegalStateException("samples are missing configuration");
        }

        this.lRecyclerView = lRecyclerView;
        lRecyclerView.setPullRefreshEnabled(false);

        SimpleAdapter adapter = new SimpleAdapter(samples);
        adapter.setOnItemClickListener(onItemClickListener);

        //new LRecyclerView.DividerItemDecoration(getDrawable(R.drawable.divider)));
        lRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        lRecyclerView.setAdapter(adapter);
    }
}
