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

        setBottomTabLayout();

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

    private void setBottomTabLayout() {

        //create tabs
        bottomTabLayout.addTab(bottomTabLayout.newTab());
        bottomTabLayout.addTab(bottomTabLayout.newTab());
        bottomTabLayout.addTab(bottomTabLayout.newTab());
        bottomTabLayout.addTab(bottomTabLayout.newTab());
        bottomTabLayout.addTab(bottomTabLayout.newTab());

        Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileMedium.ttf");

        //.......................create custom Views and add them to bottomTabLayout.......................

        View view1 = View.inflate(this, R.layout.custom_view_tab_layout_disable, null);
        TextView title = (TextView) view1.findViewById(R.id.title);
        title.setText("تنظیمات");
        title.setTypeface(myTypeface);

        bottomTabLayout.getTabAt(0).setCustomView(null);
        bottomTabLayout.getTabAt(0).setCustomView(view1);


        View view2 = View.inflate(this, R.layout.custom_view_tab_layout_disable, null);
        TextView title2 = (TextView) view2.findViewById(R.id.title);
        title2.setText("شبکه علمی");
        title2.setTypeface(myTypeface);

        bottomTabLayout.getTabAt(1).setCustomView(null);
        bottomTabLayout.getTabAt(1).setCustomView(view2);



        View view3 = View.inflate(this, R.layout.custom_view_tab_layout_enable, null);

        TextView title3 = (TextView) view3.findViewById(R.id.title);
        title3.setText("خانه");
        title3.setTypeface(myTypeface);

        bottomTabLayout.getTabAt(2).setCustomView(null);
        bottomTabLayout.getTabAt(2).setCustomView(view3);



        View view4 = View.inflate(this, R.layout.custom_view_tab_layout_disable, null);
        TextView title4 = (TextView) view4.findViewById(R.id.title);
        title4.setText("چالش ها");
        title4.setTypeface(myTypeface);

        bottomTabLayout.getTabAt(3).setCustomView(null);
        bottomTabLayout.getTabAt(3).setCustomView(view4);


        View view5 = View.inflate(this, R.layout.custom_view_tab_layout_disable, null);
        TextView title5 = (TextView) view5.findViewById(R.id.title);
        title5.setText("پروفایل");
        title5.setTypeface(myTypeface);

        bottomTabLayout.getTabAt(4).setCustomView(null);
        bottomTabLayout.getTabAt(4).setCustomView(view5);

        bottomTabLayout.getTabAt(2).select();

        //..........................................................................................

        bottomTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                //viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });


    }



}
