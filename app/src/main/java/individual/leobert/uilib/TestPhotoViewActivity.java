package individual.leobert.uilib;

import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import uk.co.senab.photoview.PhotoViewAttacher;

public class TestPhotoViewActivity extends AppCompatActivity {

    private PhotoViewAttacher mAttacher;

    private Toast mCurrentToast;

    private Matrix mCurrentDisplayMatrix = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_photo_view);

        ImageView mImageView = (ImageView) findViewById(R.id.test_photoview_iv);

        Drawable bitmap = getWallpaper();
        mImageView.setImageDrawable(bitmap);

        // The MAGIC happens here!
        mAttacher = new PhotoViewAttacher(mImageView);

        // Lets attach some listeners, not required though!
//        mAttacher.setOnMatrixChangeListener(new MatrixChangeListener());
//        mAttacher.setOnPhotoTapListener(new PhotoTapListener());
    }
}
