package mandh.ir.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import mandh.ir.myapplication.activitys.HomeActivity;

import static mandh.ir.myapplication.forHelp.G.context;

public class SplashActivity extends AppCompatActivity {
    ImageView sun1,sun0,boy;
    TextView splashText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sun0=(ImageView) findViewById(R.id.sun0);
        sun1=(ImageView)findViewById(R.id.sun1);
        boy=(ImageView)findViewById(R.id.boy);
        splashText=(TextView) findViewById(R.id.splashText);
        Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileMedium.ttf");
        splashText.setTypeface(myTypeface);
        RelativeLayout rl=(RelativeLayout) findViewById(R.id.sunback);

        anim(sun1);
        anim(sun0);
        anim(splashText);
        Handler handler=new Handler();
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(i);
                SplashActivity.this.finish();

            }
        };
        handler.postDelayed(runnable,800);


    }
    private void anim(View layout) {

        layout.setScaleX(0);
        layout.setScaleY(0);
        layout.animate().scaleX(1).scaleY(1).setDuration(500).start();

    }

}
