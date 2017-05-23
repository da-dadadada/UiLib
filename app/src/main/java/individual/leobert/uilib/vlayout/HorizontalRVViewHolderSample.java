package individual.leobert.uilib.vlayout;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

import individual.leobert.uilib.R;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlayout </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> HorizontalRVViewHolderSample </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/22.
 */

public class HorizontalRVViewHolderSample extends ViewHolder {
    RecyclerView recyclerView;
    public HorizontalRVViewHolderSample(View itemView) {
        super(itemView);
        recyclerView = (RecyclerView) itemView.findViewById(R.id.vlayout_h_scroll_rv);
        init();
    }

    private void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(10, 10, 10, 10);
            }
        });
    }

    public void setRVAdapter(RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }
}
