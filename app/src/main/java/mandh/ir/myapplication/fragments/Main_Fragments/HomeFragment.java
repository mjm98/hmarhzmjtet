package mandh.ir.myapplication.fragments.Main_Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mandh.ir.myapplication.R;
import mandh.ir.myapplication.activitys.MainActivity;
import mandh.ir.myapplication.adapters.fragmentPagerAdapter.HomePagerAdapter;
import mandh.ir.myapplication.adapters.gradeViewAdapters.HomeGradeView_Adapter;
import mandh.ir.myapplication.fragments.bookContent_fragments.Parent_BookContentFragment;

public class HomeFragment extends Fragment implements HomeGradeView_Adapter.ItemClick {


    private static HomeFragment homeFragment;
    public static ViewPager viewPager;


    public static HomeFragment getHomeFragment() {
        return homeFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = null;
        view = inflater.inflate(R.layout.home_fragment, container, false);

        cast(view);
        homeFragment=this;

        HomePagerAdapter adapter = new HomePagerAdapter(getChildFragmentManager(),2);

        //setting viewPager
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit((adapter.getCount() > 1 ? adapter.getCount() - 1 : 1));
        viewPager.setOffscreenPageLimit(1);

        return  view;

    }

    private void cast(View view) {

         viewPager=(ViewPager) view.findViewById(R.id.view_pager);
    }




    @Override
    public void onClick(int bookId) {

        Parent_BookContentFragment fragment = new Parent_BookContentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", bookId);
        fragment.setArguments(bundle);
        viewPager.setCurrentItem(2,true);
        MainActivity.home_fragment_position=1;
    }

}
