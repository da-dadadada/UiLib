package individual.leobert.uilib.vlayoutext.single;

import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;

import individual.leobert.uilib.vlayoutext.EventViewHolder;
import individual.leobert.uilib.vlayoutext.VLayoutSection;
import individual.leobert.uilib.vlayoutext.core.ISectionAdapterComponent;
import individual.leobert.uilib.vlayoutext.core.SectionAdapter;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlext </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> SingleSection </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/22.
 */

public abstract class SingleSection<VH extends EventViewHolder, SD>
        extends VLayoutSection<SD>
        implements ISectionAdapterComponent<SD,VH,Void> {

    protected SectionAdapter<VH, SD,Void> adapter;
    private SD sectionData;

    public SingleSection(SD sectionData) {
        this.sectionData = sectionData;
        adapter = new SectionAdapter<>(this);
    }

    public void setAdapter(SectionAdapter<VH, SD, Void> adapter) {
        this.adapter = adapter;
    }

    public void resetData(SD sectionData) {
        this.sectionData = sectionData;
        getAdapter().notifyDataSetChanged();
    }


    @Override
    public SD getSectionData() {
        return sectionData;
    }

    @Override
    public SectionAdapter getAdapter() {
        return adapter;
    }

    public abstract VH onCompatCreateViewHolder(View contentView, View originView);

    public abstract View onCreateItemView(ViewGroup parent);

    @Override
    public SD getItemDataByPosition(int position) {
        //only one item need data
        return sectionData;
    }

    @Override
    public int getItemDataCount() {
        return 1;
    }

    @Override
    public Void getItemEventListener() {
        return null;
    }

    @Override
    public LayoutHelper createLayoutHelper() {
        return new SingleLayoutHelper();
    }

    @Override
    public VH onCompatCreateViewHolder(View contentView, View originView, int viewType) {
        return onCompatCreateViewHolder(contentView, originView);
    }

    @Override
    public View onCreateItemView(ViewGroup parent, int viewType) {
        return onCreateItemView(parent);
    }
}
