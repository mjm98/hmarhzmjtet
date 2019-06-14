package mandh.ir.myapplication.adapters.recyclerViewAdapters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mandh.ir.myapplication.models.SoundModel;
import mandh.ir.myapplication.R;

import static mandh.ir.myapplication.forHelp.G.context;

public class SoundListRecyclerView_adapter extends RecyclerView.Adapter<SoundListRecyclerView_adapter.RecViewHolder> {

    private ArrayList<SoundModel> list;

    public SoundListRecyclerView_adapter(ArrayList<SoundModel> list) {
        this.list = list;
    }

    @Override
    public RecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.model_sound, parent, false);
        return new RecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecViewHolder holder, final int position) {
        final SoundModel movie = list.get(position);

        holder.bind(movie);

        Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileMedium.ttf");
        holder.time.setTypeface(myTypeface);
        holder.date.setTypeface(myTypeface);
        holder.title.setTypeface(myTypeface);

        holder.itemView.setId(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean expanded = movie.isExpanded();

                if (!list.get(position).isExpanded()) {

                    for (int i = 0; i < list.size(); i++) {

                        if (i != position) {
                            list.get(i).setExpanded(expanded);
                        } else {
                            list.get(i).setExpanded(!expanded);
                        }



                    }
                }
                else  list.get(position).setExpanded(false);

               notifyItemRangeChanged(0,list.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class RecViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView time;
        private TextView date;
        private View subItem;


        public RecViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            time = itemView.findViewById(R.id.time);
            date = itemView.findViewById(R.id.date);
            subItem = itemView.findViewById(R.id.sub_item);

        }

        private void bind(SoundModel movie) {
            boolean expanded = movie.isExpanded();

            subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);


        }

        //fade out animation............................................................................
        private void fadeOut(final View view) {


            view.animate()
                    .setDuration(200)
                    .translationY(view.getY()+70)
                    .alpha(0.0f)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            view.setVisibility(View.GONE);
                        }
                    });


        }
    }
}