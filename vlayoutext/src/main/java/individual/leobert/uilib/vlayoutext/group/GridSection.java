package individual.leobert.uilib.vlayoutext.group;

import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;

import java.util.List;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlayoutext.group </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> GridSection </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/24.
 */

public abstract class GridSection<VH extends RecyclerView.ViewHolder, ID>
        extends GroupSection<VH, ID> {
    protected final int spanCount;


    public GridSection(List<ID> sectionData, int spanCount) {
        super(sectionData);
        this.spanCount = spanCount;
    }

    public GridSection(List<ID> sectionData, int spanCount,
                       ViewHolderDecor decor) {
        super(sectionData, decor);
        this.spanCount = spanCount;
    }

    @Override
    LayoutHelper onCreateLayoutHelper() {
        final GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(spanCount);
        decorLayoutHelper(gridLayoutHelper);
        return gridLayoutHelper;
    }

    protected abstract void decorLayoutHelper(final GridLayoutHelper layoutHelper);
}