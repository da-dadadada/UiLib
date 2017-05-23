package individual.leobert.uilib.vlayout;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;

import individual.leobert.uilib.R;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlayout </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> SubSingleSectionAdapter </p>
 * <p><b>Description:</b> TODO </p>
 *
 * @Param SDS the dataset for this section
 * Created by leobert on 2017/5/22.
 */

public abstract class SubSingleSectionAdapter<VH extends RecyclerView.ViewHolder, SDS> extends DelegateAdapter.Adapter<VH> {

    private SDS sds;

    public SubSingleSectionAdapter(SDS sds) {
        this.sds = sds;
    }

    public SDS getSectionDataSet() {
        return sds;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new SingleLayoutHelper();
    }


    @Override
    public int getItemCount() {
        return 1;
    }

    protected View useInflate(@LayoutRes int layout, @NonNull ViewGroup parent) {
        return LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);
    }
}
