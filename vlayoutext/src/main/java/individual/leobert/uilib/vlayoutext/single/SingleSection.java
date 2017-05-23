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

public abstract class SingleSection<VH extends RecyclerView.ViewHolder, SD>
        extends VLayoutSection<SD> {
    
    protected SingleSectionAdapter<VH, SD> adapter;

    public SingleSection(SD sectionData) {
        super(sectionData);
        initAdapter();
    }

    public SingleSection(SD sectionData, ViewHolderEventDecor decor) {
        super(sectionData, decor);
        initAdapter();
    }


    private void initAdapter() {
        //only "one" item in the whole recycleView,so we use sectionData(SD as ID)
        adapter = new SingleSectionAdapter<VH, SD>(getViewHolderEventDecor()) {

            @Override
            public VH onCreateViewHolder(ViewGroup parent, int viewType) {
                return SingleSection.this.onCreateViewHolder(parent);
            }

            @Override
            public SD getSectionItemData(int position) {
                //only one item
                return SingleSection.this.getSectionData();
            }

            @Override
            public void onBindViewHolder2(VH holder, int position) {
                SingleSection.this.onBindViewHolder(holder);
            }
        };
    }

    @Override
    public SingleSectionAdapter<VH, SD> getAdapter() {
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

        public SingleSectionAdapter(ViewHolderEventDecor<VH, ID> viewHolderEventDecor) {
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
