package individual.leobert.uilib.vlayoutext.group;

import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;

import java.util.List;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlayoutext.group </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> StaggerSection </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/24.
 */

public abstract class StaggerSection<VH extends RecyclerView.ViewHolder, ID>
        extends GroupSection<VH, ID> {
    private final int lanes;


    public StaggerSection(List<ID> sectionData, int lanes) {
        super(sectionData);
        this.lanes = lanes;
    }

    public StaggerSection(List<ID> sectionData, int lanes,
                       ViewHolderEventDecor decor) {
        super(sectionData, decor);
        this.lanes = lanes;
    }

    @Override
    LayoutHelper onCreateLayoutHelper() {
        final StaggeredGridLayoutHelper layoutHelper = new StaggeredGridLayoutHelper(lanes);
        decorLayoutHelper(layoutHelper);
        return layoutHelper;
    }

    protected abstract void decorLayoutHelper(final StaggeredGridLayoutHelper layoutHelper);
}
