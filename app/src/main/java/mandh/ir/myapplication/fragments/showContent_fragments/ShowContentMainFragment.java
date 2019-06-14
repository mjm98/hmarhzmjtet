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
    int bookid;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = null;
        bookid=getArguments().getInt("id", 0);
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

        Model model1 = new Model(1,"محتوای صوتی");
        Model model2 = new Model(2,"محتوای تصویری");
        Model model3 = new Model(3,"محتوای ویدیویی");
        Model model4 = new Model(4,"محتوای فایلی");
        Model model5 = new Model(5,"محتوای 3بعدی");
        Model model6 = new Model(6,"محتوای متنی");

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

        ShowContentGradeView_Adapter gradeView = new ShowContentGradeView_Adapter(arrayList,bookid);
        gridview.setAdapter(gradeView);

    }




}


