package mandh.ir.myapplication.adapters.fragmentPagerAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import mandh.ir.myapplication.fragments.Main_Fragments.EmptyFragment;
import mandh.ir.myapplication.fragments.Main_Fragments.HomeFragment;
import mandh.ir.myapplication.fragments.bookContent_fragments.BookContentMainFragment;


/**
 *this class is a fragment page adapter for fragments of class tab view
 */

public class MainPagerAdapter extends FragmentPagerAdapter {

    int tabCount;
    public static int mpage;


    public MainPagerAdapter(FragmentManager fm, int numberOfTabs  ) {
        super(fm);
        this.tabCount = numberOfTabs;

    }



    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                mpage=0;
                EmptyFragment tab1 = new EmptyFragment();
                return tab1;

            case 1:
                mpage=1;
                EmptyFragment tab2 = new EmptyFragment();
                return tab2;

            case 2:
                mpage=2;
                HomeFragment tab3 = new HomeFragment();
                return tab3;

            case 3:
                mpage=3;
                EmptyFragment tab4 = new EmptyFragment();
                return tab4;

            case 4:
                mpage=4;
                EmptyFragment tab5 = new EmptyFragment();
                return tab5;



            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }


    public static BookContentMainFragment newInstance(int someInt) {
        BookContentMainFragment myFragment = new BookContentMainFragment();

        Bundle args = new Bundle();
        args.putInt("id", someInt);
        myFragment.setArguments(args);

        return myFragment;

    }
}