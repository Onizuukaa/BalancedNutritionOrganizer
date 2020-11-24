package hfad.com.balancednutritionorganizer.database_things;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import hfad.com.balancednutritionorganizer.R;

public class DatabaseOpenHelper extends SQLiteAssetHelper {
    private static final int DATABASE_VERSION = 1;
    public DatabaseOpenHelper(Context context) {
        super(context, context.getString(R.string.db_name), null, DATABASE_VERSION); }}
