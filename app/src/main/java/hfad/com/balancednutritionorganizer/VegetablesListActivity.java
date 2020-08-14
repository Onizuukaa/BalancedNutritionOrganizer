package hfad.com.balancednutritionorganizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

import hfad.com.balancednutritionorganizer.adapters.RecyclerViewSpecificProductListAdapter;
import hfad.com.balancednutritionorganizer.database_things.DatabaseAccess;

public class VegetablesListActivity extends AppCompatActivity {
    DatabaseAccess databaseAccess;
    Cursor cursorForVegetables;

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
        setContentView(R.layout.activity_vegetables_list);
        setTitle("Vegetables");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        cursorForVegetables = databaseAccess.getAllDataFromTableVegetables();

        createExampleList();
    }

    public void createExampleList() {

        while (cursorForVegetables.moveToNext()) {
            mProductNames.add(cursorForVegetables.getString(0));
            mProductImage.add(cursorForVegetables.getString(1));
            mProductCalories.add(cursorForVegetables.getString(2));
            mProductCarbohydrates.add(cursorForVegetables.getString(3));
            mProductSugar.add(cursorForVegetables.getString(4));
            mProductFats.add(cursorForVegetables.getString(5));
            mProductSaturatedFats.add(cursorForVegetables.getString(6));
            mProductProtein.add(cursorForVegetables.getString(7));
        }
        cursorForVegetables.close();
        databaseAccess.close();

        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.vegetablesRecyclerView);
        RecyclerViewSpecificProductListAdapter adapter = new RecyclerViewSpecificProductListAdapter(this, mProductImage, mProductNames, mProductCalories, mProductCarbohydrates,
                mProductSugar, mProductFats, mProductSaturatedFats, mProductProtein);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}