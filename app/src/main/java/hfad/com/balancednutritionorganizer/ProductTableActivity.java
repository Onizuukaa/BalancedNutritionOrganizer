package hfad.com.balancednutritionorganizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import hfad.com.balancednutritionorganizer.productsList.DairyListActivity;
import hfad.com.balancednutritionorganizer.productsList.DrinksListActivity;
import hfad.com.balancednutritionorganizer.productsList.FishListActivity;
import hfad.com.balancednutritionorganizer.productsList.FruitsListActivity;
import hfad.com.balancednutritionorganizer.productsList.GrainProductsListActivity;
import hfad.com.balancednutritionorganizer.productsList.MeatListActivity;
import hfad.com.balancednutritionorganizer.productsList.SweetsListActivity;
import hfad.com.balancednutritionorganizer.productsList.VegetablesListActivity;

public class ProductTableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_table);
        setTitle(R.string.Products_Table);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void openVegetablesListActivity(View view) {
        Intent intent = new Intent(this, VegetablesListActivity.class);
        startActivity(intent);
    }

    public void openFruitsListActivity(View view) {
        Intent intent = new Intent(this, FruitsListActivity.class);
        startActivity(intent);
    }

    public void openGrainProductsListActivity(View view) {
        Intent intent = new Intent(this, GrainProductsListActivity.class);
        startActivity(intent);
    }

    public void openDairyListActivity(View view) {
        Intent intent = new Intent(this, DairyListActivity.class);
        startActivity(intent);
    }

    public void openFishListActivity(View view) {
        Intent intent = new Intent(this, FishListActivity.class);
        startActivity(intent);
    }

    public void openMeatListActivity(View view) {
        Intent intent = new Intent(this, MeatListActivity.class);
        startActivity(intent);
    }

    public void openSweetsListActivity(View view) {
        Intent intent = new Intent(this, SweetsListActivity.class);
        startActivity(intent);
    }

    public void openDrinksListActivity(View view) {
        Intent intent = new Intent(this, DrinksListActivity.class);
        startActivity(intent);
    }
}