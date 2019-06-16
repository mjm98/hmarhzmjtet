package mandh.ir.myapplication.activitys;

import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.VideoView;

import mandh.ir.myapplication.R;

public class FullScreenActivity extends AppCompatActivity {

    String moveiuri;

    Handler mHandler;
    VideoView videoView;
    SeekBar videseekbar;
    boolean playFlag=true,toaching=false;
    ImageView play,stop,setting,fullScreen;
    Runnable updateTimeTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            moveiuri = extras.getString("moveiuri");
        }else{
           // finish();
        }
        setContentView(R.layout.activity_full_screen);
        cast();

        videoView=(VideoView)findViewById(R.id.video3);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.video1;
        videoView.setVideoURI(Uri.parse(path));
        videoView.start();
        videoView.seekTo(VideoActivity.time);
        videoView.resume();


        seekbarsetting();
        updateProgressBar();

        videoView.start();


        listeners();
    }
    private void updateProgressBar() {
        mHandler.postDelayed(updateTimeTask, 100);
    }

    private void seekbarsetting() {
       mHandler=new Handler();

        updateTimeTask= new Runnable() {
            public void run() {
                videseekbar.setProgress(videoView.getCurrentPosition());
                videseekbar.setMax(videoView.getDuration());
                VideoActivity.time=videoView.getCurrentPosition();
                if(!toaching){
                mHandler.postDelayed(this, 100);}
            }
        };

        videseekbar.setMax(videoView.getDuration());
        videseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                toaching=true;

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                videoView.seekTo(seekBar.getProgress());
                mHandler.postDelayed(updateTimeTask,100);
                VideoActivity.time=videoView.getCurrentPosition();
                toaching=false;

            }
        });

  }

    private void listeners() {
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playFlag){
                   playFlag=false;
                    play.setImageResource(R.drawable.play_white);
                   videoView.pause();
                }else {
                    playFlag=true;

                    play.setImageResource(R.drawable.pause_white);
                    videoView.start();

                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.seekTo(0);
                playFlag=false;
                play.setImageResource(R.drawable.play_white);
                videoView.pause();
            }
        });

        fullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoView.seekTo(VideoActivity.time);

    }

    @Override
    protected void onPause() {
        super.onPause();
        videoView.pause();
        VideoActivity.time=videoView.getCurrentPosition();
        play.setImageResource(R.drawable.play_white);
    }

    @Override
    protected void onDestroy() {
        VideoActivity.time=videoView.getCurrentPosition();
        super.onDestroy();
    }

    private void cast() {
        play=(ImageView)findViewById(R.id.playvideo_btn2);
        stop=(ImageView)findViewById(R.id.playvideo_stop2);
        videseekbar=(SeekBar)findViewById(R.id.videoseekbar2);
        fullScreen=(ImageView)findViewById(R.id.videofullscreen_btn2);
        setting=(ImageView)findViewById(R.id.settingvideo_btn2);

    }

}
