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

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * <p><b>Package:</b> individual.leobert.sample_lrecyclerview </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> SimpleViewHolder </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/7/3.
 */

public class SimpleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private TextView tvContent;
    private OnItemClickListener onItemClickListener;
    public SimpleViewHolder(View itemView) {
        super(itemView);
        tvContent = (TextView) itemView.findViewById(R.id.simple_content);
        if (tvContent == null) {
            throw new IllegalArgumentException("use simple_viewholder,never change it's id");
        }
        itemView.setOnClickListener(this);
    }

    public void bindClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public void setContent(String content) {
        tvContent.setText(content);
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null) {
            onItemClickListener.onClick(getAdapterPosition());
        }
    }
}
