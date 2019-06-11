package mandh.ir.myapplication.fragments.home_fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import mandh.ir.myapplication.adapters.gradeViewAdapters.HomeGradeView_Adapter;
import mandh.ir.myapplication.models.Model;
import mandh.ir.myapplication.R;

import static mandh.ir.myapplication.forHelp.G.context;

/**
 * Created by Dell  5559 on 25/05/2019.
 */

public class homeGRadeViewFragment extends Fragment     {



    GridView gridview;
    ArrayList<Model> arrayList;
    TextView title;
    Spinner gradeSpinner;
    Spinner sortSpinner;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = null;
        view = inflater.inflate(R.layout.fragment1, container, false);

        arrayList = new ArrayList<>();

        createData();

        setGradeView();

        cast(view);

        setSpinners();

        setTypeFaces();


         gradeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "font/yekan.ttf");
                ((TextView) adapterView.getChildAt(0)).setTypeface(myTypeface);
                ((TextView) adapterView.getChildAt(0)).setTextSize(9);
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.parseColor("#fad10f"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


         sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "font/yekan.ttf");
                ((TextView) adapterView.getChildAt(0)).setTypeface(myTypeface);
                ((TextView) adapterView.getChildAt(0)).setTextSize(9);
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.parseColor("#fad10f"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        return  view;

    }

    private void setTypeFaces() {

        Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileBold.ttf");
        title.setTypeface(myTypeface);

    }

    private void createData() {

        for (int i = 0 ;i<2500; i++){
            Model model = new Model("کتاب"+String.valueOf(i));
            arrayList.add(model);
        }

    }

    private void setSpinners() {


        Model  modelS0 = new Model("مرتب سازی");
        Model  modelS1 = new Model(2,"صعودی");
        Model  modelS2 = new Model(1,"نزولی");
        ArrayList<Model> sortSpinnerArray = new ArrayList<>() ;
        sortSpinnerArray.add(modelS0);
        sortSpinnerArray.add(modelS1);
        sortSpinnerArray.add(modelS2);

        Model  modelG0 = new Model("مقاطع تحصیلی");
        Model  modelG1 = new Model(2,"دوم");
        Model  modelG2 = new Model(1,"اول");
        ArrayList<Model> gradeSpinnerArray = new ArrayList<>() ;
        gradeSpinnerArray.add(modelG0);
        gradeSpinnerArray.add(modelG1);
        gradeSpinnerArray.add(modelG2);

        ArrayAdapter<Model> gradeSpinnerArrayAdapter = new ArrayAdapter< >(getActivity(), android.R.layout.simple_spinner_item, gradeSpinnerArray);

        ArrayAdapter<Model> sortSpinnerArrayAdapter = new ArrayAdapter< >(getActivity(), android.R.layout.simple_spinner_item, sortSpinnerArray);


        gradeSpinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gradeSpinner.setAdapter(gradeSpinnerArrayAdapter);

        sortSpinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(sortSpinnerArrayAdapter);

    }

    private void cast(View view) {
        gridview = (GridView) view.findViewById(R.id.gridView);
        title =(TextView) view.findViewById(R.id.title);
        gradeSpinner=(Spinner) view.findViewById(R.id.grade_spinner);
        sortSpinner=(Spinner) view.findViewById(R.id.sort_spinner);


    }

    private void setGradeView() {

        HomeGradeView_Adapter gradeView = new HomeGradeView_Adapter(arrayList);
        gridview.setAdapter(gradeView);



    }




}


