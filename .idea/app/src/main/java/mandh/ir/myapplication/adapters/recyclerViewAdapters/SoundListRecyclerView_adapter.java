package mandh.ir.myapplication.adapters.recyclerViewAdapters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Typeface;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mandh.ir.myapplication.forHelp.G;
import mandh.ir.myapplication.models.SoundModel;
import mandh.ir.myapplication.R;
import mandh.ir.myapplication.models.VideosAndAudios;

import static android.view.View.VISIBLE;
import static mandh.ir.myapplication.forHelp.G.context;

public class SoundListRecyclerView_adapter extends RecyclerView.Adapter<SoundListRecyclerView_adapter.RecViewHolder> {

    private VideosAndAudios list;
    private ArrayList<Boolean> expandList;
    MediaPlayer player;
    Context context;
    int beforePosition=-1,rightPosition=-1;
    boolean isTouching=false;
    Handler handler;
    Runnable runnable;
    boolean isPlaying=false;

    private int lastPosition = -1;


    public SoundListRecyclerView_adapter(VideosAndAudios list,Context context) {
        expandList=new ArrayList<>();
        this.context=context;
        this.list = list;
        for(int i=0;i<list.getVoices().size();i++){
            expandList.add(false);
        }
    }

    @Override
    public RecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        handler=new Handler();
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.model_sound, parent, false);
        return new RecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecViewHolder holder, final int position) {
        final boolean currentViewExpanded = expandList.get(position);


        holder.bind(currentViewExpanded);


        Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileMedium.ttf");
        holder.time.setTypeface(myTypeface);
        holder.date.setTypeface(myTypeface);
        holder.title.setTypeface(myTypeface);
        holder.title.setText(list.getVoices().get(position).getName());
        holder.itemView.setId(position);
      /*  try{
            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
            Uri musicUri = Uri.parse("android.resource://" + G.context.getPackageName() +"/"
                    +list.getVoices().get(position).getUri());
            mmr.setDataSource(G.context,musicUri);
            String durationStr = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            mmr.release();

        int time=Integer.valueOf(durationStr);*/

        holder.time.setText(list.getVoices().get(position).getDuration());


      holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean expand = currentViewExpanded;


                if (!expandList.get(position)) {

                    for (int i = 0; i < list.getVoices().size(); i++) {

                        if (i != position) {
                            expandList.set(i,expand);
                        } else {
                            try {
                            expandList.set(i,!expand);

                                play(position,holder);
                            }catch (Exception e){
                                Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
                            }

                            playBtnFun(holder,position);
                        }
                    }
                }
                else  {expandList.set(position,false);
                  // player.pause();
                }

               notifyItemRangeChanged(0,list.getVoices().size());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.getVoices().size();
    }

    private void playBtnFun(final RecViewHolder holder, final int postion){


        holder.pausebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPlaying){
                    player.pause();
                    holder.pausebtn.setImageDrawable(G.context.getDrawable(R.drawable.paly));
                }else {
                    player.start();
                    holder.pausebtn.setImageDrawable(G.context.getDrawable(R.drawable.pouse));
                }

            }
        });
    }

    private void play(int position, final RecViewHolder holder){
        try {
            beforePosition=rightPosition;

        rightPosition=position;
        if(rightPosition!=beforePosition)
        { if(player!=null)
            player.release();
            player=MediaPlayer.create(context,Integer.valueOf(list.getVoices().get(position).getUri()));}

            player.start();
            holder.pausebtn.setImageDrawable(G.context.getDrawable(R.drawable.pouse));
            isPlaying=true;
            holder.seekBar.setMax(player.getDuration());
            holder.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    isTouching=true;

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    player.seekTo(seekBar.getProgress());
                    isTouching=false;
                    handler.postDelayed(runnable,100);
                }
            });

            run(holder);
            runnable= new Runnable() {
                @Override
                public void run() {
                    holder.seekBar.setProgress(player.getCurrentPosition());
                    if(!isTouching)
                    handler.postDelayed(runnable,200);

                }
            };
            handler.post(runnable);}catch (Exception e){
            Toast.makeText(G.context,e.toString(),Toast.LENGTH_LONG).show();


        }

    }
    public void run(final RecViewHolder holder){



    }

    public String getTime(int time){
        time=time/1000;
        String min=String.valueOf(time/60);
        String sec=String.valueOf(time%60);

        return min+":"+sec;

    }

    public void releaseHandler(){

        handler.removeCallbacks(runnable);


    }
    public void stop(){
        if(player!=null && player.isPlaying()){
             player.pause();
        }
    }
    public void release(){
        if(player!=null){

        player.release();}
    }

    public class RecViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView time;
        private TextView date;
        private View subItem;
        private SeekBar seekBar;
        ImageView pausebtn;
        TextView start,finish;


        public RecViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            time = itemView.findViewById(R.id.time);
            date = itemView.findViewById(R.id.date);
            subItem = itemView.findViewById(R.id.sub_item);
            seekBar=itemView.findViewById(R.id.seekBar2);
            pausebtn=itemView.findViewById(R.id.puasesound_model);
            start=itemView.findViewById(R.id.start2);
            finish=itemView.findViewById(R.id.finish2);

        }

        private void bind(boolean movie) {
            boolean expand = movie;

            subItem.setVisibility(expand ? VISIBLE : View.GONE);

        }





    }



}