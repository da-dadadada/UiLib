package individual.leobert.uilib.vlayoutext.group;

import android.view.ViewGroup;

import com.alibaba.android.vlayout.LayoutHelper;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import individual.leobert.uilib.vlayoutext.EventViewHolder;
import individual.leobert.uilib.vlayoutext.VLayoutSection;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlayoutext.group </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> GroupSection </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/23.
 */

public abstract class GroupSection<VH extends EventViewHolder, ID, IEL>
        extends VLayoutSection<List<ID>> {

    private GroupSectionAdapter<VH, ID> adapter;

    public GroupSection(List<ID> sectionData) {
        super(sectionData);
        initAdapter();
    }

    protected IEL newItemEventListener(final ID itemData, final int position) {
        return null;
    }

    @Override
    public List<ID> getSectionData() {
        return super.getSectionData();
    }

    private void initAdapter() {
        adapter = new GroupSectionAdapter<VH, ID>() {

            @Override
            public LayoutHelper onCreateLayoutHelper() {
                return GroupSection.this.onCreateLayoutHelper();
            }

            @Override
            public VH onCreateViewHolder(ViewGroup parent, int viewType) {
                return GroupSection.this.onCreateViewHolder(parent, viewType);
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
            public void onBindViewHolder(VH holder, int position) {
                GroupSection.this.onBindViewHolder(holder, position, getSectionItemData(position));
                IEL iel = newItemEventListener(getSectionItemData(position), position);
                if (iel != null)
                    holder.bindEventListener(iel);
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
     *
     * @param holder   viewHolder
     * @param position currentPosition in the section
     * @param itemData data for this
     */
    protected abstract void onBindViewHolder(VH holder, int position, ID itemData);

    public static abstract class GroupSectionAdapter<VH extends EventViewHolder, ID>
            extends SectionAdapter<VH, ID> {
    }


    public void clearData() {
        if (sectionData == null)
            return;
        int count = sectionData.size();
        sectionData.clear();
        getAdapter().notifyItemRangeRemoved(0, count);
    }

    /**
     * @param itemData
     * @param headers  recyclerView额外添加的header的数量
     */
    public void addData(ID itemData, int headers) {
        if (sectionData == null)
            return;
        int position = sectionData.size();
        sectionData.add(itemData);
        getAdapter().notifyItemInserted(position + headers);
    }

    public void addData(int position, ID itemData, int headers) {
        if (sectionData == null)
            return;
        if (position < 0)
            position = 0;
        if (position > sectionData.size())
            position = sectionData.size();
        sectionData.add(position, itemData);
        getAdapter().notifyItemInserted(position + headers);
    }

    public void addData(List<ID> datas, int headers) {
        if (datas == null || datas.isEmpty())
            return;
        int start;
        if (sectionData == null) {
            sectionData = datas;
            start = 0;
        } else {
            start = sectionData.size();
            sectionData.addAll(datas);
        }
        getAdapter().notifyItemRangeInserted(start + headers, datas.size());
    }

    public void setData(List<ID> datas) {
        sectionData = datas;
        getAdapter().notifyDataSetChanged();
    }

    public boolean removeData(ID itemData) {
        if (sectionData == null)
            return false;
        boolean b = sectionData.remove(itemData);
        if (b)
            getAdapter().notifyDataSetChanged();
        return b;
    }

    public boolean removeData(int position, int headers) {
        if (sectionData == null)
            return false;
        if (position < 0 || position > sectionData.size())
            return false;
        sectionData.remove(position);
        getAdapter().notifyItemRemoved(position + headers);
        return true;
    }

    public boolean replaceData(int position, ID itemData, int headers) {
        if (sectionData == null)
            return false;
        if (position < 0 || position > sectionData.size())
            return false;
        sectionData.set(position, itemData);
        getAdapter().notifyItemChanged(position + headers);
        return true;
    }


    public void sortData(Comparator<? super ID> c) {
        if (sectionData == null) {
            return;
        }
        Collections.sort(sectionData, c);
        getAdapter().notifyDataSetChanged();
    }


}
