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


import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;

import java.util.Arrays;
import java.util.List;

import individual.leobert.sample_lrecyclerview.OnItemClickListener;
import individual.leobert.sample_lrecyclerview.SimpleAdapter;
import individual.leobert.sample_lrecyclerview.SimpleLRecyclerViewActivity;
import individual.leobert.uilib.lrecyclerview.LRecyclerView;
import individual.leobert.uilib.lrecyclerview.ProgressStyle;

public class PtrSample extends SimpleLRecyclerViewActivity {

    private List<String> samples = Arrays.asList(
            "SysProgress",
            "BallPulse",
            "BallGridPulse",
            "BallClipRotate",
            "BallClipRotatePulse",
            "SquareSpin",
            "BallClipRotateMultiple",
            "BallPulseRise",
            "BallRotate",
            "CubeTransition",
            "BallZigZag",
            "BallZigZagDeflect",
            "BallTrianglePath",
            "BallScale",
            "LineScale",
            "LineScaleParty",
            "BallScaleMultiple",
            "BallPulseSync",
            "BallBeat",
            "LineScalePulseOut",
            "LineScalePulseOutRapid",
            "BallScaleRipple",
            "BallScaleRippleMultiple",
            "BallSpinFadeLoader",
            "LineSpinFadeLoader",
            "TriangleSkewSpin",
            "Pacman",
            "BallGridBeat",
            "SemiCircleSpin"
    );

    private int[] styles = new int[]{
            ProgressStyle.SysProgress,
            ProgressStyle.BallPulse,
            ProgressStyle.BallGridPulse,
            ProgressStyle.BallClipRotate,
            ProgressStyle.BallClipRotatePulse,
            ProgressStyle.SquareSpin,
            ProgressStyle.BallClipRotateMultiple,
            ProgressStyle.BallPulseRise,
            ProgressStyle.BallRotate,
            ProgressStyle.CubeTransition,
            ProgressStyle.BallZigZag,
            ProgressStyle.BallZigZagDeflect,
            ProgressStyle.BallTrianglePath,
            ProgressStyle.BallScale,
            ProgressStyle.LineScale,
            ProgressStyle.LineScaleParty,
            ProgressStyle.BallScaleMultiple,
            ProgressStyle.BallPulseSync,
            ProgressStyle.BallBeat,
            ProgressStyle.LineScalePulseOut,
            ProgressStyle.LineScalePulseOutRapid,
            ProgressStyle.BallScaleRipple,
            ProgressStyle.BallScaleRippleMultiple,
            ProgressStyle.BallSpinFadeLoader,
            ProgressStyle.LineSpinFadeLoader,
            ProgressStyle.TriangleSkewSpin,
            ProgressStyle.Pacman,
            ProgressStyle.BallGridBeat,
            ProgressStyle.SemiCircleSpin
    };

    private LRecyclerView lRecyclerView;

    private OnItemClickListener onItemClickListener
            = new OnItemClickListener() {
        @Override
        public void onClick(int position) {
            int adjPosition = position - lRecyclerView.getLHeadersCount();

            if (adjPosition >= 0 && adjPosition < styles.length) {
                lRecyclerView.setRefreshProgressStyle(styles[adjPosition]);
                lRecyclerView.setLoadingMoreProgressStyle(styles[adjPosition]);

                setTitle(samples.get(adjPosition));
            }
        }
    };

    @Override
    protected void setup(final LRecyclerView lRecyclerView) {
        this.lRecyclerView = lRecyclerView;
        lRecyclerView.setPullRefreshEnabled(true);
        lRecyclerView.setLoadingMoreEnabled(true);

        SimpleAdapter adapter = new SimpleAdapter(samples);
        adapter.setOnItemClickListener(onItemClickListener);

        lRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        lRecyclerView.setAdapter(adapter);
        lRecyclerView.setLoadingListener(new LRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //just display the UI,ignore data change
                        lRecyclerView.refreshComplete();
//                        lRecyclerView.getAdapter().notifyDataSetChanged();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //just display the UI,ignore data change
                        lRecyclerView.loadMoreComplete();
//                        lRecyclerView.getAdapter().notifyDataSetChanged();
                    }
                }, 2000);
            }
        });

    }
}
