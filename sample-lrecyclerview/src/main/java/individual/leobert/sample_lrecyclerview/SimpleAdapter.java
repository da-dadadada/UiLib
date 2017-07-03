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

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * <p><b>Package:</b> individual.leobert.sample_lrecyclerview </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> SimpleAdapter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/7/3.
 */

public class SimpleAdapter extends RecyclerView.Adapter<SimpleViewHolder> {
    private List<String > data;
    private OnItemClickListener onItemClickListener;

    public SimpleAdapter(@NonNull List<String> data) {
        this.data = data;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

//    @Override
//    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.simple_viewholder,parent,false);
//        return new SimpleViewHolder(view);
//    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View originView = onCreateItemView(parent, viewType);
        View decorView = onCreateDecorItemView(parent,originView, viewType);

        return onCompatCreateViewHolder(decorView, originView, viewType);
    }


    protected View onCreateDecorItemView(ViewGroup viewGroup,
                                         View originView, int viewType) {
        return originView;
    }

    private SimpleViewHolder onCompatCreateViewHolder(View contentView,
                                        View originView, int viewType) {
        return new SimpleViewHolder(contentView);
    }

    protected View onCreateItemView(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simple_viewholder,parent,false);
        return view;
    }


    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.setContent(data.get(position));
        holder.bindClickListener(onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
