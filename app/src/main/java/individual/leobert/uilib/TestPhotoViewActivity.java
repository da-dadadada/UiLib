package individual.leobert.uilib;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.ImageViewTarget;

import java.util.Arrays;
import java.util.List;

import individual.leobert.uilib.photoview.PhotoView;
import individual.leobert.uilib.photoview.PhotoViewAttacher;


public class TestPhotoViewActivity extends AppCompatActivity implements View.OnClickListener {

    private PhotoViewAttacher mAttacher;

    private PhotoView photoView;

    private Button next;

    private Button pre;

    private static List<String> testPhoto = Arrays.asList(
            "http://img4.imgtn.bdimg.com/it/u=2646099799,3486858277&fm=23&gp=0.jpg",
            "http://172.16.23.100/test/image_test/102_96.png",
            "http://172.16.23.100/test/image_test/200_200.png",
            "http://172.16.23.100/test/image_test/248_1766.png",
            "http://172.16.23.100/test/image_test/400_400.png",
            "http://172.16.23.100/test/image_test/556_2446.png",
            "http://172.16.23.100/test/image_test/806_750.png",
            "http://172.16.23.100/test/image_test/2122_1694.png",
            "http://172.16.23.100/test/image_test/2446_556.png"
    );

    private int position = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_photo_view);

        photoView = (PhotoView) findViewById(R.id.test_photoview_iv);
        next = (Button) findViewById(R.id.next);
        pre = (Button) findViewById(R.id.pre);

        mAttacher = (PhotoViewAttacher) photoView.getIPhotoViewImplementation();

        next.setOnClickListener(this);
        pre.setOnClickListener(this);
        load(position);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pre:
                if (position > 0)
                    position--;
                load(position);
                break;
            case R.id.next:
                if (position < testPhoto.size() - 1)
                    position++;
                load(position);
                break;
            default:
                break;
        }
    }

    private void load(int position) {
        Glide.with(this).load(testPhoto.get(position))
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new ImageViewTarget<Bitmap>(photoView) {


                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        Toast.makeText(TestPhotoViewActivity.this, "load fail", Toast.LENGTH_SHORT).show();
                    }


                    @Override
                    protected void setResource(Bitmap resource) {
                        getView().setImageBitmap(resource);
                    }
                });

    }
}
