//package individual.leobert.uilib.vlayout;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//
//import com.alibaba.android.vlayout.DelegateAdapter;
//import com.alibaba.android.vlayout.LayoutHelper;
//import com.alibaba.android.vlayout.VirtualLayoutManager;
//import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
//
//import java.util.List;
//
//import individual.leobert.uilib.R;
//
///**
// * <p><b>Package:</b> individual.leobert.uilib.vlayout </p>
// * <p><b>Project:</b> UiLib </p>
// * <p><b>Classname:</b> SubLinearAdapter </p>
// * <p><b>Description:</b> TODO </p>
// * Created by leobert on 2017/5/22.
// */
//@Deprecated
//public class SubLinearAdapter extends DelegateAdapter.Adapter<LinearViewHolderSample> {
//    private Context context;
//    private List<Data1> datas;
//
//    public SubLinearAdapter(Context context, List<Data1> datas) {
//        this.context = context;
//        this.datas = datas;
//    }
//
//    @Override
//    public LinearViewHolderSample onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new LinearViewHolderSample(LayoutInflater.from(context)
//                .inflate(R.layout.vlayout_item_linear, parent, false));
//    }
//
//    @Override
//    public void onBindViewHolder(LinearViewHolderSample holder, int position) {
//        VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        holder.itemView.setLayoutParams(layoutParams);
//        holder.update(datas.get(position));
//    }
//
//    @Override
//    public int getItemCount() {
//        return datas.size();
//    }
//
//    @Override
//    public LayoutHelper onCreateLayoutHelper() {
//        return new LinearLayoutHelper();
//    }
//}
