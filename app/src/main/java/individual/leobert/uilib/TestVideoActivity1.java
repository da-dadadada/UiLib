package individual.leobert.uilib;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class TestVideoActivity1 extends AppCompatActivity {


    private VideoView videoView;
    private static final String URL = "http://oi29njqs0.bkt.clouddn.com/" +
            "a992e64b5341aa0f9f87dec7fb1d9c47_mp4_1280x720_NorthEast_c6" +
            "8e4b8bc4052e364f424e687413bb95.mp4?e=1810803500&token=zuoUeG" +
            "SQzmV9v8VzPd8WRVfS2olYpCSoQt4Xiqmt:ZTf0zArritL9j8DhF8_7BwZmwAU=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_video1);

        //网络视频

        Uri uri = Uri.parse(URL);

        videoView = (VideoView) this.findViewById(R.id.videoView);

        //设置视频控制器
        videoView.setMediaController(new MediaController(this));

        videoView.setOnPreparedListener(new MyPlayerOnPrepareListener());

        //播放完成回调
        videoView.setOnCompletionListener(new MyPlayerOnCompletionListener());

        //设置视频路径
        videoView.setVideoURI(uri);

        //开始播放视频
        videoView.start();
    }

    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            Toast.makeText(TestVideoActivity1.this, "播放完成了", Toast.LENGTH_SHORT).show();
        }
    }

    class MyPlayerOnPrepareListener implements MediaPlayer.OnPreparedListener {

        @Override
        public void onPrepared(MediaPlayer mp) {
            Log.d("lmsg","onprepare:"+System.currentTimeMillis());

        }
    }
}
