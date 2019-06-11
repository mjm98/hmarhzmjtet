package mandh.ir.myapplication.activitys;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import mandh.ir.myapplication.adapters.fragmentPagerAdapter.PagerAdapter_homeActivity;
import mandh.ir.myapplication.R;

import static mandh.ir.myapplication.forHelp.G.context;

public class HomeActivity extends AppCompatActivity {

    public static TabLayout tabLayout;
    TabLayout tabLayout2;
    ViewPager viewPager;

    TextView title;
    Button qrButton;
    EditText searchEtx;

    static HomeActivity mainActivity;

    public static HomeActivity getMainActivity() {
        return mainActivity;
    }

    Spinner gradeSpinner;
    Spinner sortSpinner;

    ImageView gradeSpinnerIcon;
    ImageView sortSpinnerIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.black));
        }
        setContentView(R.layout.activity_main);

        mainActivity=this;

        tabLayout= (TabLayout) findViewById(R.id.tab);
        tabLayout2= (TabLayout) findViewById(R.id.tab2);
        viewPager=(ViewPager) findViewById(R.id.view_pager);

        Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileBold.ttf");
        title = (TextView) findViewById(R.id.title);
        qrButton =(Button) findViewById(R.id.qr_button);
        searchEtx = (EditText) findViewById(R.id.edit_text);

        title.setTypeface(myTypeface);
        qrButton.setTypeface(myTypeface);
        searchEtx.setTypeface(myTypeface);


        setTabLatout();
        setTabLatout2();
    }




    //set tab layout................................................................................
    private void setTabLatout2() {



        //create tabs
        tabLayout2.addTab(tabLayout2.newTab().setText("کتابخانه"));
        tabLayout2.addTab(tabLayout2.newTab().setText("مطالب"));





        //create an adapter
        final PagerAdapter_homeActivity adapter = new PagerAdapter_homeActivity(getSupportFragmentManager(), tabLayout2.getTabCount());

        //setting viewPager
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit((adapter.getCount() > 1 ? adapter.getCount() - 1 : 1));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //tabLayout change tab listenr
        tabLayout2.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());

            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position,false);
                tabLayout2.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


        Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileMedium.ttf");

        ViewGroup vg = (ViewGroup) tabLayout2.getChildAt(0);
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




    //set tab layout................................................................................
    private void setTabLatout() {



        //create tabs
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileMedium.ttf");

        View view1 = View.inflate(this, R.layout.custom_view_tab_layout_disable, null);
        TextView title = (TextView) view1.findViewById(R.id.title);
        title.setText("تنظیمات");
        title.setTypeface(myTypeface);

        tabLayout.getTabAt(0).setCustomView(null);
        tabLayout.getTabAt(0).setCustomView(view1);


        View view2 = View.inflate(this, R.layout.custom_view_tab_layout_disable, null);
        TextView title2 = (TextView) view2.findViewById(R.id.title);
        title2.setText("شبکه علمی");
        title2.setTypeface(myTypeface);

        tabLayout.getTabAt(1).setCustomView(null);
        tabLayout.getTabAt(1).setCustomView(view2);



        View view3 = View.inflate(this, R.layout.custom_view_tab_layout_enable, null);

        TextView title3 = (TextView) view3.findViewById(R.id.title);
        title3.setText("خانه");
        title3.setTypeface(myTypeface);

        tabLayout.getTabAt(2).setCustomView(null);
        tabLayout.getTabAt(2).setCustomView(view3);



        View view4 = View.inflate(this, R.layout.custom_view_tab_layout_disable, null);
        TextView title4 = (TextView) view4.findViewById(R.id.title);
        title4.setText("چالش ها");
        title4.setTypeface(myTypeface);

        tabLayout.getTabAt(3).setCustomView(null);
        tabLayout.getTabAt(3).setCustomView(view4);



        View view5 = View.inflate(this, R.layout.custom_view_tab_layout_disable, null);
        TextView title5 = (TextView) view5.findViewById(R.id.title);
        title5.setText("پروفایل");
        title5.setTypeface(myTypeface);

        tabLayout.getTabAt(4).setCustomView(null);
        tabLayout.getTabAt(4).setCustomView(view5);

        tabLayout.getTabAt(2).select();


        //create an adapter
      //  final Class_adapter adapter = new Class_adapter(getSupportFragmentManager(), tabLayout.getTabCount());

        //setting viewPager
//        viewPager.setAdapter(adapter);
  //      viewPager.setOffscreenPageLimit((adapter.getCount() > 1 ? adapter.getCount() - 1 : 1));
    //    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //tabLayout change tab listenr
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                //change the position of viewPager
              //  count=tab.getPosition();
                //viewPager.setCurrentItem(tab.getPosition());

            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

    }

}
