package mandh.ir.myapplication.adapters.recyclerViewAdapters;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.os.AsyncTaskCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mandh.ir.myapplication.activitys.MovieListActivity;
import mandh.ir.myapplication.activitys.VideoActivity;
import mandh.ir.myapplication.forHelp.G;
import mandh.ir.myapplication.models.Model;
import mandh.ir.myapplication.R;
import mandh.ir.myapplication.models.Videos;
import mandh.ir.myapplication.models.VideosAndAudios;

import static mandh.ir.myapplication.forHelp.G.context;


/**
 * Created by k.z on 14/02/2018.
 */

public class MovieListRecyclerView_adapter extends RecyclerView.Adapter<MovieListRecyclerView_adapter.classes_list_holder> {

    //..............................................................................................
    Videos model;
    int bookid=0;
    int pageid=1;
    int videoid=0;
    ArrayList<Bitmap> bitmaps=new ArrayList<>();

    //..............................................................................................
    private ArrayList<Videos> arrayList = new ArrayList<>();
    //..............................................................................................
    Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileMedium.ttf");
    Typeface myTypeface2 = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileBold.ttf");



    //constructor
    public MovieListRecyclerView_adapter(VideosAndAudios list,int bookid,int pageid ) {
        this.bookid=bookid;
        this.pageid=pageid;
        this.arrayList =list.getVideos();
        for(int i=0;i<arrayList.size();i++)
            bitmaps.add(null);
        Handler handler=new Handler();
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                SetPics setPics=new SetPics();
                setPics.execute();
            }
        };
        handler.postDelayed(runnable,200);


    }

    @Override
    public classes_list_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.model_movie,parent,false);
        return new classes_list_holder(view);
    }


    @Override
    public void onBindViewHolder(final classes_list_holder holder, final int position) {
        try {

        model= arrayList.get(position);

       final Uri  videoURI = Uri.parse("android.resource://" + G.context.getPackageName() +"/"
                +arrayList.get(position).getUri());
        /*MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(G.context, videoURI);
        Bitmap bitmap = retriever
                .getFrameAtTime(100000,MediaMetadataRetriever.OPTION_PREVIOUS_SYNC);
        Drawable drawable = new BitmapDrawable(G.context.getResources(), bitmap);
        holder.imageView.setImageDrawable(drawable);*/




       /*try {
            SetPics setPics=new SetPics(videoURI);
            setPics.executeOnExecutor (holder.imageView);
        }catch (Exception e) {
           Toast.makeText(G.context, e.toString(), Toast.LENGTH_LONG).show();
       }*/
       Bitmap bmp=null;
       if(bitmaps.get(position)!=null){
           bmp=bitmaps.get(position);
            if(bmp!=null){
                    holder.imageView.setImageBitmap(bmp);
            }
       }


        holder.title.setTypeface(myTypeface2);
        holder.title.setText(model.getName());
        holder.content.setText(model.getDiscription());
        holder.content.setTypeface(myTypeface);
        holder.rl.setId(model.getId());



            holder.rl.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    videoid = position;
                   Intent intent = new Intent(G.context, VideoActivity.class);
                    intent.putExtra("bookId", bookid);
                    intent.putExtra("pageId", pageid);
                    intent.putExtra("videoId", videoid);
                    intent.putExtra("path", arrayList.get(position).getUri());
                    intent.putExtra("description", arrayList.get(position).getDiscription());
                    intent.putExtra("videoName",arrayList.get(position).getName());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    G.context.startActivity(intent);

                }
            });
        }catch (Exception e){
            Toast.makeText(G.context, e.toString(), Toast.LENGTH_LONG).show();
        }


    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class classes_list_holder extends RecyclerView.ViewHolder {

        TextView title;
        TextView content;
        RelativeLayout rl;
        ImageView imageView;

        public classes_list_holder(View itemView) {

            super(itemView);
            rl=(RelativeLayout) itemView.findViewById(R.id.movie_layout);
            title=(TextView) itemView.findViewById(R.id.title);
            content=(TextView) itemView.findViewById(R.id.description);
            imageView=(ImageView) itemView.findViewById(R.id.image3);
        }
    }

    private class SetPics extends AsyncTask<ImageView,Bitmap,Bitmap> {


        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            MovieListRecyclerView_adapter.this.notifyDataSetChanged();
        }

        @Override
        protected Bitmap doInBackground(ImageView... imageViews) {
            for(int i=0;i<arrayList.size();i++){
                Uri  videoURI = Uri.parse("android.resource://" + G.context.getPackageName() +"/"
                        +arrayList.get(i).getUri());
                MediaMetadataRetriever retriever = new MediaMetadataRetriever();
                retriever.setDataSource(G.context, videoURI);
                Bitmap bitmap = retriever
                        .getFrameAtTime(100000,MediaMetadataRetriever.OPTION_PREVIOUS_SYNC);
                bitmaps.set(i,bitmap);
                MovieListRecyclerView_adapter.this.notifyItemChanged(i);

            }

            return null;
        }
    }



}
