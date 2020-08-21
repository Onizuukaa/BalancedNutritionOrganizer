package hfad.com.balancednutritionorganizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import hfad.com.balancednutritionorganizer.adapters.RecyclerViewYourDishesAdapter;

public class YourDishesActivity extends AppCompatActivity {

    RecyclerViewYourDishesAdapter adapter;
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
    ComposingDishesActivity object;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_dishes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textView = findViewById(R.id.test);
        object = new ComposingDishesActivity();
        cursor = object.lala();
        test();
    }

    public void test() {
        textView.setText(cursor.getDouble(2) + "");
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.yourDishesRecyclerView);
        adapter = new RecyclerViewYourDishesAdapter(this, productNameArrayList, productCaloriesArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}