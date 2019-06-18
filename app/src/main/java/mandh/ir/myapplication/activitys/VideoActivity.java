package mandh.ir.myapplication.activitys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
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
import mandh.ir.myapplication.activitys.FullScreenActivity;
import mandh.ir.myapplication.adapters.recyclerViewAdapters.SimilarVideoRecyclerAdapter;
import mandh.ir.myapplication.forHelp.G;

import static mandh.ir.myapplication.forHelp.G.context;

public class VideoActivity extends Activity {


    TabLayout bottomTabLayout;
    EditText searchEtx;
    SimilarVideoRecyclerAdapter adapter;
    RecyclerView recyclerView;
    TextView vidsTitle;
    TextView title;
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
    ImageView settingBtn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video);
        try {


            cast();


            Bundle extras = getIntent().getExtras();
            if (extras != null) {

                path = extras.getString("path");
                description = extras.getString("description");
                bookId = extras.getInt("bookId");
                pageId = extras.getInt("pageId");
                pageId = extras.getInt("videoId");

            }

            path = "android.resource://" + getPackageName() + "/" + path;
            videoView.setVideoURI(Uri.parse(path));

            Uri videoURI = Uri.parse(path);
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(this, videoURI);
            Bitmap bitmap = retriever
                    .getFrameAtTime(100000, MediaMetadataRetriever.OPTION_PREVIOUS_SYNC);

            Drawable drawable = new BitmapDrawable(getResources(), bitmap);
            videoPreview.setImageDrawable(drawable);

            setTYpeFaces();

            setBottomTabLayout();

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
                    Intent i = new Intent(VideoActivity.this, FullScreenActivity.class);
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


        }catch (Exception e){
            Toast.makeText(G.context, e.toString(), Toast.LENGTH_LONG).show();
        }

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

        title.setTypeface(myTypeface);
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
        title = (TextView) findViewById(R.id.title);
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

        adapter=new SimilarVideoRecyclerAdapter(0,1,1);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(horizontalLayoutManager.getItemCount()-1);



    }


    private void setBottomTabLayout() {


        //create tabs
        bottomTabLayout.addTab(bottomTabLayout.newTab());
        bottomTabLayout.addTab(bottomTabLayout.newTab());
        bottomTabLayout.addTab(bottomTabLayout.newTab());
        bottomTabLayout.addTab(bottomTabLayout.newTab());
        bottomTabLayout.addTab(bottomTabLayout.newTab());

        Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileMedium.ttf");

        //.......................create custom Views and add them to bottomTabLayout.......................

        View view1 = View.inflate(this, R.layout.custom_view_tab_layout_disable, null);
        TextView title = (TextView) view1.findViewById(R.id.title);
        title.setText("تنظیمات");
        title.setTypeface(myTypeface);

        bottomTabLayout.getTabAt(0).setCustomView(null);
        bottomTabLayout.getTabAt(0).setCustomView(view1);


        View view2 = View.inflate(this, R.layout.custom_view_tab_layout_disable, null);
        TextView title2 = (TextView) view2.findViewById(R.id.title);
        title2.setText("شبکه علمی");
        title2.setTypeface(myTypeface);

        bottomTabLayout.getTabAt(1).setCustomView(null);
        bottomTabLayout.getTabAt(1).setCustomView(view2);



        View view3 = View.inflate(this, R.layout.custom_view_tab_layout_enable, null);

        TextView title3 = (TextView) view3.findViewById(R.id.title);
        title3.setText("خانه");
        title3.setTypeface(myTypeface);

        bottomTabLayout.getTabAt(2).setCustomView(null);
        bottomTabLayout.getTabAt(2).setCustomView(view3);



        View view4 = View.inflate(this, R.layout.custom_view_tab_layout_disable, null);
        TextView title4 = (TextView) view4.findViewById(R.id.title);
        title4.setText("چالش ها");
        title4.setTypeface(myTypeface);

        bottomTabLayout.getTabAt(3).setCustomView(null);
        bottomTabLayout.getTabAt(3).setCustomView(view4);



        View view5 = View.inflate(this, R.layout.custom_view_tab_layout_disable, null);
        TextView title5 = (TextView) view5.findViewById(R.id.title);
        title5.setText("پروفایل");
        title5.setTypeface(myTypeface);

        bottomTabLayout.getTabAt(4).setCustomView(null);
        bottomTabLayout.getTabAt(4).setCustomView(view5);

        bottomTabLayout.getTabAt(2).select();


        //..........................................................................................

        bottomTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                //viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

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
        vidTime=0;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isVideoPlaying=false;
        finish();
    }


}
