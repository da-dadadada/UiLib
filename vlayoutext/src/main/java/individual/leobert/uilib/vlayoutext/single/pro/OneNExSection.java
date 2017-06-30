package individual.leobert.uilib.vlayoutext.single.pro;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelperEx;

import java.util.List;

import individual.leobert.uilib.vlayoutext.EventViewHolder;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlayoutext.single.onen </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> OneNExSection </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/24.
 */

public abstract class OneNExSection<VH extends EventViewHolder, ID, IEL>
        extends OneNSection<VH, ID, IEL> {
    public OneNExSection(List<ID> sectionData) {
        super(sectionData);
    }

    @Override
    protected void decorLayoutHelper(OnePlusNLayoutHelper layoutHelper) {
//        ignore
    }

    protected abstract void decorLayoutHelper(OnePlusNLayoutHelperEx layoutHelper);

    @Override
    public LayoutHelper createLayoutHelper() {
        final OnePlusNLayoutHelperEx layoutHelper =
                new OnePlusNLayoutHelperEx(getSectionData().size());
        decorLayoutHelper(layoutHelper);
        return layoutHelper;
    }
}
