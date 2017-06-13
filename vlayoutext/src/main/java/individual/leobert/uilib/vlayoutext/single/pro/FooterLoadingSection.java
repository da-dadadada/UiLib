package individual.leobert.uilib.vlayoutext.single.pro;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlayoutext.single.pro </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> FooterLoadingSection </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/6/13.
 */

public abstract class FooterLoadingSection extends
        FooterSection<FooterLoadingSection.FooterLoadingViewHolder, Integer> {

    private static final int STATE_RESET = 0x0;

    private static final int STATE_LOADING = 0x1;

    private static final int STATE_END = 0x2;

    public FooterLoadingSection() {
        super(STATE_RESET);
    }

    @Override
    protected void onBindViewHolder(FooterLoadingViewHolder holder) {
        if (getSectionData() == STATE_LOADING)
            holder.onLoading();
        else if (getSectionData() == STATE_END)
            holder.onEnd();
        else
            Log.e(getClass().getSimpleName(),
                    "error occurs,onBindViewHolder when state is reset(0x0)," +
                            "check:" + getSectionData());
    }

    @Override
    public void resetData(Integer sectionData) {
        Log.w(getClass().getSimpleName(), "use reset,disable,asLoading");
    }

    public void reset() {
        super.resetData(STATE_RESET);
    }

    public void asEnd() {
        super.resetData(STATE_END);
    }

    public void asLoading() {
        super.resetData(STATE_LOADING);
    }

    private boolean enableVisibleOnEnd = false;

    public void setVisibleOnEnd(boolean visible) {
        this.enableVisibleOnEnd = visible;
    }

    @Override
    protected int getItemCount() {
        if (enableVisibleOnEnd)
            return getSectionData() == STATE_RESET ? 0 : 1;
        return getSectionData() == STATE_LOADING ? 1 : 0;
    }

    public abstract static class FooterLoadingViewHolder extends RecyclerView.ViewHolder {

        public FooterLoadingViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void onLoading();

        public abstract void onEnd();
    }
}
