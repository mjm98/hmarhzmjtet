package mandh.ir.myapplication.adapters.gradeViewAdapters;

import android.content.Context;
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
import mandh.ir.myapplication.R;

import static mandh.ir.myapplication.forHelp.G.context;


/**
 * Created by Dell  5559 on 08/05/2019.
 */

public class HomeGradeView_Adapter extends BaseAdapter {



    ArrayList<Book> arrayList = new ArrayList<>();

    public interface ItemClick {
        public void onClick(int bookId);
    }


    ItemClick itemClick;
    //..............................................................................................
    private LayoutInflater mInflaterCatalogListItems;
    //..............................................................................................
    Book model;


    public HomeGradeView_Adapter(ArrayList<Book> array,ItemClick itemClick) {

        arrayList = array;
        mInflaterCatalogListItems = (LayoutInflater) G.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemClick=itemClick;


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

                    itemClick.onClick(  arrayList.get(position).getId() );


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



}