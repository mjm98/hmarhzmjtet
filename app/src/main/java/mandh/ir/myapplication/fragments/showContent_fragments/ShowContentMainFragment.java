package mandh.ir.myapplication.fragments.showContent_fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

import mandh.ir.myapplication.models.Model;
import mandh.ir.myapplication.R;
import mandh.ir.myapplication.adapters.gradeViewAdapters.ShowContentGradeView_Adapter;

import static mandh.ir.myapplication.forHelp.G.context;

/**
 * Created by Dell  5559 on 25/05/2019.
 */

public class ShowContentMainFragment extends Fragment     {




    GridView gridview;
    TextView title  ;
    TextView number ;
    //..............................................................................................
    ArrayList<Model> arrayList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = null;
        view = inflater.inflate(R.layout.fragment_main_show_content, container, false);

        gridview = (GridView) view.findViewById(R.id.gridView);

        arrayList = new ArrayList<>();

        cast(view);

        setTypeFaces();

        createGradeViewData();

        setGradeView();




        return  view;

    }




    private void setTypeFaces() {

        Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileMedium.ttf");

        title.setTypeface(myTypeface);
        number.setTypeface(myTypeface);
    }




    private void createGradeViewData() {

        Model model1 = new Model("محتوای صوتی");
        Model model2 = new Model("محتوای تصویری");
        Model model3 = new Model("محتوای ویدیویی");
        Model model4 = new Model("محتوای فایلی");
        Model model5 = new Model("محتوای 3بعدی");
        Model model6 = new Model("محتوای متنی");

        arrayList.add(model1);
        arrayList.add(model2);
        arrayList.add(model3);
        arrayList.add(model4);
        arrayList.add(model5);
        arrayList.add(model6);

    }



    private void cast(View view) {

        title =(TextView) view.findViewById(R.id.title);
        number = (TextView)  view.findViewById(R.id.number);
    }



    private void setGradeView() {

        ShowContentGradeView_Adapter gradeView = new ShowContentGradeView_Adapter(arrayList);
        gridview.setAdapter(gradeView);

    }




}


