package hfad.com.balancednutritionorganizer.database_things;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import hfad.com.balancednutritionorganizer.database_things.ComposedMealsColumns.*;

import androidx.annotation.Nullable;

public class ComposedMealsDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "composedMeals.db";
    public static final int DATABASE_VERSION = 1;

    public ComposedMealsDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_COMPOSEDMEALS_TABLE = "CREATE TABLE " +
                ComposedMealsColumnsEntry.TABLE_NAME + " (" +
                ComposedMealsColumnsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ComposedMealsColumnsEntry.COLUMN_MEALNAME + " TEXT NOT NULL, " +
                ComposedMealsColumnsEntry.COLUMN_CALORIES + " DOUBLE NOT NULL, " +
                ComposedMealsColumnsEntry.COLUMN_WEIGHT + " DOUBLE NOT NULL, " +
                ComposedMealsColumnsEntry.COLUMN_CARBO + " DOUBLE NOT NULL, " +
                ComposedMealsColumnsEntry.COLUMN_SUGAR + " DOUBLE NOT NULL, " +
                ComposedMealsColumnsEntry.COLUMN_PROTEIN + " DOUBLE NOT NULL, " +
                ComposedMealsColumnsEntry.COLUMN_FATS + " DOUBLE NOT NULL, " +
                ComposedMealsColumnsEntry.COLUMN_SATURATEDFATS + " DOUBLE NOT NULL, " +
                ComposedMealsColumnsEntry.COLUMN_PRODUCTSINCLUDED + " TEXT NOT NULL " +
                ");";
        db.execSQL(SQL_CREATE_COMPOSEDMEALS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + ComposedMealsColumnsEntry.TABLE_NAME);
            onCreate(db);
    }
}
