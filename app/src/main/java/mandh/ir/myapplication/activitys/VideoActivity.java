package mandh.ir.myapplication.activitys;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import mandh.ir.myapplication.R;
import mandh.ir.myapplication.adapters.recyclerViewAdapters.SimilarVideoRecyclerAdapter;
import mandh.ir.myapplication.forHelp.G;

import static mandh.ir.myapplication.forHelp.G.context;

public class VideoActivity extends AppCompatActivity {
    public static int time=0;
    LinearLayout controller;
    VideoView videoView;
    TabLayout bottomTabLayout;
    TextView title,videoDiscription,date,others;
    RecyclerView recyclerView;
    SimilarVideoRecyclerAdapter adapter;
    Button play,stop,setting;
    ImageButton fullscreen,full;
    boolean playFlag=false;
    SeekBar videseekbar;
    Handler mHandler;
    Runnable updateTimeTask;
    boolean toaching=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video);
        time=0;

        videoView=(VideoView)findViewById(R.id.video2);
        recyclerView=(RecyclerView)findViewById(R.id.similarvideorecycler);
        bottomTabLayout = (TabLayout) findViewById(R.id.tab3);
        title=(TextView)findViewById(R.id.videoTitle2);
        videoDiscription=(TextView)findViewById(R.id.videoDetail);
        others=(TextView)findViewById(R.id.others);
        date=(TextView)findViewById(R.id.date1);
        fullscreen=(ImageButton) findViewById(R.id.videofullscreen_btn);
        full=(ImageButton)findViewById(R.id.fullscreenbtn12);
        play=(Button) findViewById(R.id.playvideo_btn) ;
        stop=(Button) findViewById(R.id.playvideo_stop);
        setting=(Button) findViewById(R.id.settingvideo_btn);
        videseekbar=(SeekBar)findViewById(R.id.videoseekbar);
        time=0;

        listeners();


        String path = "android.resource://" + getPackageName() + "/" + R.raw.video1;
         videoView.setVideoURI(Uri.parse(path));
         videoView.seekTo(time);

       /* MediaController mediaController=new MediaController(this);

        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);*/

        //ViewGroup.LayoutParams params = videoView.getLayoutParams();
        // Double z=    (float) params.width*3.0/4.0;
        // params.height= z.intValue();
        // Toast.makeText(G.context,String.valueOf(videoView.get),Toast.LENGTH_LONG).show();
        //videoView.setLayoutParams(params);

        adapter=new SimilarVideoRecyclerAdapter();
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(VideoActivity.this, LinearLayoutManager.HORIZONTAL, true);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        recyclerView.setAdapter(adapter);

       setbottomtablayout();
       setTYpeFaces();
       listeners();
       seekbarsetting();
        updateProgressBar();






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
                    play.setBackground( getResources().getDrawable( R.drawable.play_white ));
                    videoView.pause();
                }else {
                    playFlag=true;

                    play.setBackground( getResources().getDrawable( R.drawable.pause_white));
                    videoView.start();

                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.seekTo(0);
                playFlag=false;
                play.setBackground( getResources().getDrawable( R.drawable.play_white ));
                videoView.pause();
            }
        });





        full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time=videoView.getCurrentPosition();
                videoView.pause();
                Intent i=new Intent(VideoActivity.this,FullScreenActivity.class);
                VideoActivity.this.startActivity(i);

            }
        });



        fullscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time=videoView.getCurrentPosition();
                videoView.pause();
                Intent i=new Intent(VideoActivity.this,FullScreenActivity.class);
                VideoActivity.this.startActivity(i);
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);



            }
        });
    }

    private void setbottomtablayout() {
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

    private void setTYpeFaces() {
        Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileBold.ttf");
        others.setTypeface(myTypeface);
        //videoDiscription.setTypeface(myTypeface);
        title.setTypeface(myTypeface);
        date.setTypeface(myTypeface);

    }
}
