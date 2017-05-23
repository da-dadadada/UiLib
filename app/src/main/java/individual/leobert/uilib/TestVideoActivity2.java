package individual.leobert.uilib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import individual.leobert.uilib.videoplayer.model.Video;
import individual.leobert.uilib.videoplayer.model.VideoUrl;
import individual.leobert.uilib.videoplayer.view.SuperVideoPlayer;

public class TestVideoActivity2 extends AppCompatActivity {

    private SuperVideoPlayer superVideoPlayer;

    private static final String URL = "http://oi29njqs0.bkt.clouddn.com/a992e64b5341aa0f9f87dec7fb1d" +
            "9c47_mp4_1280x720_NorthEast_c68e4b8bc4052e364f424e687413bb95.mp4?e=181080350" +
            "0&token=zuoUeGSQzmV9v8VzPd8WRVfS2olYpCSoQt4Xiqmt:ZTf0zArritL9j8DhF8_7BwZmwAU=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_video2);

        superVideoPlayer = (SuperVideoPlayer) findViewById(R.id.super_video_player);

        ArrayList<VideoUrl> videoUrls = new ArrayList<>();

        VideoUrl videoUrl = new VideoUrl();
        videoUrl.setFormatName("test");
        videoUrl.setFormatUrl(URL);

        videoUrls.add(videoUrl);

        Video video = new Video();
        video.setVideoUrl(videoUrls);
        video.setPlayUrl(0);

        ArrayList<Video> videos = new ArrayList<>();
        videos.add(video);

        superVideoPlayer.loadMultipleVideo(videos);
    }
}
