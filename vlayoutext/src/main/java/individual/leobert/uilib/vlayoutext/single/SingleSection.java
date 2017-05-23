package individual.leobert.uilib.vlayoutext.single;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;

import individual.leobert.uilib.vlayoutext.VLayoutSection;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlext </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> SingleSection </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/22.
 */

public abstract class SingleSection<VH extends RecyclerView.ViewHolder, ID>
        extends VLayoutSection {
    protected ID sectionData;

    protected SectionAdapter.ViewHolderEventDecor decor = null;

    protected SingleSectionAdapter<VH, ID> adapter;

    public SingleSection(ID sectionData) {
        this.sectionData = sectionData;
        initAdapter();
    }

    public SingleSection(ID sectionData, SectionAdapter.ViewHolderEventDecor decor) {
        this.sectionData = sectionData;
        this.decor = decor;
        initAdapter();
    }

    public ID getSectionData() {
        return sectionData;
    }

    private void initAdapter() {
        adapter = new SingleSectionAdapter<VH, ID>(decor) {

            @Override
            public VH onCreateViewHolder(ViewGroup parent, int viewType) {
                return SingleSection.this.onCreateViewHolder(parent);
            }

            @Override
            public ID getSectionItemData(int position) {
                return SingleSection.this.getSectionData();
            }

            @Override
            public void onBindViewHolder2(VH holder, int position) {
                SingleSection.this.onBindViewHolder(holder);
            }
        };
    }

    @Override
    public SingleSectionAdapter<VH, ID> getAdapter() {
        return adapter;
    }

    //only one type
    abstract VH onCreateViewHolder(ViewGroup parent);

    //only one instance,position always 0
    abstract void onBindViewHolder(VH holder);

    public static abstract class SingleSectionAdapter<VH extends RecyclerView.ViewHolder, ID>
            extends SectionAdapter<VH, ID> {

        public SingleSectionAdapter() { //unused
        }

        public SingleSectionAdapter(ViewHolderEventDecor viewHolderEventDecor) {
            super(viewHolderEventDecor);
        }

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
