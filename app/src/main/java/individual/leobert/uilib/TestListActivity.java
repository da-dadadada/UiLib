package individual.leobert.uilib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TestListActivity extends AppCompatActivity implements View.OnClickListener {

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
        switch (v.getId()) {
            case R.id.test_numbadge:
                start(TestNumBadge.class);
                break;
            default:
                Toast.makeText(this, "has't set onClick for this", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    protected void startActivity(Class<? extends Activity> clazz) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivity(intent);
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
