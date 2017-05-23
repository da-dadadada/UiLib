package individual.leobert.uilib.vlayout;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import individual.leobert.uilib.R;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlayout </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> LinearViewHolderSample </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/22.
 */

public class LinearViewHolderSample extends RecyclerView.ViewHolder {
    ImageView ivBG;
    ImageView ivAvatar;
    TextView tvUsername;
    TextView tvTitle;
    TextView tvIntroduce;

    public LinearViewHolderSample(View itemView) {
        super(itemView);
        init();
    }

    private void init() {
        ivBG = (ImageView) itemView.findViewById(R.id.iv_bg);
        ivAvatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
        tvUsername = (TextView) itemView.findViewById(R.id.tv_username);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        tvIntroduce = (TextView) itemView.findViewById(R.id.tv_introduce);
    }

    public void update(Data1 data1) {
        tvUsername.setText(data1.getUser());
        tvTitle.setText(data1.getTitle());
        tvIntroduce.setText(data1.getIntro());

        ivAvatar.setImageResource(R.drawable.v1000_drawable_avatar_default);

//        Glide.with(itemView.getContext())
//                .load(R.drawable.v1000_drawable_avatar_default)
//                .fitCenter()
//                .into(ivAvatar);
    }
}
