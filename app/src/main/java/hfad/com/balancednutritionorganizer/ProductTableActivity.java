package hfad.com.balancednutritionorganizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class ProductTableActivity extends AppCompatActivity {
    ImageView imageViewVegetables;
    ImageView imageViewFruits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_table);
        setTitle("Products Table");

        imageViewVegetables = (ImageView) findViewById(R.id.imageViewVegetablesTable);
        imageViewVegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVegetablesListActivity();
            }
        });
        imageViewFruits = (ImageView) findViewById(R.id.imageViewFruitsTable);
        imageViewFruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFruitsListActivity();
            }
        });
    }

    public void openVegetablesListActivity() {
        Intent intent = new Intent(this, VegetablesListActivity.class);
        startActivity(intent);
    }

    public void openFruitsListActivity() {
        Intent intent = new Intent(this, FruitsListActivity.class);
        startActivity(intent);
    }

}