package individual.leobert.uilib;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import individual.leobert.uilib.temp.GuideFragment;

public class GuideActivity extends AppCompatActivity
        implements GuideFragment.IGuidePageEventDecor {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ArrayList<Integer> layouts = new ArrayList<>();

        List<Integer> list = Arrays.asList(R.layout.view_guide_one,
                R.layout.view_guide_two,
                R.layout.view_guide_three,
                R.layout.view_guide_four);
        layouts.addAll(list);


        DialogFragment guideFg = GuideFragment.newInstance(0.85f, 0.7f, layouts);
        FragmentManager fm = getSupportFragmentManager();
        guideFg.show(fm, "guide fg");
    }

    @Override
    public void decorEventForPage(final int index, View rootView) {
        // empty
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GuideActivity.this, "this is" + index, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
