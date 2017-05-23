package individual.leobert.uilib.vlayoutext;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlext </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> VLayoutSection </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/22.
 */

public abstract class VLayoutSection<SD> {

    protected SD sectionData;

    protected ViewHolderEventDecor decor = null;


    public VLayoutSection(SD sectionData) {
        this.sectionData = sectionData;
    }

    public VLayoutSection(SD sectionData, ViewHolderEventDecor decor) {
        this.sectionData = sectionData;
        this.decor = decor;
    }

    protected final ViewHolderEventDecor getViewHolderEventDecor() {
        return decor;
    }

    public SD getSectionData() {
        return sectionData;
    }

    public abstract SectionAdapter getAdapter();

    public static abstract class SectionAdapter<VH extends RecyclerView.ViewHolder, ID>
            extends DelegateAdapter.Adapter<VH> {

        ViewHolderEventDecor<VH, ID> viewHolderEventDecor;

        public SectionAdapter() {
            this(null);
        }

        public SectionAdapter(ViewHolderEventDecor<VH, ID> viewHolderEventDecor) {
            this.viewHolderEventDecor = viewHolderEventDecor;
        }

        protected View useInflate(@LayoutRes int layout, @NonNull ViewGroup parent) {
            return LayoutInflater.from(parent.getContext())
                    .inflate(layout, parent, false);
        }

        public abstract ID getSectionItemData(int position);

        @Override
        public final void onBindViewHolder(VH holder, int position) {
            onBindViewHolder2(holder, position);
            if (viewHolderEventDecor != null)
                viewHolderEventDecor.decor(holder, getSectionItemData(position), position);
        }

        protected abstract void onBindViewHolder2(VH holder, int position);
    }

    public abstract static class ViewHolderEventDecor<VH extends RecyclerView.ViewHolder, ID> {

        public abstract void decor(VH holder, ID itemData, int position);
    }


}
