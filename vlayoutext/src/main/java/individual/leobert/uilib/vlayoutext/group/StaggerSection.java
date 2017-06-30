package individual.leobert.uilib.vlayoutext.group;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;

import java.util.List;

import individual.leobert.uilib.vlayoutext.EventViewHolder;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlayoutext.group </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> StaggerSection </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/24.
 */

public abstract class StaggerSection<VH extends EventViewHolder, ID,IEL>
        extends GroupSection<VH, ID,IEL> {
    private final int lanes;


    public StaggerSection(List<ID> sectionData, int lanes) {
        super(sectionData);
        this.lanes = lanes;
    }


    @Override
    public LayoutHelper createLayoutHelper() {
        final StaggeredGridLayoutHelper layoutHelper = new StaggeredGridLayoutHelper(lanes);
        decorLayoutHelper(layoutHelper);
        return layoutHelper;
    }

    protected abstract void decorLayoutHelper(final StaggeredGridLayoutHelper layoutHelper);
}
