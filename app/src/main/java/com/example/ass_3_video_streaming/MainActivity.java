package com.example.ass_3_video_streaming;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ass_3_video_streaming.databinding.ActivityMainBinding;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

public class MainActivity extends AppCompatActivity {

    String videoLink ="https://firebasestorage.googleapis.com/v0/b/mcc-course-5d082.appspot.com/o/upload%2Fvideo%2F%D8%AD%D8%A7%D9%84%D8%A7%D8%AA%20%D9%88%D8%A7%D8%AA%D8%B3%20%D8%A7%D8%A8%20%D8%B9%D9%86%20%D8%A7%D9%84%D8%B5%D8%AF%D8%A7%D9%82%D8%A9%20%D8%AD%D8%A7%D9%84%D8%A7%D8%AA%20%D9%88%D8%A7%D8%AA%D8%B3%20%D8%A7%D8%A8%202020%D8%A7%D8%AC%D9%85%D9%84%20%D9%81%D9%8A%D8%AF%D9%8A%D9%88%20%D8%B9%D9%86%20%D8%A7%D9%84%D8%B5%D8%AF%D8%A7%D9%82%D8%A9%20%D8%AD%D8%A7%D9%84%D8%A7%D8%AA%20%D9%88%D8%A7%D8%AA%D8%B3%20%D8%B5%D8%AF%D8%A7%D9%82%D8%A9%F0%9F%98%8D%F0%9F%92%99.mp4?alt=media&token=805c4018-ced9-43c5-8af1-f07878356ef8";
ActivityMainBinding binding;
PlayerView pv;

SimpleExoPlayer player ;
boolean playWhenReady = true;
long curentPostion = 0 ;
int curentWindow = 0 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

pv = binding.playerView;

    }

    private void  initPlayer(){
        player = new SimpleExoPlayer.Builder(this).build();
      // ربط البلير فيو ب ال (SimpleExoPlayer)
        pv.setPlayer(player);
        MediaItem item = MediaItem.fromUri(videoLink);
        player.setMediaItem(item);

        // تشغيل الفيديو
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(curentWindow , curentPostion);
        player.prepare();


    }


    // method (لتوقيف الفيديو )

    private  void  relesePlayer(){
        if(player != null){
            // لجلب حالة الفيديو أين وقف و هكذا
            playWhenReady = player.getPlayWhenReady();
           curentWindow = player.getCurrentWindowIndex();
            curentPostion = player.getCurrentPosition();
           player = null ;

        }
    }
// في بداية التطبيق
    @Override
    protected void onStart() {
        super.onStart();
        initPlayer();
    }
// عند تشغيل اكتيفيتي تانية و ارجع ل تشغيل الفيديو
    @Override
    protected void onResume() {
        super.onResume();
        if(player != null){
            initPlayer();
        }
    }
 // توقيف الفيديو
    @Override
    protected void onPause() {
        super.onPause();
        relesePlayer();
    }
    // توقيف الفيديو

    @Override
    protected void onStop() {
        super.onStop();
        relesePlayer();
    }
}