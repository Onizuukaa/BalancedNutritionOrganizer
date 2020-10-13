package hfad.com.balancednutritionorganizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import hfad.com.balancednutritionorganizer.adapters.RecyclerViewComposedMealsAdapter;

public class ComposedMealsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewComposedMealsAdapter adapter;
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
    Cursor cursor;
    Double testowa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composed_meals);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
testowa = 33.3;
        //textView = findViewById(R.id.test);
        //object = new ComposingDishesActivity();
       // cursor = object.lala();
        //test();
        initRecyclerView();
    }

    public void test() {
        textView.setText(cursor.getDouble(2) + "");
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.composedMealsRecyclerView);
        //adapter = new RecyclerViewComposedMealsAdapter(this, productNameArrayList, productCaloriesArrayList);
        adapter = new RecyclerViewComposedMealsAdapter(this, productNameArrayList, productCaloriesArrayList);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}