package ali.feyz_abadi.onlinetv.app;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

public class application extends Application {

   private static Context context;



    @Override
    public void onCreate() {
        super.onCreate();

        context=this;
        setFont();
    }

    public static Context getContext(){

        return context;
    }

    private void setFont(){
        String font="fonts/ir.ttf";
        fontOverride.setDefaultFont(context,font);
    }

}
