package mandh.ir.myapplication.adapters.fragmentPagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import mandh.ir.myapplication.fragments.ImageFragment;
import mandh.ir.myapplication.fragments.bookContent_fragments.Parent_BookContentFragment;
import mandh.ir.myapplication.fragments.bookSelect_fragment.Parent_BookSelectFragment;
import mandh.ir.myapplication.models.Pics;

public class ImagePagerAdapter extends FragmentPagerAdapter {

    int tabCount;
    ArrayList<Pics> pics=new ArrayList<>();
    public ImagePagerAdapter(FragmentManager fm,int tabCount,ArrayList<Pics> pics) {
        super(fm);
        this.tabCount=tabCount;
        this.pics=pics;
    }

    @Override
    public Fragment getItem(int position) {


            return ImageFragment.newInstance(pics.get(position).getName(),Integer.valueOf(pics.get(position).getImgUri()),pics.get(position).getDes());


    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
