package individual.leobert.uilib;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import individual.leobert.uilib.nineimagesetview.NineImageSetView;
import individual.leobert.uilib.nineimagesetview.NineImageSetViewAdapter;

public class TestNineImage extends AppCompatActivity {

    NineImageSetView<String> nineImageSetView;

    NineImageSetViewAdapter<String> adapter;
    ArrayList<String> data = new ArrayList<>();

    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_nine_image);
        nineImageSetView = (NineImageSetView<String>) findViewById(R.id.nine_img);

        nineImageSetView.setGap(10);

        adapter = new NineImageSetViewAdapter<String>(data) {
            @Override
            protected void onDisplayImage(Context context, ImageView imageView, String s) {
                if (s.equals("0"))
                    imageView.setImageResource(R.color.colorPrimary);
                else
                    imageView.setImageResource(R.color.colorAccent);
            }
        };

        findViewById(R.id.nine_img_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testAdd();
            }
        });

        findViewById(R.id.nine_img_minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testMinus();
            }
        });

        nineImageSetView.setAdapter(adapter);

    }

    private void testAdd() {
        data.add(String.valueOf(index % 2));
        index++;
        adapter.setDatas(data);
    }

    private void testMinus() {
        if (!data.isEmpty()) {
            data.remove(0);
            index--;
        }
        adapter.setDatas(data);
    }
}
