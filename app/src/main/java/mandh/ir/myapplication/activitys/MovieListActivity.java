package mandh.ir.myapplication.activitys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import mandh.ir.myapplication.adapters.recyclerViewAdapters.MovieListRecyclerView_adapter;
import mandh.ir.myapplication.models.StaticsData;
import mandh.ir.myapplication.models.VideosAndAudios;

import static mandh.ir.myapplication.forHelp.G.context;

public class MovieListActivity extends Activity {

    MovieListRecyclerView_adapter adapter;
    TabLayout bottomTabLayout;
    TabLayout topTabLayout;
    ViewPager viewPager;
    TextView title;
    Button qrButton;
    EditText searchEtx;
    Spinner sortSpinner;
    RecyclerView recyclerView;

    ArrayList<Model> movieList;

    TextView sampleVidTitle ;
    TextView sampleVidDescription ;
    int bookid=0;
    int pageid=1;

    private  static MovieListActivity movieListActivity;

    public static MovieListActivity getMovieListActivity() {
        return movieListActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_movie_list);

        bookid=getIntent().getExtras().getInt("bookid");
        pageid=getIntent().getExtras().getInt("pageid");

        movieList = new ArrayList<>();
        movieListActivity=this;

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
                ((TextView) adapterView.getChildAt(0)).setTextSize(10);
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.parseColor("#fad10f"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void setRecyclerView() {
        Intent i=new Intent(MovieListActivity.this,VideoActivity.class);

        if(pageid!=-1) {
            adapter = new MovieListRecyclerView_adapter((VideosAndAudios) StaticsData.makeData().get(bookid).getPages().get(pageid), bookid, pageid);

        }else{
            adapter = new MovieListRecyclerView_adapter((VideosAndAudios)StaticsData.makeData().get(bookid),bookid,pageid);}
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

    }

    private void createData() {

        movieList.add(new Model("Schindler's List"));
        movieList.add(new Model("Pulp Fiction"));
        movieList.add(new Model("No Country for Old Men"));
        movieList.add(new Model("Léon: The Professional"));
        movieList.add(new Model("Fight Club"));
        movieList.add(new Model("Forrest Gump"));
        movieList.add(new Model("The Shawshank Redemption"));
        movieList.add(new Model("The Godfather"));
        movieList.add(new Model("A Beautiful Mind"));
        movieList.add(new Model("Good Will Hunting"));

    }

    private void setTypeFaces() {

        Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileMedium.ttf");
        Typeface myTypeface2 = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileBold.ttf");
        sampleVidTitle.setTypeface(myTypeface2);
        sampleVidDescription.setTypeface(myTypeface);
        title.setTypeface(myTypeface2);

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
        sampleVidTitle =(TextView) findViewById(R.id.vid_title);
        sampleVidDescription = (TextView) findViewById(R.id.description);
        recyclerView = findViewById(R.id.recyclerView);

    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }

}
