package individual.leobert.uilib.vlayoutext.group;

import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.RangeGridLayoutHelper;

import java.util.List;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlayoutext.group </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> RangeGridSection </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/24.
 */

public abstract class RangeGridSection<VH extends RecyclerView.ViewHolder, ID>
        extends GridSection<VH, ID> {
    public RangeGridSection(List<ID> sectionData, int spanCount) {
        super(sectionData, spanCount);
    }

    public RangeGridSection(List<ID> sectionData, int spanCount, ViewHolderDecor decor) {
        super(sectionData, spanCount, decor);
    }

    @Override
    LayoutHelper onCreateLayoutHelper() {
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
