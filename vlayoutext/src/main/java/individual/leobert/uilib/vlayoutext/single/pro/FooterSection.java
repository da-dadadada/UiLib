package individual.leobert.uilib.vlayoutext.single.pro;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.Collection;

import individual.leobert.uilib.vlayoutext.single.SingleSection;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlayoutext.pro </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> FooterSection </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/6/13.
 */

public abstract class FooterSection<VH extends RecyclerView.ViewHolder, SD>
        extends SingleSection<VH, SD> {

    public FooterSection(SD sectionData) {
        super(sectionData);
    }

    public void remove() {
        sectionData = null;
        getAdapter().notifyDataSetChanged();
    }

    public void show(SD data) {
        sectionData = data;
        getAdapter().notifyDataSetChanged();
    }

    @Override
    protected void initAdapter() {
        adapter = new SingleSectionAdapter<VH, SD>() {
            @Override
            public VH onCreateViewHolder(ViewGroup parent, int viewType) {
                return FooterSection.this.onCreateViewHolder(parent);
            }

            @Override
            public SD getSectionItemData(int position) {
                //only one item
                return FooterSection.this.getSectionData();
            }

            @Override
            public void onBindViewHolder(VH holder, int position) {
                FooterSection.this.onBindViewHolder(holder);
            }

            @Override
            public int getItemCount() {
                return FooterSection.this.getItemCount();
            }
        };
    }


    protected int getItemCount() {
        if (getSectionData() == null)
            return 0;
        if (sectionData instanceof Collection) {
            return ((Collection) sectionData).isEmpty() ? 0 : 1;
        }
        return 1;
    }
}
