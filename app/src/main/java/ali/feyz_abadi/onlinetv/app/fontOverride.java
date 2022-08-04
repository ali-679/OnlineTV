package ali.feyz_abadi.onlinetv.app;

import android.content.Context;
import android.graphics.Typeface;

import java.lang.reflect.Field;

public class fontOverride {
    public static void setDefaultFont(Context context,String font){
       final Typeface typeface=Typeface.createFromAsset(context.getAssets(),font);
        try {
            final Field field=Typeface.class.getDeclaredField("MONOSPACE");
            field.setAccessible(true);
            field.set(null,typeface);
        }catch (Exception ignored){}
    }
}
