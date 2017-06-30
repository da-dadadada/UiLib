package individual.leobert.uilib.vlayoutext.group;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;

import java.util.List;

import individual.leobert.uilib.vlayoutext.EventViewHolder;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlayoutext.group </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> GridSection </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/24.
 */

public abstract class GridSection<VH extends EventViewHolder, ID,IEL>
        extends GroupSection<VH, ID,IEL> {
    protected final int spanCount;


    public GridSection(List<ID> sectionData, int spanCount) {
        super(sectionData);
        this.spanCount = spanCount;
    }

    @Override
    public LayoutHelper createLayoutHelper() {
        final GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(spanCount);
        decorLayoutHelper(gridLayoutHelper);
        return gridLayoutHelper;
    }

    protected abstract void decorLayoutHelper(final GridLayoutHelper layoutHelper);
}