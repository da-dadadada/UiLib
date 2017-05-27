package individual.leobert.uilib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TestListActivity extends AppCompatActivity implements View.OnClickListener {

    private static SparseArray<Class> testPool = new SparseArray<>();

    static {
        testPool.put(R.id.test_numbadge, TestNumBadge.class);
        testPool.put(R.id.test_banner, TestBannerActivity.class);
        testPool.put(R.id.test_photoview, TestPhotoViewActivity.class);
        testPool.put(R.id.test_vlayout, TestVlayout.class);
        testPool.put(R.id.test_video1, TestVideoActivity1.class);
        testPool.put(R.id.test_video2, TestVideoActivity2.class);
        testPool.put(R.id.test_nine_img,TestNineImage.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_list);
        LinearLayout testCollections = (LinearLayout) findViewById(R.id.test_ll);
        setOnClick2Child(testCollections);
    }

    /**
     * @param viewGroup
     */
    private void setOnClick2Child(ViewGroup viewGroup) {
        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            if (viewGroup.getChildAt(i) instanceof ViewGroup) {
                setOnClick2Child((ViewGroup) viewGroup.getChildAt(i));
            }
            //全部添加上
            viewGroup.getChildAt(i).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (testPool.get(id) != null) {
            Class test = testPool.get(id);
            start(test);
        } else {
            Toast.makeText(this, "has't add to test for this", Toast.LENGTH_SHORT).show();
        }
    }

    protected final void start(Class<? extends Activity> clazz) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivity(intent);
    }

    protected final void start(Class<? extends Activity> clazz, String key, String stringValue) {
        Intent intent = new Intent(getActivity(), clazz);
        intent.putExtra(key, stringValue);
        startActivity(intent);
    }

    protected final void start(Class<? extends Activity> clazz, String key, boolean booleanValue) {
        Intent intent = new Intent(getActivity(), clazz);
        intent.putExtra(key, booleanValue);
        startActivity(intent);
    }

    private Activity getActivity() {
        return this;
    }


}
