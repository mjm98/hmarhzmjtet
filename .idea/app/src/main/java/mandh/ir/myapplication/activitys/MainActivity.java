package mandh.ir.myapplication.activitys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import mandh.ir.myapplication.R;
import mandh.ir.myapplication.adapters.fragmentPagerAdapter.MainPagerAdapter;
import mandh.ir.myapplication.fragments.Main_Fragments.HomeFragment;

import static mandh.ir.myapplication.forHelp.G.context;

public class MainActivity extends AppCompatActivity {


    TabLayout bottomTabLayout;
    ViewPager viewPager;
    int backPressedTimes = 0;
    public static  int home_fragment_position =0;

    private  static MainActivity mainActivity;
    public static  MainActivity getMainActivity() { return mainActivity; }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove statusBar (notification bar)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        mainActivity=this;

        cast();

        setBottomTabLayout();

    }


    private void cast() {
        bottomTabLayout = (TabLayout) findViewById(R.id.tab);
        viewPager =(ViewPager) findViewById(R.id.view_pager);
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
        ImageView icon1 =(ImageView) view1.findViewById(R.id.icon);
        title.setText("تنظیمات");
        icon1.setImageResource(R.drawable.setting_icon);
        title.setTypeface(myTypeface);
        bottomTabLayout.getTabAt(0).setCustomView(null);
        bottomTabLayout.getTabAt(0).setCustomView(view1);


        View view2 = View.inflate(this, R.layout.custom_view_tab_layout_disable, null);
        TextView title2 = (TextView) view2.findViewById(R.id.title);
        ImageView icon2 =(ImageView) view2.findViewById(R.id.icon);
        icon2.setImageResource(R.drawable.net_icon);
        title2.setText("شبکه علمی");
        title2.setTypeface(myTypeface);
        bottomTabLayout.getTabAt(1).setCustomView(null);
        bottomTabLayout.getTabAt(1).setCustomView(view2);


        View view3 = View.inflate(this, R.layout.custom_view_tab_layout_disable, null);
        TextView title3 = (TextView) view3.findViewById(R.id.title);
        ImageView icon3 =(ImageView) view3.findViewById(R.id.icon);
        icon3.setImageResource(R.drawable.home_icon);
        title3.setText("خانه");
        title3.setTypeface(myTypeface);
        bottomTabLayout.getTabAt(2).setCustomView(null);
        bottomTabLayout.getTabAt(2).setCustomView(view3);


        View view4 = View.inflate(this, R.layout.custom_view_tab_layout_disable, null);
        TextView title4 = (TextView) view4.findViewById(R.id.title);
        ImageView icon4 =(ImageView) view4.findViewById(R.id.icon);
        icon4.setImageResource(R.drawable.chalesh_icon);
        title4.setText("چالش ها");
        title4.setTypeface(myTypeface);
        bottomTabLayout.getTabAt(3).setCustomView(null);
        bottomTabLayout.getTabAt(3).setCustomView(view4);


        View view5 = View.inflate(this, R.layout.custom_view_tab_layout_disable, null);
        TextView title5 = (TextView) view5.findViewById(R.id.title);
        ImageView icon5 =(ImageView) view5.findViewById(R.id.icon);
        icon5.setImageResource(R.drawable.profile_icon);
        title5.setText("پروفایل");
        title5.setTypeface(myTypeface);
        bottomTabLayout.getTabAt(4).setCustomView(null);
        bottomTabLayout.getTabAt(4).setCustomView(view5);


        //..........................................................................................

        bottomTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });


        final MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(), bottomTabLayout.getTabCount());

        //setting viewPager
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit((adapter.getCount() > 1 ? adapter.getCount() - 1 : 1));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(bottomTabLayout));


        //viewPager page change listener
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position, false);
                bottomTabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        bottomTabLayout.getTabAt(2).select();
        viewPager.setCurrentItem(2,false);


    }


    @Override
    public void onBackPressed() {

        @SuppressLint("RestrictedApi") List<Fragment> fragmentList = HomeFragment.getHomeFragment().getChildFragmentManager().getFragments();
        if (fragmentList != null) {
            //TODO: Perform your logic to pass back press here
            for (Fragment fragment : fragmentList) {

                if ( home_fragment_position ==1) {
                    HomeFragment.viewPager.setCurrentItem(0,true);
                    home_fragment_position=0;
                }

            }
        }
    }



}
