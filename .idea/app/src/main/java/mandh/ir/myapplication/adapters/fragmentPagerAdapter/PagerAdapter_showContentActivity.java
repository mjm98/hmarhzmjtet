package mandh.ir.myapplication.adapters.fragmentPagerAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import mandh.ir.myapplication.fragments.bookContent_fragments.BookContentFragmentEmpty2;
import mandh.ir.myapplication.fragments.bookContent_fragments.BookContentFragmentEmpty3;
import mandh.ir.myapplication.fragments.bookContent_fragments.BookContentMainFragment;


/**
 *this class is a fragment page adapter for fragments of class tab view
 */

public class PagerAdapter_showContentActivity extends FragmentPagerAdapter {

    int tabCount;
    public static int mpage;
    int bookid;

    public PagerAdapter_showContentActivity(FragmentManager fm, int numberOfTabs,int id) {
        super(fm);
        this.tabCount = numberOfTabs;
        this.bookid=id;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                mpage=0;
                BookContentMainFragment tab1 = newInstance(bookid);
                return tab1;

            case 1:
                mpage=1;
                BookContentFragmentEmpty2 tab2 = new BookContentFragmentEmpty2();
                return tab2;

            case 2:
                mpage=2;
                BookContentFragmentEmpty3 tab3 = new BookContentFragmentEmpty3();
                return tab3;



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