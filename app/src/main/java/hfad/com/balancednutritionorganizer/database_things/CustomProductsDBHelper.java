package hfad.com.balancednutritionorganizer.database_things;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import hfad.com.balancednutritionorganizer.database_things.CustomProductsColumns.*;

public class CustomProductsDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "customProducts.db";
    public static final int DATABASE_VERSION = 1;

    public CustomProductsDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        final String SQL_CREATE_CUSTOMPRODUCTS_TABLE = "CREATE TABLE " +
                CustomProductsColumns.CustomProductsColumnsEntry.TABLE_NAME + " (" +
                CustomProductsColumns.CustomProductsColumnsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CustomProductsColumns.CustomProductsColumnsEntry.COLUMN_productName + " TEXT NOT NULL, " +
                CustomProductsColumns.CustomProductsColumnsEntry.COLUMN_productImage + " Uri NOT NULL, " +
                CustomProductsColumns.CustomProductsColumnsEntry.COLUMN_productCalories + " DOUBLE NOT NULL, " +
                CustomProductsColumns.CustomProductsColumnsEntry.COLUMN_productCarbohydrates + " DOUBLE NOT NULL, " +
                CustomProductsColumns.CustomProductsColumnsEntry.COLUMN_productSugar + " DOUBLE NOT NULL, " +
                CustomProductsColumns.CustomProductsColumnsEntry.COLUMN_productFats + " DOUBLE NOT NULL, " +
                CustomProductsColumns.CustomProductsColumnsEntry.COLUMN_productSaturatedFats + " DOUBLE NOT NULL, " +
                CustomProductsColumns.CustomProductsColumnsEntry.COLUMN_productProtein + " DOUBLE NOT NULL " +

                ");";
        db.execSQL(SQL_CREATE_CUSTOMPRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CustomProductsColumnsEntry.TABLE_NAME);
    }
}
