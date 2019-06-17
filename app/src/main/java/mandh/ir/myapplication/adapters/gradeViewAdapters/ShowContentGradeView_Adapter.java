package mandh.ir.myapplication.adapters.gradeViewAdapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaMetadata;
import android.media.MediaMetadataRetriever;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mandh.ir.myapplication.activitys.SoundListActivity;
import mandh.ir.myapplication.activitys.MovieListActivity;
import mandh.ir.myapplication.forHelp.G;
import mandh.ir.myapplication.models.Model;
import mandh.ir.myapplication.R;
import mandh.ir.myapplication.activitys.ShowContentActivity;
import mandh.ir.myapplication.models.ModelShowContent;
import mandh.ir.myapplication.models.StaticsData;
import mandh.ir.myapplication.models.VideosAndAudios;

import static mandh.ir.myapplication.forHelp.G.context;


/**
 * Created by Dell  5559 on 08/05/2019.
 */

public class ShowContentGradeView_Adapter extends BaseAdapter {



    int bookid;

    //that means whole of the book
    int pageid;


    ArrayList<ModelShowContent> arrayList = new ArrayList<>();
    //..............................................................................................
    private LayoutInflater mInflaterCatalogListItems;
    //..............................................................................................
    ModelShowContent model;
    //..............................................................................................
    Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileBold.ttf");
    Typeface myTypeface2 = Typeface.createFromAsset(context.getAssets(), "font/yekan.ttf");
    //..............................................................................................


    public ShowContentGradeView_Adapter(ArrayList<ModelShowContent> array, int bookid, int pageid) {

        this.pageid=pageid;
        this.bookid=bookid;
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
            holder.num=(TextView) convertView.findViewById(R.id.num);


            convertView.setTag(holder);


        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        ////////////////////////////////////Change the content here/////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////

        model=arrayList.get(position);
        if (model != null) {
            try {
            if (!model.getImageUrl().isEmpty())
                holder.imageView.setImageResource(Integer.parseInt(model.getImageUrl()));

            holder.name2.setText(model.getName());
            holder.name2.setTypeface(myTypeface);

            holder.name.setTypeface(myTypeface2);
            holder.num.setTypeface(myTypeface2);

            holder.num.setText(model.getNumber());



               /* if (pageid == -1) {
                    switch (position) {
                        case 0:
                            holder.num.setText(String.valueOf(StaticsData.makeData().get(bookid).getNumberOfVoices()));
                            break;
                        case 1:
                            holder.num.setText(String.valueOf(StaticsData.makeData().get(bookid).getNumberOfVideos()));
                            break;
                        case 2:
                            holder.num.setText(String.valueOf(StaticsData.makeData().get(bookid).getNumberOfPages()));
                            break;
                        case 3:
                            holder.num.setText(String.valueOf(StaticsData.makeData().get(bookid).getNumberOfFiles()));
                            break;
                        case 4:
                            holder.num.setText(String.valueOf(StaticsData.makeData().get(bookid).getNumberOfdocs()));
                            break;
                        case 5:
                            holder.num.setText(String.valueOf(StaticsData.makeData().get(bookid).getNumberOf3d()));
                            break;
                    }
                } else {




                    switch (position) {
                        case 0:
                            holder.num.setText(String.valueOf(StaticsData.makeData().get(bookid).getPages().get(pageid).getNumberOfVoices()));
                            break;
                        case 1:
                            holder.num.setText(String.valueOf(StaticsData.makeData().get(bookid).getPages().get(pageid).getNumberOfVideos()));
                            break;
                        case 2:
                            holder.num.setText(String.valueOf(StaticsData.makeData().get(bookid).getPages().get(pageid).getNumberOfPics()));
                            break;
                        case 3:
                            holder.num.setText(String.valueOf(StaticsData.makeData().get(bookid).getPages().get(pageid).getNumberOfFiles()));
                            break;
                        case 4:
                            holder.num.setText(String.valueOf(StaticsData.makeData().get(bookid).getPages().get(pageid).getNumberOfdocs()));
                            break;
                        case 5:
                            holder.num.setText(String.valueOf(StaticsData.makeData().get(bookid).getPages().get(pageid).getNumberOf3d()));
                            break;

                    }

                }*/
            }catch (Exception e){
                Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
            }

            holder.cardView.setId(position);

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                   /* Model model=arrayList.get(view.getId());*/
                    int id = position;
                    Intent intent = null;
                    intent = new Intent(G.context, SoundListActivity.class);
                    if (id==0) {
                        try {
                            intent.putExtra("pageid", pageid);
                            intent.putExtra("bookid",bookid);
                        }catch (Exception e){
                            Toast.makeText(G.context,e.toString(),Toast.LENGTH_LONG).show();
                        }



                    }

                    if (id==2) {
                        intent = new Intent(G.context, MovieListActivity.class);
                        intent.putExtra("pageid", pageid);
                        intent.putExtra("bookid",bookid);
                    }

                    if (intent!=null) {
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        G.context.startActivity(intent);
                        ShowContentActivity.getShowContentActivity().overridePendingTransition(R.anim.a_fade_in, R.anim.a_fade_out);

                    }


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
        TextView num;

    }
    public void setModel(ArrayList<ModelShowContent> array,int pageid){
        this.pageid=pageid;
        this.arrayList=array;

    }



}