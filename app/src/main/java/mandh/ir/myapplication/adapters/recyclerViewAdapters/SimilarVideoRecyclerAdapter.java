package mandh.ir.myapplication.adapters.recyclerViewAdapters;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import mandh.ir.myapplication.R;

import static mandh.ir.myapplication.forHelp.G.context;

public class SimilarVideoRecyclerAdapter extends RecyclerView.Adapter<SimilarVideoRecyclerAdapter.ItemHolder>  {

    public SimilarVideoRecyclerAdapter() {
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_similar_video_rescyclerview, parent, false);
        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        ImageView videImage;
        TextView videoTitle;
        public ItemHolder(View itemView) {
            super(itemView);
            videImage=(ImageView) itemView.findViewById(R.id.videoImage);
            videoTitle=(TextView)itemView.findViewById(R.id.videoTitle);
            Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileMedium.ttf");
            videoTitle.setTypeface(myTypeface);

            }
    }
}
