package hfad.com.balancednutritionorganizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import hfad.com.balancednutritionorganizer.adapters.RecyclerViewComposedMealsAdapter;
import hfad.com.balancednutritionorganizer.database_things.ComposedMealsColumns;
import hfad.com.balancednutritionorganizer.database_things.ComposedMealsDBHelper;

public class ComposedMealsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewComposedMealsAdapter adapter;
    Cursor cursor;
    private SQLiteDatabase mDatabaseComposedMeals;

    private ArrayList<String> productNameArrayList = new ArrayList<>();
    private ArrayList<String> productCaloriesArrayList = new ArrayList<>();
    private ArrayList<String> productGramArrayList = new ArrayList<>();
    private ArrayList<String> productCarbohydratesArrayList = new ArrayList<>();
    private ArrayList<String> productSugarArrayList = new ArrayList<>();
    private ArrayList<String> productFatsArrayList = new ArrayList<>();
    private ArrayList<String> productSaturatedFatsArrayList = new ArrayList<>();
    private ArrayList<String> productProteinArrayList = new ArrayList<>();

    private ArrayList<String> mProductImage = new ArrayList<>();

    TextView textView;
    ComposeMealActivity object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composed_meals);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //textView = findViewById(R.id.test);
        //object = new ComposingDishesActivity();
       // cursor = object.lala();
        //test();
        ComposedMealsDBHelper dbHelperComposedMeals = new ComposedMealsDBHelper(this);
        mDatabaseComposedMeals = dbHelperComposedMeals.getWritableDatabase();
        initRecyclerView();
    }

    private Cursor getAllItems() {
        return mDatabaseComposedMeals.query(
                ComposedMealsColumns.ComposedMealsColumnsEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    public void test() {
        textView.setText(cursor.getDouble(2) + "");
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.composedMealsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //adapter = new RecyclerViewComposedMealsAdapter(this, productNameArrayList, productCaloriesArrayList);
        adapter = new RecyclerViewComposedMealsAdapter(this, getAllItems());

        recyclerView.setAdapter(adapter);


        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}