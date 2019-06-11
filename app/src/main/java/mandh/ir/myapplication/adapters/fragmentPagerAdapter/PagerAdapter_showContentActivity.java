package mandh.ir.myapplication.adapters.fragmentPagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import mandh.ir.myapplication.fragments.showContent_fragments.FragmentEmpty2;
import mandh.ir.myapplication.fragments.showContent_fragments.FragmentEmpty3;
import mandh.ir.myapplication.fragments.showContent_fragments.ShowContentMainFragment;


/**
 *this class is a fragment page adapter for fragments of class tab view
 */

public class PagerAdapter_showContentActivity extends FragmentPagerAdapter {

    int tabCount;
    public static int mpage;

    public PagerAdapter_showContentActivity(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.tabCount = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                mpage=0;
                ShowContentMainFragment tab1 = new ShowContentMainFragment();
                return tab1;

            case 1:
                mpage=1;
                FragmentEmpty2 tab2 = new FragmentEmpty2();
                return tab2;

            case 2:
                mpage=2;
                FragmentEmpty3 tab3 = new FragmentEmpty3();
                return tab3;



            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}