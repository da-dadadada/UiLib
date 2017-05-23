package individual.leobert.uilib.vlayout;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import individual.leobert.uilib.R;
import individual.leobert.uilib.autolooperbanner.AutoLooperBanner;
import individual.leobert.uilib.autolooperbanner.IBannerUpdate;
import individual.leobert.uilib.autolooperbanner.ImgRes;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlayout </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> BannerViewHolderSample </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/22.
 */

public class BannerViewHolderSample extends RecyclerView.ViewHolder {
    AutoLooperBanner bannerLayout;

    private IBannerUpdate iBannerUpdate = new IBannerUpdate() {
        @Override
        public void UpdateImage(ImgRes<?> res, ImageView imageView) {
            Log.e("lmsg", "UpdateImage");
            Glide.with(imageView.getContext())
                    .load(res.getRes())
                    .asBitmap()
                    .error(R.mipmap.ic_launcher)
                    .into(imageView);
        }
    };

    public BannerViewHolderSample(View itemView, List<String> urls) {
        super(itemView);
        bannerLayout = (AutoLooperBanner) itemView.findViewById(R.id.banner);

        bannerLayout.setIBannerUpdate(iBannerUpdate);
        bannerLayout.setViewUrls(urls);

    }

    @Override
    protected void finalize() throws Throwable {
        Log.d("lmsg", "BannerViewHolderSample gc");
        super.finalize();
    }
}
