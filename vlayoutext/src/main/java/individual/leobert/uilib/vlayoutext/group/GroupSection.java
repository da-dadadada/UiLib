package individual.leobert.uilib.vlayoutext.group;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.LayoutHelper;

import java.util.List;

import individual.leobert.uilib.vlayoutext.VLayoutSection;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlayoutext.group </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> GroupSection </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/23.
 */

public abstract class GroupSection<VH extends RecyclerView.ViewHolder, ID>
        extends VLayoutSection<List<ID>> {

    private GroupSectionAdapter<VH,ID> adapter;

    public GroupSection(List<ID> sectionData) {
        super(sectionData);
        initAdapter();
    }

    public GroupSection(List<ID> sectionData, ViewHolderDecor<VH,ID> decor) {
        super(sectionData, decor);
        initAdapter();
    }

    @Override
    public List<ID> getSectionData() {
        return super.getSectionData();
    }

    private void initAdapter() {
        adapter = new GroupSectionAdapter<VH, ID>(getViewHolderEventDecor()) {

            @Override
            public LayoutHelper onCreateLayoutHelper() {
                return GroupSection.this.onCreateLayoutHelper();
            }

            @Override
            public VH onCreateViewHolder(ViewGroup parent, int viewType) {
                return GroupSection.this.onCreateViewHolder(parent,viewType);
            }

            @Override
            public int getItemCount() {
                return GroupSection.this.getSectionData().size();
            }

            @Override
            public ID getSectionItemData(int position) {
                return GroupSection.this.getSectionData().get(position);
            }


            @Override
            public void onBindViewHolder2(VH holder, int position) {
                GroupSection.this.onBindViewHolder(holder,position,getSectionItemData(position));
            }
        };
    }

    @Override
    public GroupSectionAdapter<VH, ID> getAdapter() {
        return adapter;
    }

    abstract LayoutHelper onCreateLayoutHelper();

    protected abstract VH onCreateViewHolder(ViewGroup parent, int viewType);


    /**
     * jobs:update the viewHolder with the itemData,position is prepare for future-use
     * @param holder viewHolder
     * @param position currentPosition in the section
     * @param itemData data for this
     */
    protected abstract void onBindViewHolder(VH holder, int position, ID itemData);

    public static abstract class GroupSectionAdapter<VH extends RecyclerView.ViewHolder, ID>
            extends SectionAdapter<VH, ID> {

        public GroupSectionAdapter() { //unused
        }

        public GroupSectionAdapter(ViewHolderDecor<VH, ID> viewHolderDecor) {
            super(viewHolderDecor);
        }

    }
}
