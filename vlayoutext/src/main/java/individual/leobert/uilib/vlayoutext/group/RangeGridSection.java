package individual.leobert.uilib.vlayoutext.group;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.RangeGridLayoutHelper;

import java.util.List;

import individual.leobert.uilib.vlayoutext.EventViewHolder;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlayoutext.group </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> RangeGridSection </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/24.
 */

public abstract class RangeGridSection<VH extends EventViewHolder, ID,IEL>
        extends GridSection<VH, ID,IEL> {
    public RangeGridSection(List<ID> sectionData, int spanCount) {
        super(sectionData, spanCount);
    }



    @Override
    public LayoutHelper createLayoutHelper() {
        final RangeGridLayoutHelper gridLayoutHelper = new RangeGridLayoutHelper(spanCount);
        decorLayoutHelper(gridLayoutHelper);
        return gridLayoutHelper;
    }

    @Override
    @Deprecated
    protected final void decorLayoutHelper(GridLayoutHelper layoutHelper) {
        //useless
    }

    protected abstract void decorLayoutHelper(RangeGridLayoutHelper rangeGridLayoutHelper);
}
