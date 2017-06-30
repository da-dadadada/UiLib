package individual.leobert.uilib.vlayout;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import individual.leobert.uilib.R;
import individual.leobert.uilib.vlayoutext.EventViewHolder;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlayout </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> LinearViewHolderSample </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/22.
 */

public class LinearViewHolderSample extends
        EventViewHolder {
    ImageView ivBG;
    ImageView ivAvatar;
    TextView tvUsername;
    TextView tvTitle;
    TextView tvIntroduce;

    public LinearViewHolderSample(View itemView) {
        super(itemView);
        init();
    }

    @Override
    protected <I> void onEventListenerSet(I listener) {
        if (listener instanceof IEventListener) {
            bindListener((IEventListener) listener);
        }
    }

    private void bindListener(final IEventListener listener) {
        ivAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onAvatarClick(getPositionInSection());
            }
        });
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

    }

    public interface IEventListener {
        void onAvatarClick(int position);
    }
}
