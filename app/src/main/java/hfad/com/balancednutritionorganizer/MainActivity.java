package hfad.com.balancednutritionorganizer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBarWater;
    TextView textView_progressBarWater;
    int glassOfWaterForProgressBar = 0, textViewWater = 0;

    Bundle extras;
    int test;
    //public static Activity fa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Balanced Nutrition Organizer");
        initViews();


        //fa = this;

    }

    public void resetWater(View view) {
        progressBarWater.setProgress(0);
        glassOfWaterForProgressBar = 0;
        textViewWater = 0;
        textView_progressBarWater.setText(textViewWater + "/2500");
    }

    public void button_addWater(View view) {
        glassOfWaterForProgressBar += 10;
        System.out.println("CO JEST: " + glassOfWaterForProgressBar);
        progressBarWater.setProgress(glassOfWaterForProgressBar);
        textViewWater += 250;
        textView_progressBarWater.setText(textViewWater + "/2500");
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        textView_progressBarWater.setText(sharedPref.getString("textViewWater","") + "/2500");
        progressBarWater.setProgress(sharedPref.getInt("glassOfWaterForProgressBar",0));
        glassOfWaterForProgressBar = sharedPref.getInt("glassOfWaterForProgressBar",0);
        textViewWater = sharedPref.getInt("textViewWater2",0);

        extras = getIntent().getExtras();
        if (extras != null) {
            test = extras.getInt("dawid");
        }
        System.out.println("DAWID EXTRAS: " + test);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("textViewWater", String.valueOf(textViewWater));
        editor.putInt("glassOfWaterForProgressBar", glassOfWaterForProgressBar);
        editor.putInt("textViewWater2", textViewWater);
        editor.commit();
    }


    public void imageView_openYourDish(View view) {
        Intent intent = new Intent(this, ComposedMealsActivity.class);
        startActivity(intent);
    }

    public void imageView_openDailyMeals(View view) {
        Intent intent = new Intent(this, ComposedDailyMealsActivity.class);
        startActivity(intent);
    }

    public void imageView_openProductTableActivity(View view) {
        Intent intent = new Intent(this, FoodTableActivity.class);
        startActivity(intent);
    }

    public void imageView_openBmiActivity(View view) {
        Intent intent = new Intent(this, BmiActivity.class);
        startActivity(intent);
    }

    public void imageView_OpenComposeTheDish(View view) {
        Intent intent = new Intent(this, ComposeMealActivity.class);
        startActivity(intent);
    }

    public void imageView_OpenCompleteMetabolism(View view) {
        Intent intent = new Intent(this, CompleteMetabolismActivity.class);
        startActivity(intent);
    }

    private void initViews() {
        progressBarWater = findViewById(R.id.progressBarWater);
        textView_progressBarWater = findViewById(R.id.textView_progressBarWater);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.healthy_info_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_healthyInfo:
                Intent intent = new Intent(this, HealthyInfoActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}