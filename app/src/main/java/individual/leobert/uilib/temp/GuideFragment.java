package individual.leobert.uilib.temp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import individual.leobert.uilib.R;

/**
 * <p><b>Package:</b> individual.leobert.uilib.temp </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> GuideFragment </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/31.
 */

public class GuideFragment extends DialogFragment {

    private static final String ARG_HEIGHT_RATIO = "arg_height_ratio";
    private static final String ARG_WIDTH_RATIO = "arg_width_ratio";

    private static final String ARG_PAGES_LAYOUT = "arg_pages_layout";

    private float widthRatio = 0.85f;
    private float heightRatio = 0.7f;
    private ArrayList<Integer> viewLayoutList;

    public static GuideFragment newInstance(float widthRatio, float heightRatio,
                                            @NonNull ArrayList<Integer> viewLayoutList) {
        if (viewLayoutList.isEmpty()) {
            throw new IllegalArgumentException("viewLayoutList cannot be empty");
        }
        GuideFragment fragment = new GuideFragment();
        Bundle args = new Bundle();
        args.putFloat(ARG_HEIGHT_RATIO, heightRatio);
        args.putFloat(ARG_WIDTH_RATIO, widthRatio);
        args.putIntegerArrayList(ARG_PAGES_LAYOUT, viewLayoutList);
        fragment.setArguments(args);
        return fragment;
    }

    public interface IGuidePageEventDecor {
        void decorEventForPage(int index, View rootView);
    }


    private ViewPager viewPager;

    private IGuidePageEventDecor iGuidePageEventDecor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            widthRatio = getArguments().getFloat(ARG_WIDTH_RATIO, 0.7f);
            heightRatio = getArguments().getFloat(ARG_HEIGHT_RATIO, 0.7f);
            viewLayoutList = getArguments().getIntegerArrayList(ARG_PAGES_LAYOUT);
        } else {
            throw new RuntimeException("you must use factory to create the instance");
        }
        setCancelable(false);


        //such as register eventbus
    }

    public void onStart() {
        super.onStart();
        //设置宽高
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels * widthRatio),
                    (int) (dm.heightPixels * heightRatio));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_guide, container);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IGuidePageEventDecor) {
            this.iGuidePageEventDecor = (IGuidePageEventDecor) context;
        } else {
            throw new RuntimeException("this fg must be attached something impl IGuidePageEventDecor");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = (ViewPager) view.findViewById(R.id.guide_pages);

        ArrayList<View> views = new ArrayList<>();
        LayoutInflater inflater = LayoutInflater.from(view.getContext());
        for (int i = 0; i < viewLayoutList.size(); i++) {
            View page = inflater.inflate(viewLayoutList.get(i), null, false);
            iGuidePageEventDecor.decorEventForPage(i, page);
            views.add(page);
        }


        viewPager.setAdapter(new ViewPagerAdapter(views));
    }


    public static class ViewPagerAdapter extends PagerAdapter {
        private List<View> views;

        public ViewPagerAdapter(List<View> views) {
            this.views = views;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }

        @Override
        public int getCount() {
            if (views != null) {
                return views.size();
            }
            return 0;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views.get(position), 0);
            return views.get(position);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    static class DisplayParams implements Parcelable {

        enum IndicatorStyle {
            Square, Round;

            public static IndicatorStyle newInstance(int i) {
                if (i == Square.ordinal())
                    return Square;
                else
                    return Round;
            }
        }

        private
        @ColorRes
        int indicatorColorResDefault;

        private
        @ColorRes
        int indicatorColorResSelected;

        private IndicatorStyle indicatorStyle;

        private int indicatorSizeDp = 10;

        private int indicatorDimenDp = 30;




        protected DisplayParams(Parcel in) {
            indicatorColorResDefault = in.readInt();
            indicatorColorResSelected = in.readInt();
            indicatorStyle = IndicatorStyle.newInstance(in.readInt());
            indicatorSizeDp = in.readInt();
            indicatorDimenDp = in.readInt();

        }

        public static final Creator<DisplayParams> CREATOR = new Creator<DisplayParams>() {
            @Override
            public DisplayParams createFromParcel(Parcel in) {
                return new DisplayParams(in);
            }

            @Override
            public DisplayParams[] newArray(int size) {
                return new DisplayParams[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(indicatorColorResDefault);
            dest.writeInt(indicatorColorResSelected);
            dest.writeInt(indicatorStyle.ordinal());
            dest.writeInt(indicatorSizeDp);
            dest.writeInt(indicatorDimenDp);
        }

        public int getIndicatorColorResDefault() {
            return indicatorColorResDefault;
        }

        public void setIndicatorColorResDefault(int indicatorColorResDefault) {
            this.indicatorColorResDefault = indicatorColorResDefault;
        }

        public int getIndicatorColorResSelected() {
            return indicatorColorResSelected;
        }

        public void setIndicatorColorResSelected(int indicatorColorResSelected) {
            this.indicatorColorResSelected = indicatorColorResSelected;
        }

        public IndicatorStyle getIndicatorStyle() {
            return indicatorStyle;
        }

        public void setIndicatorStyle(IndicatorStyle indicatorStyle) {
            this.indicatorStyle = indicatorStyle;
        }

        public int getIndicatorSizeDp() {
            return indicatorSizeDp;
        }

        public void setIndicatorSizeDp(int indicatorSizeDp) {
            this.indicatorSizeDp = indicatorSizeDp;
        }

        public int getIndicatorDimenDp() {
            return indicatorDimenDp;
        }

        public void setIndicatorDimenDp(int indicatorDimenDp) {
            this.indicatorDimenDp = indicatorDimenDp;
        }
    }
}
