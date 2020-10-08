package hfad.com.balancednutritionorganizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ComposedDailyMealsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composed_daily_meals);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}