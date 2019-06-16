package mandh.ir.myapplication.adapters.recyclerViewAdapters;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import mandh.ir.myapplication.activitys.MovieListActivity;
import mandh.ir.myapplication.activitys.VideoActivity;
import mandh.ir.myapplication.forHelp.G;
import mandh.ir.myapplication.models.Model;
import mandh.ir.myapplication.R;

import static mandh.ir.myapplication.forHelp.G.context;


/**
 * Created by k.z on 14/02/2018.
 */

public class MovieListRecyclerView_adapter extends RecyclerView.Adapter<MovieListRecyclerView_adapter.classes_list_holder> {

    //..............................................................................................
    Model model;
    //..............................................................................................
    private ArrayList<Model> arrayList = new ArrayList<>();
    //..............................................................................................
    Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileMedium.ttf");
    Typeface myTypeface2 = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileBold.ttf");

    Intent i;

    //constructor
    public MovieListRecyclerView_adapter(ArrayList<Model> arrayList ,Intent i) {
        this.i=i;
        this.arrayList =arrayList;
    }

    @Override
    public classes_list_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.model_movie,parent,false);
        return new classes_list_holder(view);
    }


    @Override
    public void onBindViewHolder(final classes_list_holder holder, int position) {

        model= arrayList.get(position);

        holder.title.setTypeface(myTypeface2);
        holder.content.setTypeface(myTypeface);
        holder.rl.setId(model.getId());
        holder.rl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(G.context,VideoActivity.class);
                G.context.startActivity(intent);

            }
         });

    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class classes_list_holder extends RecyclerView.ViewHolder {

        TextView title;
        TextView content;
        RelativeLayout rl;

        public classes_list_holder(View itemView) {

            super(itemView);
            rl=(RelativeLayout) itemView.findViewById(R.id.movie_layout);
            title=(TextView) itemView.findViewById(R.id.title);
            content=(TextView) itemView.findViewById(R.id.description);
        }
    }



}
