package mandh.ir.myapplication.adapters.gradeViewAdapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import mandh.ir.myapplication.forHelp.G;
import mandh.ir.myapplication.models.Book;
import mandh.ir.myapplication.models.Model;
import mandh.ir.myapplication.R;
import mandh.ir.myapplication.activitys.HomeActivity;
import mandh.ir.myapplication.activitys.ShowContentActivity;

import static mandh.ir.myapplication.forHelp.G.context;


/**
 * Created by Dell  5559 on 08/05/2019.
 */

public class HomeGradeView_Adapter extends BaseAdapter {



    ArrayList<Book> arrayList = new ArrayList<>();

    public interface ItemClick {
        public void onClick();
    }
    ItemClick itemClick;
    //..............................................................................................
    private LayoutInflater mInflaterCatalogListItems;
    //..............................................................................................
    Book model;


    public HomeGradeView_Adapter(ArrayList<Book> array) {

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
            convertView = mInflaterCatalogListItems.inflate(R.layout.model_home_gradeview_, null);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.imageView=(ImageView) convertView.findViewById(R.id.im);
            holder.cardView=(RelativeLayout) convertView.findViewById(R.id.layout);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        ////////////////////////////////////Change the content here/////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////

        model=arrayList.get(position) ;
        if (model != null) {

            if (model.getImageurl()!=0)
            holder.imageView.setImageResource(model.getImageurl());

            Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileMedium.ttf");
            holder.name.setText(model.getTitle());
            holder.name.setTypeface(myTypeface);

            holder.cardView.setId(model.getId());
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(G.context, ShowContentActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("id", arrayList.get(position).getId());
                    G.context.startActivity(intent);
                    HomeActivity.getMainActivity().overridePendingTransition(R.anim.a_fade_in, R.anim.a_fade_out);




                }
            });

        }


        return convertView;
    }


    private static class ViewHolder {
        TextView name;
        RelativeLayout layout;
        ImageView imageView;
        RelativeLayout cardView;

    }

    public void sortData(int s){
        if(s==1){
        for(int i = 0; i<arrayList.size()-1; i++) {
            for (int j = i+1; j<arrayList.size(); j++) {
                if(arrayList.get(i).getTitle().compareTo(arrayList.get(j).getTitle())>0) {
                    Book temp = arrayList.get(i);
                    arrayList.set(i,arrayList.get(j));
                    arrayList.set(j,temp);
                }
            }
         }
        }else if(s==2){
            for(int i = 0; i<arrayList.size()-1; i++) {
                for (int j = i+1; j<arrayList.size(); j++) {
                    if(arrayList.get(i).getTitle().compareTo(arrayList.get(j).getTitle())<0) {
                        Book temp = arrayList.get(i);
                        arrayList.set(i,arrayList.get(j));
                        arrayList.set(j,temp);
                    }
                }
            }
        }
     }



}