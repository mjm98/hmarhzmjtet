package mandh.ir.myapplication.fragments.bookContent_fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import mandh.ir.myapplication.adapters.fragmentPagerAdapter.PagerAdapter_showContentActivity;
import mandh.ir.myapplication.R;
import mandh.ir.myapplication.forHelp.G;

import static mandh.ir.myapplication.forHelp.G.context;

public class Parent_BookContentFragment extends Fragment {

    TabLayout bottomTabLayout;
    TabLayout topTabLayout;
    ViewPager viewPager;
    Button codeButton;
    EditText searchEtx;
    int bookid;

    public static Parent_BookContentFragment bookContentFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = null;
        view = inflater.inflate(R.layout.activity_show_content, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            bookid = bundle.getInt("id");
        }
        //
      //  Toast.makeText(G.context,String.valueOf(bookid), Toast.LENGTH_SHORT).show();

        cast(view);

        setTypeFaces();


        setTopTabLayout();

        return view;

    }


    private void setTypeFaces() {

        Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileBold.ttf");

        codeButton.setTypeface(myTypeface);
        searchEtx.setTypeface(myTypeface);

    }

    private void cast(View view) {

        bottomTabLayout = (TabLayout) view.findViewById(R.id.tab);
        topTabLayout = (TabLayout) view.findViewById(R.id.tab2);
        viewPager=(ViewPager) view.findViewById(R.id.view_pager);
        codeButton=(Button) view.findViewById(R.id.code_button);
        searchEtx =(EditText) view.findViewById(R.id.edit_text);

    }


    //set tab layout................................................................................
    private void setTopTabLayout() {


        //create tabs
        topTabLayout.addTab(topTabLayout.newTab().setText("صفحات"));
        topTabLayout.addTab(topTabLayout.newTab().setText("بخش ها"));
        topTabLayout.addTab(topTabLayout.newTab().setText("ذخیره شده ها"));

        //create an adapter
        final PagerAdapter_showContentActivity adapter = new PagerAdapter_showContentActivity(getChildFragmentManager(), topTabLayout.getTabCount(),bookid);

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





}
