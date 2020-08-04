package hfad.com.balancednutritionorganizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

public class FruitsListActivity extends AppCompatActivity {
    DatabaseAccess databaseAccess;
    Cursor cursorForFruits;

    private ArrayList<String> mProductNames = new ArrayList<>();
    private ArrayList<String> mProductCalories = new ArrayList<>();
    private ArrayList<String> mProductImage = new ArrayList<>();
    private ArrayList<String> mProductCarbohydrates = new ArrayList<>();
    private ArrayList<String> mProductSugar = new ArrayList<>();
    private ArrayList<String> mProductFats = new ArrayList<>();
    private ArrayList<String> mProductSaturatedFats = new ArrayList<>();
    private ArrayList<String> mProductProtein = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits_list);
        setTitle("Fruits");
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        cursorForFruits = databaseAccess.getAllDataFromTableFruits();

        createExampleList();
    }

    public void createExampleList() {
        while (cursorForFruits.moveToNext()) {
            mProductNames.add(cursorForFruits.getString(0));
            mProductImage.add(cursorForFruits.getString(1));
            mProductCalories.add(cursorForFruits.getString(2));
            mProductCarbohydrates.add(cursorForFruits.getString(3));
            mProductSugar.add(cursorForFruits.getString(4));
            mProductFats.add(cursorForFruits.getString(5));
            mProductSaturatedFats.add(cursorForFruits.getString(6));
            mProductProtein.add(cursorForFruits.getString(7));
        }
        cursorForFruits.close();
        databaseAccess.close();

        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.fruitsRecyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mProductImage, mProductNames, mProductCalories, mProductCarbohydrates,
                mProductSugar, mProductFats, mProductSaturatedFats, mProductProtein);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}