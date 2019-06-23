package mandh.ir.myapplication.activitys;

import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import mandh.ir.myapplication.R;
import mandh.ir.myapplication.adapters.fragmentPagerAdapter.ImagePagerAdapter;
import mandh.ir.myapplication.adapters.fragmentPagerAdapter.MainPagerAdapter;
import mandh.ir.myapplication.models.Pics;

import static mandh.ir.myapplication.forHelp.G.context;

public class ImageActivity extends AppCompatActivity {

    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        cast();
        setViewPager();

    }
    private void cast() {
        viewPager =(ViewPager) findViewById(R.id.view_pager_image);
    }


    private void setViewPager() {



        ArrayList<Pics> pics=new ArrayList<>();
        pics.add(new Pics("تصویر اول"," این مربوط به تصسویر اول است",String.valueOf(R.drawable.pic1)));
        pics.add(new Pics("تصویر دوم","این مربوط به تصسویر دوم است",String.valueOf(R.drawable.pic2)));
        pics.add(new Pics("تصویر سوم","این مربوط به تصسویر سوم است",String.valueOf(R.drawable.pic3)));
        pics.add(new Pics("تصویر چهارم","این مربوط به تصسویر چهارم است",String.valueOf(R.drawable.pic4)));

        final ImagePagerAdapter adapter = new ImagePagerAdapter(getSupportFragmentManager(),pics.size(),pics);

        //setting viewPager
        viewPager.setAdapter(adapter);


    }
}
