package hfad.com.balancednutritionorganizer.database_things;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import hfad.com.balancednutritionorganizer.database_things.ComposedDailyMealsColumns.*;

public class ComposedDailyMealsDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "composedDailyMeals.db";
    public static final int DATABASE_VERSION = 1;

    public ComposedDailyMealsDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_COMPOSEDDAILYMEALS_TABLE = "CREATE TABLE " +
                ComposedDailyMealsColumnsEntry.TABLE_NAME + " (" +
                ComposedDailyMealsColumnsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ComposedDailyMealsColumnsEntry.COLUMN_nameComposedDailyMeals + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_nameComposedDailyMeals_KCAL_SUM + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_nameFirstProduct + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_nameSecondProduct + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_nameThirdProduct + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_nameFourthProduct + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_nameFifthProduct + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_macrosFirstProductsKcalWeight + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_macrosSecondProductsKcalWeight + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_macrosThirdProductsKcalWeight + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_macrosFourthProductsKcalWeight + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_macrosFifthProductsKcalWeight + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_productsIncludedFirstProduct + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_productsIncludedSecondProduct + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_productsIncludedThirdProduct + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_productsIncludedFourthProduct + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_productsIncludedFifthProduct + " TEXT NOT NULL " +
                ");";
        db.execSQL(SQL_CREATE_COMPOSEDDAILYMEALS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ComposedDailyMealsColumnsEntry.TABLE_NAME);
        onCreate(db);
    }
}
