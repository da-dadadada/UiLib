package individual.leobert.uilib.temp;

import android.support.annotation.NonNull;

import java.util.List;

import individual.leobert.uilib.vlayoutext.EventViewHolder;
import individual.leobert.uilib.vlayoutext.VLayoutSection;
import individual.leobert.uilib.vlayoutext.core.ISectionAdapterComponent;
import individual.leobert.uilib.vlayoutext.core.SectionAdapter;

/**
 * <p><b>Package:</b> individual.leobert.uilib.temp </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> GroupSection </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/6/29.
 */

public abstract class GroupSection<ID, VH extends EventViewHolder,IEL>
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
}
