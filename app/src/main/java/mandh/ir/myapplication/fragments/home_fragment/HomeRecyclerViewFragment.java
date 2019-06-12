package mandh.ir.myapplication.fragments.home_fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import mandh.ir.myapplication.adapters.recyclerViewAdapters.HomeRecyclerView_adapter;
import mandh.ir.myapplication.models.Model;
import mandh.ir.myapplication.R;

import static mandh.ir.myapplication.forHelp.G.context;

/**
 * Created by Dell  5559 on 25/05/2019.
 */

public class HomeRecyclerViewFragment extends Fragment   {



    Spinner gradeSpinner;
    Spinner sortSpinner;
    TextView title;
    RecyclerView recyclerView;
    //..............................................................................................
    ArrayList<Model> arrayList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = null;
        view = inflater.inflate(R.layout.fragment_home_recyclerview, container, false);


        arrayList = new ArrayList<>();

        cast(view);

        createData();

        setTYpeFaces();

        setGradeSpinners();

        setSortSpinners();

        setRecyclerView();


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




    private void setSortSpinners() {

        Model  modelS0 = new Model("مرتب سازی");
        Model  modelS1 = new Model(2,"صعودی");
        Model  modelS2 = new Model(1,"نزولی");
        ArrayList<Model> sortSpinnerArray = new ArrayList<>() ;
        sortSpinnerArray.add(modelS0);
        sortSpinnerArray.add(modelS1);
        sortSpinnerArray.add(modelS2);

        ArrayAdapter<Model> sortSpinnerArrayAdapter = new ArrayAdapter< >(getActivity(), android.R.layout.simple_spinner_item, sortSpinnerArray);

        sortSpinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(sortSpinnerArrayAdapter);


    }

    private void setGradeSpinners() {

        Model  modelG0 = new Model("مقالات");
        Model  modelG1 = new Model(2,"روزنامه ها");
        Model  modelG2 = new Model(1,"پایان نامه ها");
        ArrayList<Model> gradeSpinnerArray = new ArrayList<>() ;
        gradeSpinnerArray.add(modelG0);
        gradeSpinnerArray.add(modelG1);
        gradeSpinnerArray.add(modelG2);

        ArrayAdapter<Model> gradeSpinnerArrayAdapter = new ArrayAdapter< >(getActivity(), android.R.layout.simple_spinner_item, gradeSpinnerArray);

        gradeSpinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gradeSpinner.setAdapter(gradeSpinnerArrayAdapter);


    }

    private void setRecyclerView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        HomeRecyclerView_adapter recyclerViewAdapter3 = new HomeRecyclerView_adapter(arrayList);
        recyclerView.setAdapter(recyclerViewAdapter3);

    }

    private void setTYpeFaces() {

        Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileBold.ttf");
        title.setTypeface(myTypeface);
    }

    private void createData() {

        for (int i = 0 ;i<2500; i++){
            Model model = new Model("کتاب"+String.valueOf(i));
            arrayList.add(model);
        }

    }

    private void cast(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        title =(TextView) view.findViewById(R.id.title);
        gradeSpinner=(Spinner) view.findViewById(R.id.grade_spinner);
        sortSpinner=(Spinner) view.findViewById(R.id.sort_spinner);

    }




}
