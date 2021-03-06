package individual.leobert.uilib.vlayoutext.single.pro;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;

import java.util.List;

import individual.leobert.uilib.vlayoutext.EventViewHolder;
import individual.leobert.uilib.vlayoutext.VLayoutSection;
import individual.leobert.uilib.vlayoutext.core.ISectionAdapterComponent;
import individual.leobert.uilib.vlayoutext.core.SectionAdapter;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlayoutext.group </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> OneNSection </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/24.
 */

public abstract class OneNSection<VH extends EventViewHolder, ID, IEL>
        extends VLayoutSection<List<ID>>
        implements ISectionAdapterComponent<ID, VH, IEL> {

    private SectionAdapter<VH, ID, IEL> adapter;

    private List<ID> sectionData;


    public OneNSection(@NonNull List<ID> sectionData) {
        this.sectionData = sectionData;
        setAdapter(new SectionAdapter<>(this));
    }

    public void setAdapter(SectionAdapter<VH, ID, IEL> adapter) {
        this.adapter = adapter;
    }

    @Override
    public List<ID> getSectionData() {
        return sectionData;
    }

    @Override
    public SectionAdapter getAdapter() {
        return adapter;
    }



    protected abstract void decorLayoutHelper(final OnePlusNLayoutHelper layoutHelper);


    @Override
    public ID getItemDataByPosition(int position) {
        return sectionData.get(position);
    }

    @Override
    public int getItemDataCount() {
        return sectionData.size();
    }

    @Override
    public IEL getItemEventListener() {
        return null;
    }

    @Override
    public LayoutHelper createLayoutHelper() {
        final OnePlusNLayoutHelper layoutHelper =
                new OnePlusNLayoutHelper(getItemDataCount());
        decorLayoutHelper(layoutHelper);
        return layoutHelper;
    }
}