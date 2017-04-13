package scy.com.myreader;

import android.app.Application;
import android.util.Log;

/**
 * Created by Administrator on 2017/4/12.
 */

public class MyApplication extends Application {

    public static MyApplication myApplication;

    public static MyApplication getMyApplication(){
        if(myApplication == null){

        }
        return myApplication;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }
}
