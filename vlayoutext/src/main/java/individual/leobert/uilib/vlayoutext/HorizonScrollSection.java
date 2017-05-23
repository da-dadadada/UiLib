package individual.leobert.uilib.vlayoutext;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlayoutext </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> HorizonScrollSection </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/23.
 */

public class HorizonScrollSection<SDS> extends
        SingleSection<HorizonScrollSection.HorizonScrollSectionViewHolder> {


    private SDS sds;

    public HorizonScrollSection(SDS sds) {
        this.sds = sds;
    }

    public SDS getSectionDataSet() {
        return sds;
    }


    @Override
    HorizonScrollSectionViewHolder onCreateViewHolder(ViewGroup parent) {
        return new HorizonScrollSectionViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vlext_section_horizon_scroll_rv, parent, false));
    }

    @Override
    void onBindViewHolder(HorizonScrollSectionViewHolder holder) {

    }

    public static class HorizonScrollSectionViewHolder extends RecyclerView.ViewHolder {

        public HorizonScrollSectionViewHolder(View itemView) {
            super(itemView);
        }
    }
}
