package hfad.com.balancednutritionorganizer.database_things;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import hfad.com.balancednutritionorganizer.R;

public class DatabaseOpenHelper extends SQLiteAssetHelper {
    //private static final String DATABASE_NAME = "productsDatabase.db";
    private static final int DATABASE_VERSION = 1;

    // constructor
    public DatabaseOpenHelper(Context context) {
        //super(context, DATABASE_NAME, null, DATABASE_VERSION);
        super(context, context.getString(R.string.db_name), null, DATABASE_VERSION);
    }
}
