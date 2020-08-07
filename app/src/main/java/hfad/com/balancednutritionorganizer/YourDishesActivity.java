package hfad.com.balancednutritionorganizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class YourDishesActivity extends AppCompatActivity {

    RecyclerViewAdapter3 adapter;
    private ArrayList<String> productNameArrayList = new ArrayList<>();
    private ArrayList<String> productCaloriesArrayList = new ArrayList<>();
    private ArrayList<String> productGramArrayList = new ArrayList<>();
    private ArrayList<String> productCarbohydratesArrayList = new ArrayList<>();
    private ArrayList<String> productSugarArrayList = new ArrayList<>();
    private ArrayList<String> productFatsArrayList = new ArrayList<>();
    private ArrayList<String> productSaturatedFatsArrayList = new ArrayList<>();
    private ArrayList<String> productProteinArrayList = new ArrayList<>();

    private ArrayList<String> mProductImage = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_dishes);
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.yourDishesRecyclerView);
        adapter = new RecyclerViewAdapter3(this, mProductImage, productNameArrayList, productCaloriesArrayList,
                productCarbohydratesArrayList, productSugarArrayList, productFatsArrayList, productSaturatedFatsArrayList,
                productProteinArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}