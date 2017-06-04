package individual.leobert.uilib.vlayoutext.single.pro;

import android.view.ViewGroup;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;

import java.util.List;

import individual.leobert.uilib.vlayoutext.EventViewHolder;
import individual.leobert.uilib.vlayoutext.VLayoutSection;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlayoutext.group </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> OneNSection </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/24.
 */

public abstract class OneNSection<VH extends EventViewHolder, ID,IEL>
        extends VLayoutSection< List<ID>> {

    private SectionAdapter<VH, ID> adapter;


    public OneNSection(List<ID> sectionData) {
        super(sectionData);
        initAdapter();
    }

    protected IEL newItemEventListener(final ID itemData,final int position) {
        return null;
    }


    protected void initAdapter() {
        adapter = new OneNSectionAdapter<VH, ID>() {

            @Override
            public VH onCreateViewHolder(ViewGroup parent, int viewType) {
                return OneNSection.this.onCreateViewHolder(parent, viewType);
            }

            @Override
            public int getItemCount() {
                return OneNSection.this.getSectionData().size();
            }

            @Override
            public LayoutHelper onCreateLayoutHelper() {
                return OneNSection.this.onCreateLayoutHelper();
            }

            @Override
            public ID getSectionItemData(int position) {
                return OneNSection.this.getSectionData().get(position);
            }

            @Override
            public void onBindViewHolder(VH holder, int position) {
                OneNSection.this.onBindViewHolder(holder,position,getSectionItemData(position));
                IEL iel = newItemEventListener(getSectionItemData(position),position);
                if (iel != null)
                    holder.bindEventListener(iel);
            }
        };
    }

    protected abstract VH onCreateViewHolder(ViewGroup parent, int viewType);


    /**
     * jobs:update the viewHolder with the itemData,position is prepare for future-use
     * @param holder viewHolder
     * @param position currentPosition in the section
     * @param itemData data for this
     */
    protected abstract void onBindViewHolder(VH holder, int position, ID itemData);

    @Override
    public SectionAdapter<VH, ID> getAdapter() {
        return adapter;
    }

    LayoutHelper onCreateLayoutHelper() {
        final OnePlusNLayoutHelper layoutHelper = new OnePlusNLayoutHelper(getSectionData().size());
        decorLayoutHelper(layoutHelper);
        return layoutHelper;
    }

    protected abstract void decorLayoutHelper(final OnePlusNLayoutHelper layoutHelper);

    public static abstract class OneNSectionAdapter<VH extends EventViewHolder, ID>
            extends SectionAdapter<VH, ID> {
    }
}