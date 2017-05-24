package individual.leobert.uilib.vlayoutext.single.pro;

import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelperEx;

import java.util.List;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlayoutext.single.onen </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> OneNExSection </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/24.
 */

public abstract class OneNExSection<VH extends RecyclerView.ViewHolder, ID>
        extends OneNSection<VH,ID> {
    public OneNExSection(List<ID> sectionData) {
        super(sectionData);
    }

    public OneNExSection(List<ID> sectionData, ViewHolderEventDecor decor) {
        super(sectionData, decor);
    }

    @Override
    LayoutHelper onCreateLayoutHelper() {
        return new OnePlusNLayoutHelperEx(getSectionData().size());
    }
}
