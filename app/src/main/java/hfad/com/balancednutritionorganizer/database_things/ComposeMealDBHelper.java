package hfad.com.balancednutritionorganizer.database_things;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import hfad.com.balancednutritionorganizer.database_things.ComposeMealColumns.*;

import androidx.annotation.Nullable;

public class ComposeMealDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "composeMeal.db";
    public static final int DATABASE_VERSION = 1;
    public ComposeMealDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION); }
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_composeMeal_TABLE = "CREATE TABLE " +
                ComposeMealColumnsEntry.TABLE_NAME + " (" +
                ComposeMealColumnsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ComposeMealColumnsEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                ComposeMealColumnsEntry.COLUMN_AMOUNT + " DOUBLE NOT NULL, " +
                ComposeMealColumnsEntry.COLUMN_CARBO + " DOUBLE NOT NULL, " +
                ComposeMealColumnsEntry.COLUMN_SUGAR + " DOUBLE NOT NULL, " +
                ComposeMealColumnsEntry.COLUMN_FATS + " DOUBLE NOT NULL, " +
                ComposeMealColumnsEntry.COLUMN_SATURATEDFATS + " DOUBLE NOT NULL, " +
                ComposeMealColumnsEntry.COLUMN_PROTEIN + " DOUBLE NOT NULL, " +
                ComposeMealColumnsEntry.COLUMN_WEIGHT + " DOUBLE NOT NULL" +
                ");";
        db.execSQL(SQL_CREATE_composeMeal_TABLE); }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + ComposeMealColumnsEntry.TABLE_NAME);
            onCreate(db); }}
