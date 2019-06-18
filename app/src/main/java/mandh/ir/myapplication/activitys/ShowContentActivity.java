package mandh.ir.myapplication.activitys;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import mandh.ir.myapplication.adapters.fragmentPagerAdapter.PagerAdapter_showContentActivity;
import mandh.ir.myapplication.R;

import static mandh.ir.myapplication.forHelp.G.context;

public class ShowContentActivity extends AppCompatActivity {

    TabLayout bottomTabLayout;
    TabLayout topTabLayout;
    ViewPager viewPager;
    Button codeButton;
    EditText searchEtx;
    int bookid;

    public static ShowContentActivity showContentActivity;

    public static ShowContentActivity getShowContentActivity() {
        return showContentActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showContentActivity=this;

        //remove statusBar (notification bar)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_show_content);
        bookid = getIntent().getExtras().getInt("id");

        cast();

        setTypeFaces();

        setBottomTabLayout();

        setTopTabLayout();
    }

    private void setTypeFaces() {

        Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileBold.ttf");

        codeButton.setTypeface(myTypeface);
        searchEtx.setTypeface(myTypeface);

    }

    private void cast() {

        bottomTabLayout = (TabLayout) findViewById(R.id.tab);
        topTabLayout = (TabLayout) findViewById(R.id.tab2);
        viewPager=(ViewPager) findViewById(R.id.view_pager);
        codeButton=(Button) findViewById(R.id.code_button);
        searchEtx =(EditText) findViewById(R.id.edit_text);

    }


    //set tab layout................................................................................
    private void setTopTabLayout() {


        //create tabs
        topTabLayout.addTab(topTabLayout.newTab().setText("صفحات"));
        topTabLayout.addTab(topTabLayout.newTab().setText("بخش ها"));
        topTabLayout.addTab(topTabLayout.newTab().setText("ذخیره شده ها"));

        //create an adapter
        final PagerAdapter_showContentActivity adapter = new PagerAdapter_showContentActivity(getSupportFragmentManager(), topTabLayout.getTabCount(),bookid);

        //setting viewPager
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit((adapter.getCount() > 1 ? adapter.getCount() - 1 : 1));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(bottomTabLayout));

        //bottomTabLayout change tab listenr
        topTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override public void onTabUnselected(TabLayout.Tab tab) {}

            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });

        //viewPager page change listener
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position, false);
                topTabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        setTablaouyTitleTypeFaces(topTabLayout);
    }


    private void setTablaouyTitleTypeFaces(TabLayout tabLayout) {

        Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileMedium.ttf");

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(myTypeface);
                    ((TextView) tabViewChild).setTextColor(Color.parseColor("#4b43cf"));
                }
            }


        }
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


    @Override
    public void onBackPressed() {
        super.onBackPressed();

       // overridePendingTransition(R.anim.a_fade_in, R.anim.a_fade_out);

    }


}
