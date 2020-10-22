package hfad.com.balancednutritionorganizer.database_things;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor cursor = null;

    //private constructor so that object creation from outside the class is avoided
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    //to return the single instance of database
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    //to open the database
    public void open() {
        this.db = openHelper.getWritableDatabase();
    }

    //closing the database connection
    public void close() {
        if (db != null) {
            this.db.close();
        }
    }

    //Method to query and return the result from database. Here from table vegetablesValues
    public Cursor getAllDataFromTableVegetables() {
        cursor = db.rawQuery("select * from vegetablesValues", null);
        return cursor;
    }

    public Cursor getAllDataFromTableFruits() {
        cursor = db.rawQuery("select * from fruitsValues", null);
        return cursor;
    }

    public Cursor getAllDataFromTableGrainProducts() {
        cursor = db.rawQuery("select * from grainProductsValues", null);
        return cursor;
    }

    public Cursor getAllDataFromTableDairy() {
        cursor = db.rawQuery("select * from dairyValues", null);
        return cursor;
    }

    public Cursor getAllDataFromTableFish() {
        cursor = db.rawQuery("select * from fishValues", null);
        return cursor;
    }

    public Cursor getAllDataFromTableMeat() {
        cursor = db.rawQuery("select * from meatValues", null);
        return cursor;
    }

    public Cursor getAllDataFromTableSweets() {
        cursor = db.rawQuery("select * from sweetsValues", null);
        return cursor;
    }

    public Cursor getAllDataFromTableDrinks() {
        cursor = db.rawQuery("select * from drinksValues", null);
        return cursor;
    }


}
