package individual.leobert.uilib.vlayoutext.group;

import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import individual.leobert.uilib.vlayoutext.EventViewHolder;
import individual.leobert.uilib.vlayoutext.VLayoutSection;
import individual.leobert.uilib.vlayoutext.core.ISectionAdapterComponent;
import individual.leobert.uilib.vlayoutext.core.SectionAdapter;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlayoutext.group </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> GroupSection </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/23.
 */

public abstract class GroupSection<VH extends EventViewHolder, ID, IEL>
        extends VLayoutSection<List<ID>>
        implements ISectionAdapterComponent<ID, VH ,IEL> {

    private List<ID> sectionData;

    private SectionAdapter<VH,ID,IEL> sectionAdapter;

    public GroupSection(@NonNull List<ID> sectionData) {
        this.sectionData = sectionData;
        sectionAdapter = new SectionAdapter<>(this);
    }

    @Override
    public List<ID> getSectionData() {
        return sectionData;
    }

    @Override
    public SectionAdapter getAdapter() {
        return sectionAdapter;
    }

    public void setSectionAdapter(SectionAdapter<VH, ID, IEL> sectionAdapter) {
        this.sectionAdapter = sectionAdapter;
    }

    @Override
    public ID getItemDataByPosition(int position) {
        return sectionData.get(position);
    }

    @Override
    public int getItemDataCount() {
        return sectionData.size();
    }

//    @Override
//    public IEL getItemEventListener() {
//        return null;
//    }
//
//    @Override
//    public LayoutHelper createLayoutHelper() {
//        return null;
//    }
//
//    @Override
//    public VH onCompatCreateViewHolder(View contentView, View originView, int viewType) {
//        return null;
//    }
//
//    @Override
//    public View onCreateItemView(ViewGroup parent, int viewType) {
//        return null;
//    }


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
