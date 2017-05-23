package individual.leobert.uilib.vlayoutext.group;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;

import java.util.List;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlayoutext.group </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> ListSection </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/23.
 */

public abstract class ListSection<VH extends RecyclerView.ViewHolder, ID>
        extends GroupSection<VH, ID> {


    public ListSection(List<ID> sectionData) {
        super(sectionData);
    }

    public ListSection(List<ID> sectionData,
                       ViewHolderEventDecor decor) {
        super(sectionData, decor);
    }

    @Override
    LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }
}
