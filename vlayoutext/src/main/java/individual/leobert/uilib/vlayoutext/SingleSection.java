package individual.leobert.uilib.vlayoutext;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlext </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> SingleSection </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/22.
 */

public abstract class SingleSection<VH extends RecyclerView.ViewHolder> extends VLayoutSection {
    protected SingleSectionAdapter<VH> adapter = new SingleSectionAdapter<VH>() {

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            return SingleSection.this.onCreateViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {
            SingleSection.this.onBindViewHolder(holder);
        }
    };

    @Override
    public DelegateAdapter.Adapter<VH> getAdapter() {
        return adapter;
    }

    //only one type
    abstract VH onCreateViewHolder(ViewGroup parent);

    //only one instance,position always 0
    abstract void onBindViewHolder(VH holder);

    public static abstract class SingleSectionAdapter <VH extends RecyclerView.ViewHolder>
            extends SectionAdapter<VH> {

        @Override
        public final LayoutHelper onCreateLayoutHelper() {
            return new SingleLayoutHelper();
        }


        @Override
        public final int getItemCount() {
            return 1;
        }
    }
}
