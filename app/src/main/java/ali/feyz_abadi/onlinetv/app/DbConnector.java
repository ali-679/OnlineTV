package ali.feyz_abadi.onlinetv.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbConnector extends SQLiteOpenHelper {

    SQLiteDatabase sqLiteDatabase;

    public DbConnector(@Nullable Context context, @Nullable SQLiteDatabase.CursorFactory factory) {
        super(context, app.DB_NAME, factory, app.DB_VERSION);

        this.creatTable();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        sqLiteDatabase = db;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase getSqLiteDatabase() {
        return getWritableDatabase();
    }

    private void creatTable() {
        String table = "CREATE TABLE IF NOT EXISTS " + app.TB_favorite + "(" +
                app.ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                app.NAME + " VARCHAR(200)," +
                app.IMG_LINK + " TEXT, " +
                app.TYPE + " VARCHAR(100), " +
                app.CATEGORY_ID + " INTEGER," +
                app.PLAY_LINK + " TEXT," +
                app.CATEGORY + " VARCHAR(200)" +
                " );";
        getSqLiteDatabase().execSQL(table);
    }

}
