package mandh.ir.myapplication.activitys;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import mandh.ir.myapplication.R;
import mandh.ir.myapplication.adapters.recyclerViewAdapters.SoundListRecyclerView_adapter;
import mandh.ir.myapplication.forHelp.G;
import mandh.ir.myapplication.models.Model;
import mandh.ir.myapplication.models.SoundModel;
import mandh.ir.myapplication.models.StaticsData;
import mandh.ir.myapplication.models.VideosAndAudios;

import static mandh.ir.myapplication.forHelp.G.context;

public class SoundListActivity extends Activity {


    TabLayout bottomTabLayout;
    SoundListRecyclerView_adapter adapter;
    TabLayout topTabLayout;
    ViewPager viewPager;
    Button qrButton;
    EditText searchEtx;
    Spinner sortSpinner;
    TextView title;
    RecyclerView recyclerView;
    int bookid=0,pageid=1;

    ArrayList<SoundModel> arrayLiset;

    @Override
    protected void onPause() {
        adapter.stop();
        super.onPause();

    }

    @Override
    protected void onDestroy() {

        adapter.release();
        adapter.releaseHandler();
        super.onDestroy();


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bookid=getIntent().getExtras().getInt("bookid");
        pageid=getIntent().getExtras().getInt("pageid");


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sound_list);

        arrayLiset = new ArrayList<>();

        cast();

        setTypeFaces();

        createData();

        setRecyclerView();

        setSortSpinners();


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



    }

    private void setRecyclerView() {

            if(pageid!=-1)
                adapter = new SoundListRecyclerView_adapter((VideosAndAudios)StaticsData.makeData().get(bookid).getPages().get(pageid),this);
            else
                adapter = new SoundListRecyclerView_adapter((VideosAndAudios)StaticsData.makeData().get(bookid),this);
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    private void setTypeFaces() {
        Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileMedium.ttf");
        title.setTypeface(myTypeface);
    }

    private void createData() {
        arrayLiset.add(new SoundModel("Schindler's List"));
        arrayLiset.add(new SoundModel("Pulp Fiction"));
        arrayLiset.add(new SoundModel("No Country for Old Men"));
        arrayLiset.add(new SoundModel("Léon: The Professional"));
        arrayLiset.add(new SoundModel("Fight Club"));
    }


    private void setSortSpinners() {

        Model modelS0 = new Model("مرتب سازی");
        Model  modelS1 = new Model(2,"صعودی");
        Model  modelS2 = new Model(1,"نزولی");
        ArrayList<Model> sortSpinnerArray = new ArrayList<>() ;
        sortSpinnerArray.add(modelS0);
        sortSpinnerArray.add(modelS1);
        sortSpinnerArray.add(modelS2);

        ArrayAdapter<Model> sortSpinnerArrayAdapter = new ArrayAdapter< >(this, android.R.layout.simple_spinner_item, sortSpinnerArray);

        sortSpinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(sortSpinnerArrayAdapter);

    }


    private void cast() {

        bottomTabLayout = (TabLayout) findViewById(R.id.tab);
        topTabLayout = (TabLayout) findViewById(R.id.tab2);
        viewPager=(ViewPager) findViewById(R.id.view_pager);
        title = (TextView) findViewById(R.id.title);
        qrButton =(Button) findViewById(R.id.qr_button);
        searchEtx = (EditText) findViewById(R.id.edit_text);
        sortSpinner=(Spinner) findViewById(R.id.sort_spinner);
        title =(TextView) findViewById(R.id.title);
        recyclerView = findViewById(R.id.recyclerView);

    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }
}
