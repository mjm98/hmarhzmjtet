package mandh.ir.myapplication.forHelp;

import android.app.Application;
import android.content.Context;

/**
 * Created by k.z on 12/02/2018.
 */

public class G extends Application {

        public static Context context;


    public void onCreate() {

        super.onCreate();

        context = getApplicationContext();

    }




}


