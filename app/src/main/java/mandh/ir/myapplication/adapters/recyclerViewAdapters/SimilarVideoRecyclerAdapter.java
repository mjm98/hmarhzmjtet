package mandh.ir.myapplication.adapters.recyclerViewAdapters;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mandh.ir.myapplication.R;
import mandh.ir.myapplication.activitys.SplashActivity;
import mandh.ir.myapplication.activitys.VideoActivity;
import mandh.ir.myapplication.forHelp.G;
import mandh.ir.myapplication.models.StaticsData;
import mandh.ir.myapplication.models.Videos;

import static mandh.ir.myapplication.forHelp.G.context;

public class SimilarVideoRecyclerAdapter extends RecyclerView.Adapter<SimilarVideoRecyclerAdapter.ItemHolder>  {
    ArrayList<Videos> videoList=new ArrayList<>();
    ArrayList<Bitmap> bitmaps=new ArrayList<>();
    int pageid,bookid,videoid;
    ArrayList<Videos> list=new ArrayList<>();
    public SimilarVideoRecyclerAdapter(int bookid,int pageid,int videoid) {
        this.bookid=bookid;
        this.videoid=videoid;
        this.pageid=pageid;

        Toast.makeText(G.context,String.valueOf(bookid)+".."+String.valueOf(pageid)+".."+String.valueOf(videoid),Toast.LENGTH_LONG).show();



        Handler handler=new Handler();
        int x=0;

        if(pageid==-1){
            list=StaticsData.makeData().get(bookid).getVideos();
            x=list.size()<=5?list.size():5;
            Toast.makeText(G.context,String.valueOf(bookid)+".."+String.valueOf(pageid)+".."+String.valueOf(videoid),Toast.LENGTH_LONG).show();
        }else {
            list=StaticsData.makeData().get(bookid).getPages().get(pageid).getVideos();
            x=list.size()<=5?list.size():5;
            }
        for(int i=0;i<x;i++){
            if(videoid!=i){
                videoList.add(list.get(i));
            }
        }
        for(int i=0;i<videoList.size();i++){
            bitmaps.add(null);}
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                SetPics setPics=new SetPics();
                setPics.execute();
            }
        };

        handler.post(runnable);

           // Toast.makeText(G.context,String.valueOf(pageid),Toast.LENGTH_LONG).show();

    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_similar_video_rescyclerview, parent, false);
        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, final int position) {

        holder.videoTitle.setText(videoList.get(position).getName());
        Bitmap bmp=null;
        if(bitmaps.get(position)!=null){
            bmp=bitmaps.get(position);
            if(bmp!=null){
                holder.videImage.setImageBitmap(bmp);
            }
        }
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(G.context, VideoActivity.class);
                i.putExtra("pageId", pageid);
                i.putExtra("bookId",bookid);
                i.putExtra("videoId", list.get(videoList.get(position).getId()).getId());
                i.putExtra("path", list.get(videoList.get(position).getId()).getUri());
                i.putExtra("description", list.get(videoList.get(position).getId()).getDiscription());
                i.putExtra("videoName",list.get(videoList.get(position).getId()).getName());
                G.context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {

        return videoList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        ImageView videImage;
        TextView videoTitle;
        LinearLayout linearLayout;
        public ItemHolder(View itemView) {
            super(itemView);
            videImage=(ImageView) itemView.findViewById(R.id.videoImage);
            videoTitle=(TextView)itemView.findViewById(R.id.videoTitle);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.similarmodellinear);
            Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileMedium.ttf");
            videoTitle.setTypeface(myTypeface);

            }
    }
    private class SetPics extends AsyncTask<ImageView,Bitmap,Bitmap> {


        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            SimilarVideoRecyclerAdapter.this.notifyDataSetChanged();
        }

        @Override
        protected Bitmap doInBackground(ImageView... imageViews) {
            for(int i=0;i<videoList.size();i++){
                Uri videoURI = Uri.parse("android.resource://" + G.context.getPackageName() +"/"
                        +videoList.get(i).getUri());
                MediaMetadataRetriever retriever = new MediaMetadataRetriever();
                retriever.setDataSource(G.context, videoURI);
                Bitmap bitmap = retriever
                        .getFrameAtTime(100000,MediaMetadataRetriever.OPTION_PREVIOUS_SYNC);
                bitmaps.set(i,bitmap);
                //SimilarVideoRecyclerAdapter.this.notifyItemChanged(i);

            }

            return null;
        }
    }
}
