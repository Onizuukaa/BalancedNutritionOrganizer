package hfad.com.balancednutritionorganizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

public class VegetablesListActivity extends AppCompatActivity {
    DatabaseHelper db;
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
        db = new DatabaseHelper(this);
        cursorForVegetables = db.allDataForVegetables();
        createExampleList();
    }

    public void createExampleList() {

        while(cursorForVegetables.moveToNext())
        {
            mProductNames.add(cursorForVegetables.getString(0));
            mProductImage.add(cursorForVegetables.getString(1));
            mProductCalories.add(cursorForVegetables.getString(2));
            mProductCarbohydrates.add(cursorForVegetables.getString(3));
            mProductSugar.add(cursorForVegetables.getString(4));
            mProductFats.add(cursorForVegetables.getString(5));
            mProductSaturatedFats.add(cursorForVegetables.getString(6));
            mProductProtein.add(cursorForVegetables.getString(7));
        }
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.vegetablesRecyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mProductImage, mProductNames, mProductCalories, mProductCarbohydrates,
                mProductSugar, mProductFats, mProductSaturatedFats, mProductProtein);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}