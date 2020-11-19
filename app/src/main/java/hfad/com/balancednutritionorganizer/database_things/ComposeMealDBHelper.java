package hfad.com.balancednutritionorganizer.database_things;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import hfad.com.balancednutritionorganizer.database_things.ComposeMealColumns.*;

import androidx.annotation.Nullable;

public class ComposeMealDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "foodForCompose.db";
    public static final int DATABASE_VERSION = 1;

    public ComposeMealDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_GROCERYLIST_TABLE = "CREATE TABLE " +
                GroceryEntry.TABLE_NAME + " (" +
                GroceryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                GroceryEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                GroceryEntry.COLUMN_AMOUNT + " DOUBLE NOT NULL, " +
                GroceryEntry.COLUMN_CARBO + " DOUBLE NOT NULL, " +
                GroceryEntry.COLUMN_SUGAR + " DOUBLE NOT NULL, " +
                GroceryEntry.COLUMN_FATS + " DOUBLE NOT NULL, " +
                GroceryEntry.COLUMN_SATURATEDFATS + " DOUBLE NOT NULL, " +
                GroceryEntry.COLUMN_PROTEIN + " DOUBLE NOT NULL, " +
                GroceryEntry.COLUMN_WEIGHT + " DOUBLE NOT NULL, " +
                GroceryEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        db.execSQL(SQL_CREATE_GROCERYLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + GroceryEntry.TABLE_NAME);
            onCreate(db);
    }
}
