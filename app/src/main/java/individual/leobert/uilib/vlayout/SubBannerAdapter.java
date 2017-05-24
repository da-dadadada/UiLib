//package individual.leobert.uilib.vlayout;
//
//import android.content.Context;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//
//import com.alibaba.android.vlayout.DelegateAdapter;
//import com.alibaba.android.vlayout.LayoutHelper;
//import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
//
//import java.util.List;
//
//import individual.leobert.uilib.R;
//
///**
// * <p><b>Package:</b> individual.leobert.uilib.vlayout </p>
// * <p><b>Project:</b> UiLib </p>
// * <p><b>Classname:</b> SubBannerAdapter </p>
// * <p><b>Description:</b> TODO </p>
// * Created by leobert on 2017/5/22.
// */
//
//public class SubBannerAdapter extends DelegateAdapter.Adapter<BannerViewHolderSample> {
//    private Context context;
//
//    List<String> datas;
//
//    public SubBannerAdapter(Context context, List<String> datas) {
//        this.context = context;
//        this.datas = datas;
//    }
//
//    @Override
//    public LayoutHelper onCreateLayoutHelper() {
//        return new SingleLayoutHelper();
//    }
//
//    @Override
//    public BannerViewHolderSample onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new BannerViewHolderSample(LayoutInflater.from(context)
//                .inflate(R.layout.vl_item_banner, parent, false),
//                this.datas);
//    }
//
//    @Override
//    public void onBindViewHolder(BannerViewHolderSample holder, int position) {
//        Log.e("lmsg", "onBindBannerViewHolder");
//    }
//
//    @Override
//    public int getItemCount() {
//        return 1;
//    }
//}
//
//
