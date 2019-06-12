package mandh.ir.myapplication.adapters.recyclerViewAdapters;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mandh.ir.myapplication.models.Model;
import mandh.ir.myapplication.R;

import static mandh.ir.myapplication.forHelp.G.context;


/**
 * Created by k.z on 14/02/2018.
 */

public class HomeRecyclerView_adapter extends RecyclerView.Adapter<HomeRecyclerView_adapter.classes_list_holder> {

    //..............................................................................................
    Model model;
    //..............................................................................................
    private ArrayList<Model> arrayList = new ArrayList<>();
    //..............................................................................................
    Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileMedium.ttf");
    Typeface myTypeface2 = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileBold.ttf");





    //constructor
    public HomeRecyclerView_adapter(ArrayList<Model> arrayList ) {
        this.arrayList =arrayList;
    }


    @Override
    public classes_list_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.model_home_recyclerview,parent,false);
        return new classes_list_holder(view);
    }


    @Override
    public void onBindViewHolder(classes_list_holder holder, int position) {

        model= arrayList.get(position);


        holder.date.setTypeface(myTypeface);
        holder.title.setTypeface(myTypeface2);
        holder.content.setTypeface(myTypeface);

    /* holder.layout.setId(model.getId());
       holder.layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Toast.makeText(G.context,String.valueOf(v.getId()),Toast.LENGTH_LONG).show();

            }
        });
        */
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class classes_list_holder extends RecyclerView.ViewHolder {

        TextView title;
        TextView date;
        TextView content;

        public classes_list_holder(View itemView) {

            super(itemView);

            date=(TextView) itemView.findViewById(R.id.date_tx);
            title=(TextView) itemView.findViewById(R.id.title);
            content=(TextView) itemView.findViewById(R.id.content);
        }
    }



}
