package mandh.ir.myapplication.activitys;

import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;


import mandh.ir.myapplication.R;

public class FullScreenActivity extends AppCompatActivity {

    String moveiuri;

    Handler mHandler;
    VideoView videoView;
    SeekBar videseekbar;
    ImageView play,stop,setting,fullScreen;
    Runnable updateTimeTask;
    String path;
    LinearLayout media_controller;
    ImageView pauseImage;
    View videoClickableView;
    boolean toaching=false;
    ImageView fullScreenBtn;
    ImageView settingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_full_screen);

        Bundle extras = getIntent().getExtras();
        path = extras.getString("videoUri");



        cast();

        videoView.setVideoURI(Uri.parse(path));

        seekbarsetting();
        updateProgressBar();



        // listeners();

        pauseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play.performClick();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VideoActivity.vidTime=0;
                videoView.seekTo(VideoActivity.vidTime);
                VideoActivity.isVideoPlaying=false;
                play.setImageDrawable(getResources().getDrawable(R.drawable.h_play));
                videoClickableView.setVisibility(View.GONE);
                pauseImage.setVisibility(View.VISIBLE);
                videoView.pause();            }
        });

        fullScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FullScreenActivity.this, "setting clicked", Toast.LENGTH_SHORT).show();
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(VideoActivity.isVideoPlaying){
                    VideoActivity.isVideoPlaying=false;
                    play.setImageDrawable(getResources().getDrawable(R.drawable.h_play));
                    pauseImage.setVisibility(View.VISIBLE);
                    videoClickableView.setVisibility(View.GONE);
                    videoView.pause();
                    VideoActivity.isVideoPlaying=false;
                }else {
                    VideoActivity.isVideoPlaying=true;

                    play.setImageDrawable(getResources().getDrawable(R.drawable.h_pause));
                    pauseImage.setVisibility(View.GONE);
                    videoClickableView.setVisibility(View.VISIBLE);
                    media_controller.setVisibility(View.INVISIBLE);
                    VideoActivity.isVideoPlaying=true;
                    videoView.start();

                }
            }
        });

        videoClickableView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (media_controller.getVisibility()==View.VISIBLE) {
                    media_controller.setVisibility(View.GONE);
                }
                else media_controller.setVisibility(View.VISIBLE);

            }
        });

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
                toaching=false;

            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        videoView.seekTo((int) VideoActivity.vidTime);



        if (VideoActivity.isVideoPlaying) {
            videoView.start();
            pauseImage.setVisibility(View.GONE);
            videoClickableView.setVisibility(View.VISIBLE);
            play.setImageDrawable(getResources().getDrawable(R.drawable.h_pause));

        }
        else {
            videoView.pause();
            pauseImage.setVisibility(View.VISIBLE);
            videoClickableView.setVisibility(View.GONE);
            play.setImageDrawable(getResources().getDrawable(R.drawable.h_play));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoView.pause();
        VideoActivity.vidTime=videoView.getCurrentPosition();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        VideoActivity.vidTime=videoView.getCurrentPosition();

    }

    private void cast() {
        settingBtn  = (ImageView) findViewById(R.id.setting);
        fullScreenBtn=(ImageView) findViewById(R.id.fullScreenBtn);
        stop=(ImageView) findViewById(R.id.stop);
        pauseImage=(ImageView) findViewById(R.id.pause_image);
        videoView=(VideoView)findViewById(R.id.video);
        play=(ImageView)findViewById(R.id.playvideo_btn);
        videseekbar=(SeekBar)findViewById(R.id.videoseekbar);
        media_controller= (LinearLayout) findViewById(R.id.media_controller);
        videoClickableView=(View)  findViewById(R.id.clickable_view);

    }

}
