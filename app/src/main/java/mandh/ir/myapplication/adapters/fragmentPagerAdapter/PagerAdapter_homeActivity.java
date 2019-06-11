package mandh.ir.myapplication.adapters.fragmentPagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import mandh.ir.myapplication.fragments.home_fragment.HomeRecyclerViewFragment;
import mandh.ir.myapplication.fragments.home_fragment.homeGRadeViewFragment;


/**
 *this class is a fragment page adapter for fragments of class tab view
 */

public class PagerAdapter_homeActivity extends FragmentPagerAdapter {

    int tabCount;
    public static int mpage;

    public PagerAdapter_homeActivity(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.tabCount = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                mpage=0;
                homeGRadeViewFragment tab1 = new homeGRadeViewFragment();
                return tab1;

            case 1:
                mpage=1;
                HomeRecyclerViewFragment tab2 = new HomeRecyclerViewFragment();
                return tab2;



            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}