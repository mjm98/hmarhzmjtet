package mandh.ir.myapplication.adapters.gradeViewAdapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import mandh.ir.myapplication.forHelp.G;
import mandh.ir.myapplication.models.Model;
import mandh.ir.myapplication.R;
import mandh.ir.myapplication.activitys.ShowContentActivity;

import static mandh.ir.myapplication.forHelp.G.context;


/**
 * Created by Dell  5559 on 08/05/2019.
 */

public class ShowContentGradeView_Adapter extends BaseAdapter {





    ArrayList<Model> arrayList = new ArrayList<>();
    //..............................................................................................
    private LayoutInflater mInflaterCatalogListItems;
    //..............................................................................................
    Model model;
    //..............................................................................................
    Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileBold.ttf");
    Typeface myTypeface2 = Typeface.createFromAsset(context.getAssets(), "font/yekan.ttf");
    //..............................................................................................


    public ShowContentGradeView_Adapter(ArrayList<Model> array) {

        arrayList = array;
        mInflaterCatalogListItems = (LayoutInflater) G.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();
            convertView = mInflaterCatalogListItems.inflate(R.layout.model_show_content_gradeview, null);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.name2 = (TextView) convertView.findViewById(R.id.name2);
            holder.imageView=(ImageView) convertView.findViewById(R.id.im);
            holder.cardView=(LinearLayout) convertView.findViewById(R.id.layout);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        ////////////////////////////////////Change the content here/////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////

        model=arrayList.get(position);
        if (model != null) {


            if (!model.getImageUrl().isEmpty())
                holder.imageView.setImageResource(Integer.parseInt(model.getImageUrl()));

            holder.name2.setText(model.getName());
            holder.name2.setTypeface(myTypeface);
            holder.name.setTypeface(myTypeface2);

            holder.cardView.setId(model.getId());
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(G.context, ShowContentActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("name", arrayList.get(position).getName());
                    G.context.startActivity(intent);


                }
            });

        }


        return convertView;
    }


    private static class ViewHolder {
        TextView name;
        TextView name2;
        RelativeLayout layout;
        ImageView imageView;
        LinearLayout cardView;

    }



}