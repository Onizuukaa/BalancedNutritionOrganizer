package hfad.com.balancednutritionorganizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class ProductTableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_table);
        setTitle("Products Table");
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
}