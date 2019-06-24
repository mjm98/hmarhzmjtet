package mandh.ir.myapplication.adapters.fragmentPagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import mandh.ir.myapplication.fragments.bookSelect_fragment.Parent_BookSelectFragment;
import mandh.ir.myapplication.fragments.bookContent_fragments.Parent_BookContentFragment;


/**
 *this class is a fragment page adapter for fragments of class tab view
 */

public class HomePagerAdapter extends FragmentPagerAdapter {

    int tabCount;
    public static int mpage;


    public HomePagerAdapter(FragmentManager fm, int numberOfTabs  ) {
        super(fm);
        this.tabCount = numberOfTabs;

    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                mpage=0;
                Parent_BookSelectFragment tab1 = new Parent_BookSelectFragment();
                return tab1;

            case 1:
                mpage=1;
                Parent_BookContentFragment tab2 = new Parent_BookContentFragment();
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