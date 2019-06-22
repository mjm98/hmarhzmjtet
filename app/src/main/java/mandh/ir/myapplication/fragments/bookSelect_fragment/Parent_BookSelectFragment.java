package mandh.ir.myapplication.fragments.bookSelect_fragment;

import android.content.Intent;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import mandh.ir.myapplication.adapters.fragmentPagerAdapter.PagerAdapter_homeActivity;
import mandh.ir.myapplication.R;

import static mandh.ir.myapplication.forHelp.G.context;

public class Parent_BookSelectFragment extends Fragment implements View.OnClickListener {

    TabLayout bottomTabLayout;
    TabLayout topTabLayout;
    ViewPager viewPager;
    TextView title;
    Button qrButton;
    EditText searchEtx;
    RelativeLayout qr_bt;
    IntentIntegrator qrScan;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = null;
        view = inflater.inflate(R.layout.activity_home, container, false);

        cast(view);
        qr_bt.setOnClickListener(this);
        setTYpeFaces();

        setTopTabLatout();

        return  view;

    }




    private void setTYpeFaces() {
        Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileBold.ttf");

        title.setTypeface(myTypeface);
        qrButton.setTypeface(myTypeface);
        searchEtx.setTypeface(myTypeface);
    }





    private void cast(View view) {


        topTabLayout = (TabLayout) view.findViewById(R.id.tab2);
        viewPager=(ViewPager) view.findViewById(R.id.view_pager);
        title = (TextView) view.findViewById(R.id.title);
        qrButton =(Button) view.findViewById(R.id.qr_button);
        searchEtx = (EditText) view.findViewById(R.id.edit_text);
        qr_bt=(RelativeLayout) view.findViewById(R.id.qr_layout);
        qrScan = new IntentIntegrator(getActivity());
    }






    private void setTopTabLatout() {



        //create tabs
        topTabLayout.addTab(topTabLayout.newTab().setText("کتابخانه"));
        topTabLayout.addTab(topTabLayout.newTab().setText("مطالب"));


        //create an adapter
        final PagerAdapter_homeActivity adapter = new PagerAdapter_homeActivity(getChildFragmentManager(), topTabLayout.getTabCount());

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
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        //viewPager page change listener
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position,false);
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








    @Override
    public void onClick(View view) {

        qrScan.setBeepEnabled(false);


        qrScan.setCameraId(0);
        qrScan.setOrientationLocked(false);
        qrScan.setPrompt("لطفا دوربین را بر روی بارکد بگیرید ");
        qrScan.initiateScan();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(getActivity(), "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                try {
                    String s=result.getContents();
                    Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }





}
