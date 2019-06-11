package mandh.ir.myapplication.fragments.showContent_fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
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



    CardView commentBtn;
    TextView textView;
    LinearLayout commentLayout;
    CardView titleCardView;
    NestedScrollView scroll;
    CardView copyBtn;
    CardView shareBtn;

    EditText nameETX;
    EditText emainETX;
    EditText messageETX;
    Button sendBtn;

    Typeface myTypeface;
    Typeface myTypeface2;

    RelativeLayout nameLyout;
    RelativeLayout emailLayout;
    RelativeLayout messageLayout;

    private final int SPLASH_DISPLAY_LENGTH = 250;

    GridView gridview;
    ArrayList<Model> arrayList;

    Spinner gradeSpinner;
    Spinner sortSpinner;

    ImageView gradeSpinnerIcon;
    ImageView sortSpinnerIcon;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = null;
        view = inflater.inflate(R.layout.fragment2_1, container, false);

        gridview = (GridView) view.findViewById(R.id.gridView);

          arrayList = new ArrayList<>();


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

        setGradeView();

        Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileMedium.ttf");
        TextView title =(TextView) view.findViewById(R.id.title);
        TextView number = (TextView)  view.findViewById(R.id.number);
        title.setTypeface(myTypeface);
        number.setTypeface(myTypeface);



        cast(view);









        return  view;

    }

    private void cast(View view) {

    }

    private void setGradeView() {


        ShowContentGradeView_Adapter gradeView = new ShowContentGradeView_Adapter(arrayList);
        gridview.setAdapter(gradeView);



    }




}


