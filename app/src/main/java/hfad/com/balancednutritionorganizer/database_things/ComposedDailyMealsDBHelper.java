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
                ComposedDailyMealsColumnsEntry.COLUMN_composedDailyMeals_Name + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_ComposedDailyMeals_KCAL_SUM + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_ComposedDailyMeals_WEIGHT_SUM + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_ComposedDailyMeals_CARBOHYDRATES_SUM + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_ComposedDailyMeals_SUGAR_SUM + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_ComposedDailyMeals_FATS_SUM + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_ComposedDailyMeals_saturatedFATS_SUM + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_ComposedDailyMeals_PROTEIN_SUM + " TEXT NOT NULL, " +

                ComposedDailyMealsColumnsEntry.COLUMN_FirstProduct_NAME + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_SecondProduct_NAME + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_ThirdProduct_NAME + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_FourthProduct_NAME + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_FifthProduct_NAME + " TEXT NOT NULL, " +

                ComposedDailyMealsColumnsEntry.COLUMN_FirstProduct_KCAL + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_SecondProduct_KCAL + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_ThirdProduct_KCAL + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_FourthProduct_KCAL + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_FifthProduct_KCAL + " TEXT NOT NULL, " +

                ComposedDailyMealsColumnsEntry.COLUMN_FirstProduct_WEIGHT + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_SecondProduct_WEIGHT + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_ThirdProduct_WEIGHT + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_FourthProduct_WEIGHT + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_FifthProduct_WEIGHT + " TEXT NOT NULL, " +

                ComposedDailyMealsColumnsEntry.COLUMN_FirstProduct_CARBOHYDRATES + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_SecondProduct_CARBOHYDRATES + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_ThirdProduct_CARBOHYDRATES + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_FourthProduct_CARBOHYDRATES + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_FifthProduct_CARBOHYDRATES + " TEXT NOT NULL, " +

                ComposedDailyMealsColumnsEntry.COLUMN_FirstProduct_SUGAR + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_SecondProduct_SUGAR + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_ThirdProduct_SUGAR + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_FourthProduct_SUGAR + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_FifthProduct_SUGAR + " TEXT NOT NULL, " +

                ComposedDailyMealsColumnsEntry.COLUMN_FirstProduct_FATS + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_SecondProduct_FATS + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_ThirdProduct_FATS + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_FourthProduct_FATS + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_FifthProduct_FATS + " TEXT NOT NULL, " +

                ComposedDailyMealsColumnsEntry.COLUMN_FirstProduct_saturatedFATS + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_SecondProduct_saturatedFATS + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_ThirdProduct_saturatedFATS + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_FourthProduct_saturatedFATS + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_FifthProduct_saturatedFATS + " TEXT NOT NULL, " +

                ComposedDailyMealsColumnsEntry.COLUMN_FirstProduct_PROTEIN + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_SecondProduct_PROTEIN + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_ThirdProduct_PROTEIN + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_FourthProduct_PROTEIN + " TEXT NOT NULL, " +
                ComposedDailyMealsColumnsEntry.COLUMN_FifthProduct_PROTEIN + " TEXT NOT NULL, " +

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
