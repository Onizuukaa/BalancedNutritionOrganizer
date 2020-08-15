package hfad.com.balancednutritionorganizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBarWater;
    TextView textView_progressBarWater;
    int glassOfWater = 10, water = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    public void imageView_openYourDish(View view) {
        Intent intent = new Intent(this, YourDishesActivity.class);
        startActivity(intent);
    }

    public void imageView_openDailyMeals(View view) {
        Intent intent = new Intent(this, DailyMealsActivity.class);
        startActivity(intent);
    }

    public void imageView_openProductTableActivity(View view) {
        Intent intent = new Intent(this, ProductTableActivity.class);
        startActivity(intent);
    }

    public void imageView_openBmiActivity(View view) {
        Intent intent = new Intent(this, BmiActivity.class);
        startActivity(intent);
    }

    public void resetWater(View view) {
        progressBarWater.setProgress(0);
        glassOfWater = 10;
        water = 0;
        textView_progressBarWater.setText(water + "/2500");
    }

    public void imageView_OpenComposeTheDish(View view) {
        Intent intent = new Intent(this, ComposingDishesActivity.class);
        startActivity(intent);
    }

    public void button_addWater(View view){
        progressBarWater.setProgress(glassOfWater);
        glassOfWater += 10;
        water+=250;
        textView_progressBarWater.setText(water + "/2500");
    }

    private void initViews(){
        progressBarWater = findViewById(R.id.progressBarWater);
        textView_progressBarWater = findViewById(R.id.textView_progressBarWater);
    }
}