package hfad.com.balancednutritionorganizer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBarWater;
    TextView textView_progressBarWater;
    int glassOfWater = 10, water = 0;
//a
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
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
        Intent intent = new Intent(this, ComposeMealActivity.class);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.info_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_heartInfo:
                Intent intent = new Intent(this, HealthyInfoActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}