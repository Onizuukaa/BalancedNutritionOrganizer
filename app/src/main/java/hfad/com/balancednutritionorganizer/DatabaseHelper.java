package hfad.com.balancednutritionorganizer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "productsDatabase.db", null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            //creating tables;
//        db.execSQL("create table products(calories text,carbohydrates text, sugar text, fats text, saturatedFats text, protein text, roll_no integer primary key)");
//        db.execSQL("create table class(sub1 text,sub2 text,sub3 text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("drop table if exists products");
//        db.execSQL("drop table if exists class");
    }

    //insert data in table student
//    public boolean insertInProduct(String calories, String carbohydrates, String sugar, String fats, String saturatedFats, String protein, Integer i1){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("calories", calories);
//        contentValues.put("carbohydrates", carbohydrates);
//        contentValues.put("sugar", sugar);
//        contentValues.put("fats", fats);
//        contentValues.put("saturatedFats", saturatedFats);
//        contentValues.put("protein", protein);
//        contentValues.put("roll_no", i1);
//        long result = db.insert("products", null, contentValues);
//        if(result==-1){
//            return false;
//        }
//        else {
//            return true;
//        }
//    }

    //retrieving all data
    public Cursor allDataForVegetables() {
      SQLiteDatabase db = this.getWritableDatabase();
      Cursor cursorForVegetables = db.rawQuery("select * from vegetablesValues", null);
      return cursorForVegetables;
    }
    public Cursor allDataForFruits() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursorForFruits = db.rawQuery("select * from fruitsValues", null);
        return cursorForFruits;
    }
}
