package mandh.ir.myapplication.activitys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import mandh.ir.myapplication.R;
import mandh.ir.myapplication.adapters.recyclerViewAdapters.SimilarVideoRecyclerAdapter;

import static mandh.ir.myapplication.forHelp.G.context;

public class VideoActivity extends Activity {


    TabLayout bottomTabLayout;
    EditText searchEtx;
    SimilarVideoRecyclerAdapter adapter;
    RecyclerView recyclerView;
    TextView vidsTitle;
    TextView titleTx;
    TextView descriptionTxt;
    TextView dateTx;
    VideoView videoView;
    ImageView play,stop,setting;
    SeekBar videseekbar;
    Handler mHandler;
    Runnable updateTimeTask;
    boolean toaching=false;
    boolean playFlag=false;
    LinearLayout media_controller;
    ImageView pauseImage;
    ImageView videoClickableView;
    public static int vidTime =0;
    ImageView fullScrennBtn;
    String path;
    public static boolean isVideoPlaying = false;
    ImageView stopBtn;
    ImageView tagBtn;
    ImageView downloadBtn;
    ImageView favBtn;
    String  description;
    int bookId;
    int pageId;
    int videoId;
    ImageView videoPreview;
    String title;
    ImageView settingBtn;
    Bitmap bitmap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video);



            cast();


            Bundle extras = getIntent().getExtras();
            if (extras != null) {

                path = extras.getString("path");
                description = extras.getString("description");
                bookId = extras.getInt("bookId");
                pageId = extras.getInt("pageId");
                videoId = extras.getInt("videoId");
                title=extras.getString("title");

            }

            path = "android.resource://" + getPackageName() + "/" + path;
            videoView.setVideoURI(Uri.parse(path));

            Uri videoURI = Uri.parse(path);
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(this, videoURI);
            bitmap = retriever
                    .getFrameAtTime(1, MediaMetadataRetriever.OPTION_PREVIOUS_SYNC);


            videoPreview.setImageBitmap(bitmap);
            retriever.release();


            descriptionTxt.setText(description);
            titleTx.setText(title);

            setTYpeFaces();


            setRecycler();

            seekbarsetting();

            updateProgressBar();


///////////////////////////////////////listeners////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////

            pauseImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    play.performClick();
                }
            });

            stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    VideoActivity.vidTime = 0;
                    videoView.seekTo(VideoActivity.vidTime);
                    isVideoPlaying = false;
                    play.setImageDrawable(getResources().getDrawable(R.drawable.h_play));
                    videoClickableView.setVisibility(View.GONE);
                    pauseImage.setVisibility(View.VISIBLE);
                    videoView.pause();
                }
            });

            settingBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getBaseContext(), "setting clicked", Toast.LENGTH_SHORT).show();
                }
            });


            play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isVideoPlaying) {

                        play.setImageDrawable(getResources().getDrawable(R.drawable.h_play));
                        pauseImage.setVisibility(View.VISIBLE);
                        videoClickableView.setVisibility(View.GONE);
                        videoView.pause();
                        isVideoPlaying = false;

                    } else {

                        videoPreview.setVisibility(View.INVISIBLE);
                        videoPreview.setImageBitmap(null);
                        bitmap.recycle();

                        isVideoPlaying = true;
                        play.setImageDrawable(getResources().getDrawable(R.drawable.h_pause));
                        pauseImage.setVisibility(View.GONE);
                        videoClickableView.setVisibility(View.VISIBLE);
                        media_controller.setVisibility(View.INVISIBLE);
                        videoView.start();

                    }
                }
            });

            videoClickableView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (media_controller.getVisibility() == View.VISIBLE)
                        media_controller.setVisibility(View.GONE);
                    else media_controller.setVisibility(View.VISIBLE);

                }
            });

            fullScrennBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    vidTime = videoView.getCurrentPosition();
                    videoView.pause();
                    Intent i = new Intent(VideoActivity.this, FullScreenVideoActivity.class);
                    i.putExtra("videoUri", path);
                    i.putExtra("isVideoPlaying", isVideoPlaying);
                    startActivity(i);
                }
            });

            tagBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(VideoActivity.this, "click", Toast.LENGTH_SHORT).show();
                }
            });

            downloadBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(VideoActivity.this, "click", Toast.LENGTH_SHORT).show();
                }
            });


            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(VideoActivity.this, "click", Toast.LENGTH_SHORT).show();
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


    private void setTYpeFaces() {
        Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileBold.ttf");
        Typeface myTypeface2 = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileMedium.ttf");

        titleTx.setTypeface(myTypeface);
        descriptionTxt.setTypeface(myTypeface2);
        vidsTitle.setTypeface(myTypeface);
        dateTx.setTypeface(myTypeface);
    }


    private void cast() {
        settingBtn=(ImageView) findViewById(R.id.setting);
        tagBtn=(ImageView) findViewById(R.id.tagBtn);
        downloadBtn= (ImageView) findViewById(R.id.downloadBtn);
        favBtn= (ImageView) findViewById(R.id.favBtn);
        stop=(ImageView) findViewById(R.id.stop);
        pauseImage=(ImageView) findViewById(R.id.pause_image);
        recyclerView=(RecyclerView)findViewById(R.id.similarvideorecycler);
        bottomTabLayout = (TabLayout) findViewById(R.id.tab);
        searchEtx = (EditText) findViewById(R.id.edit_text);
        vidsTitle =(TextView) findViewById(R.id.vids_title);
        titleTx = (TextView) findViewById(R.id.title);
        descriptionTxt =(TextView) findViewById(R.id.description);
        dateTx =(TextView) findViewById(R.id.date);
        videoView=(VideoView)findViewById(R.id.video2);
        play=(ImageView) findViewById(R.id.playvideo_btn) ;
        videseekbar=(SeekBar)findViewById(R.id.videoseekbar);
        media_controller= (LinearLayout) findViewById(R.id.media_controller);
        videoClickableView=(ImageView)  findViewById(R.id.clickable_view);
        fullScrennBtn=(ImageView) findViewById(R.id.fullScreenBtn);
        stopBtn=(ImageView) findViewById(R.id.stop);
        videoPreview=(ImageView) findViewById(R.id.video_preview);


    }

    private void setRecycler() {

        adapter=new SimilarVideoRecyclerAdapter(bookId,pageId,videoId);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(horizontalLayoutManager.getItemCount()-1);



    }




    @Override
    protected void onPause() {
        super.onPause();
        videoView.pause();
        vidTime=videoView.getCurrentPosition();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoView.seekTo((int) vidTime);



        if (isVideoPlaying) {
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
    protected void onDestroy() {
        super.onDestroy();
        isVideoPlaying=false;
        videoView.suspend();
        vidTime=0;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isVideoPlaying=false;
        videoView.suspend();
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }


}
