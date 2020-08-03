package hfad.com.balancednutritionorganizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView imageViewProductTable, imageViewBmiActivity, imageViewComposeTheDish, imageViewYourDishes, imageViewDailyMeals;
    ProgressBar progressBarWater;
    Button buttonAddWater;
    int glassOfWater = 10;
    String imageUrl, productCaloriesString, productCarbohydratesString, productSugarString, productFatsString,
            productSaturatedFatsString, productProteinString, productNameString, productGramString;

    private ArrayList<String> productNameArrayList = new ArrayList<>();
    private ArrayList<String> productCaloriesArrayList = new ArrayList<>();
    private ArrayList<String> productCarbohydratesArrayList = new ArrayList<>();
    private ArrayList<String> productGramArrayList = new ArrayList<>();
    private ArrayList<String> productSugarArrayList = new ArrayList<>();
    private ArrayList<String> productFatsArrayList = new ArrayList<>();
    private ArrayList<String> productSaturatedFatsArrayList = new ArrayList<>();
    private ArrayList<String> productProteinArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, new IntentFilter("INTENT_NAME"));

        progressBarWater = (ProgressBar) findViewById(R.id.progressBarWater);
        buttonAddWater = (Button) findViewById(R.id.buttonAddWater);
        buttonAddWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarWater.setProgress(glassOfWater);
                glassOfWater += 10;
            }
        });

        imageViewProductTable = (ImageView) findViewById(R.id.imageViewProductTable);
        imageViewBmiActivity = (ImageView) findViewById(R.id.imageViewBmiActivity);
        imageViewComposeTheDish = (ImageView) findViewById(R.id.imageViewComposeTheDish);
        imageViewYourDishes = (ImageView) findViewById(R.id.imageViewYourDishes);
        imageViewDailyMeals = (ImageView) findViewById(R.id.imageViewDailyMeals);

        imageViewProductTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProductTableActivity();
            }
        });
        imageViewBmiActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBmiActivity();
            }
        });
    }

    public void openYourDish(View view) {
        Intent intent = new Intent(this, YourDishesActivity.class);
        startActivity(intent);
    }

    public void openDailyMeals(View view) {
        Intent intent = new Intent(this, DailyMealsActivity.class);
        startActivity(intent);
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            productNameString = intent.getStringExtra("product_name");
            productNameArrayList.add(productNameString);
            productCaloriesString = intent.getStringExtra("product_calories");
            productCaloriesArrayList.add(productCaloriesString);
            productCarbohydratesString = intent.getStringExtra("product_carbohydrates");
            productCarbohydratesArrayList.add(productCarbohydratesString);
            productSugarString = intent.getStringExtra("product_sugar");
            productSugarArrayList.add(productSugarString);
            productFatsString = intent.getStringExtra("product_fats");
            productFatsArrayList.add(productFatsString);
            productSaturatedFatsString = intent.getStringExtra("product_saturatedFats");
            productSaturatedFatsArrayList.add(productSaturatedFatsString);
            productProteinString = intent.getStringExtra("product_protein");
            productProteinArrayList.add(productProteinString);
            productGramString = intent.getStringExtra("product_gram");
            productGramArrayList.add(productGramString);
        }
    };

    public void openProductTableActivity() {
        Intent intent = new Intent(this, ProductTableActivity.class);
        startActivity(intent);
    }

    public void openBmiActivity() {
        Intent intent = new Intent(this, BmiActivity.class);
        startActivity(intent);
    }

    public void resetWater(View view) {
        progressBarWater.setProgress(0);
        glassOfWater = 10;
    }

    //Open method Activity
    public void openComposeTheDish(View view) {
        Intent intent = new Intent(this, ComposingDishesActivity.class);
        intent.putExtra("product_name", productNameArrayList);
        intent.putExtra("product_image", imageUrl);
        intent.putExtra("product_calories", productCaloriesArrayList);
        intent.putExtra("product_carbohydrates", productCarbohydratesArrayList);
        intent.putExtra("product_sugar", productSugarArrayList);
        intent.putExtra("product_fats", productFatsArrayList);
        intent.putExtra("product_saturatedFats", productSaturatedFatsArrayList);
        intent.putExtra("product_protein", productProteinArrayList);
        intent.putExtra("product_gram", productGramArrayList);
        startActivity(intent);
    }
}