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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import mandh.ir.myapplication.forHelp.G;
import mandh.ir.myapplication.models.Model;
import mandh.ir.myapplication.R;
import mandh.ir.myapplication.activitys.HomeActivity;
import mandh.ir.myapplication.activitys.ShowContentActivity;

import static mandh.ir.myapplication.forHelp.G.context;


/**
 * Created by Dell  5559 on 08/05/2019.
 */

public class HomeGradeView_Adapter extends BaseAdapter {



    ArrayList<Model> arrayList = new ArrayList<>();
    private LayoutInflater mInflaterCatalogListItems;
    Model model;


    public HomeGradeView_Adapter(ArrayList<Model> array) {

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
            convertView = mInflaterCatalogListItems.inflate(R.layout.model_categoury, null);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.imageView=(ImageView) convertView.findViewById(R.id.im);
            holder.cardView=(RelativeLayout) convertView.findViewById(R.id.layout);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        model=arrayList.get(position) ;
        //Change the content here
        if (model != null) {


            model=arrayList.get(position);
            holder.name.setText(model.getName());

            if (!model.getImageUrl().isEmpty())
            holder.imageView.setImageResource(Integer.parseInt(model.getImageUrl()));

            Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileMedium.ttf");
            holder.name.setTypeface(myTypeface);



            holder.cardView.setId(model.getId());
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(G.context, ShowContentActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("name", arrayList.get(position).getName());
                    G.context.startActivity(intent);
                    HomeActivity.getMainActivity().overridePendingTransition(R.anim.a_fade_in, R.anim.a_fade_out);


                }
            });

        }


        return convertView;
    }

    //View Holder class used for reusing the same inflated view. It will decrease the inflation overhead @getView
    private static class ViewHolder {
        TextView name;
        RelativeLayout layout;
        ImageView imageView;
        RelativeLayout cardView;

    }


    public static String Encrypt(String text) throws UnsupportedEncodingException {
      //  return URLEncoder.encode(text, "utf-8");


        return  text.replace("http","https");



    }

}