package individual.leobert.uilib.vlayoutext.single;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import individual.leobert.uilib.vlayoutext.R;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlayoutext </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> HorizonScrollSection </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/23.
 */

public abstract class HorizonScrollSection<ID> extends
        SingleSection<HorizonScrollSection.HorizonScrollSectionViewHolder,ID> {

    private RecyclerView.Adapter sectionRvAdapter;

    private List<RecyclerView.ItemDecoration> itemDecorations;

    public HorizonScrollSection(ID sectionData,
                                List<RecyclerView.ItemDecoration> itemDecorations) {
        super(sectionData);
        this.sectionRvAdapter = newSectionRvAdapter(getSectionData());
        this.itemDecorations = itemDecorations;
    }

    public abstract RecyclerView.Adapter newSectionRvAdapter(ID itemData);


    @Override
    HorizonScrollSectionViewHolder onCreateViewHolder(ViewGroup parent) {
        return new HorizonScrollSectionViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vlext_section_horizon_scroll_rv, parent, false),
                itemDecorations);
    }

    @Override
    void onBindViewHolder(HorizonScrollSectionViewHolder holder) {
        holder.setRVAdapter(sectionRvAdapter);
    }

    public static class HorizonScrollSectionViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;

        public HorizonScrollSectionViewHolder(View itemView, List<RecyclerView.ItemDecoration> itemDecorations) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.vlext_h_scroll_rv);
            init(itemDecorations);
        }

        private void init(List<RecyclerView.ItemDecoration> itemDecorations) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(linearLayoutManager);

            for (RecyclerView.ItemDecoration itemDecoration : itemDecorations) {
                recyclerView.addItemDecoration(itemDecoration);
            }
        }

        public void setRVAdapter(RecyclerView.Adapter adapter) {
            recyclerView.setAdapter(adapter);
        }

        @Deprecated
        public void notifyDataSetChanged() {
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }
}
