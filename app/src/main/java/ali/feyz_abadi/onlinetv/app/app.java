package ali.feyz_abadi.onlinetv.app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

public class app {

    public static final String BASE_URL = "http://alifeyzabadi.ir/onlineTV/";

    private static final String TAG = "tv";

    public static final String DB_NAME = "favorite";
    public static final int DB_VERSION = 1;


    public static final String TB_favorite= "fav";

    public static final String ID= "id";
    public static final String CATEGORY_ID= "category_id";
    public static final String NAME= "name";
    public static final String IMG_LINK= "img_link";
    public static final String PLAY_LINK= "play_link";
    public static final String CATEGORY= "category";
    public static final String TYPE= "type";

    public static void log(String message) {
        Log.e(TAG, message);
    }

    public static void toast(String message) {
        Toast.makeText(application.getContext(), message, Toast.LENGTH_LONG).show();
    }

    public static Boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) application.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
