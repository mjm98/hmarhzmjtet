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

/**
 * Created by Dell  5559 on 25/05/2019.
 */

public class FragmentEmpty2 extends Fragment     {



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
        view = inflater.inflate(R.layout.empety, container, false);




        return  view;

    }




}


