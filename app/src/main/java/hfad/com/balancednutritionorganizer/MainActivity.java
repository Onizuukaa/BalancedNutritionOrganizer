package hfad.com.balancednutritionorganizer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity implements DialogSetGoalWater.WaterDialogListener, DialogSetCupWater.ExampleDialogListener {
    ProgressBar progressBarWater;
    TextView textView_progressBarWater;
    Button buttonAddWater;
    int pbCounter = 0, tvDrunk = 0, tvGlassCapacity = 250,
            pbGlassCapacity = 125000000, waterGoal = 2000;
    String waterGoalForTextView = "/2000 ml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Balanced Nutrition Organizer");
        initViews();
        progressBarWater.setMax(1000000000);
    }

    @Override
    public void setCup(String username) {
        tvGlassCapacity = parseInt(username);
        buttonAddWater.setText(getString(R.string.ADD) + " " + tvGlassCapacity + " ML " + getString(R.string.Water2));
        pbGlassCapacity = (int) ((tvGlassCapacity / (double)waterGoal) * 1000000000);
    }

    @Override
    public void setGoal(String weight) {
        waterGoal = (int) Math.round( (parseDouble(weight) * 0.030) * 1000 ); // //waga używana do policzenia zapotrzebowania, wynik 2009,9999998 coś takiego i zaokrąglony wynik do 2010
        waterGoalForTextView = tvDrunk + "/" + waterGoal + " ml";
        textView_progressBarWater.setText(waterGoalForTextView + "");
        pbGlassCapacity = (int) ((tvGlassCapacity / (double) waterGoal) * 1000000000); //2010 w double czyli może 2010.0 i po to żeby podzielić przez to
        pbCounter = (int) (((double) tvDrunk / (double) waterGoal) * 1000000000);
        progressBarWater.setProgress(pbCounter);
        // pojemność szklanki w int dla progress bar ( pojemność szklanki dla textView / nowy cel do wypicia)
    }

    public void resetWater(View view) {
        progressBarWater.setProgress(0);
        pbCounter = 0;
        tvDrunk = 0;
        textView_progressBarWater.setText(tvDrunk + waterGoalForTextView);
    }

    public void button_addWater(View view) {
        pbCounter += pbGlassCapacity;
        progressBarWater.setProgress(pbCounter);
        tvDrunk += tvGlassCapacity;
        waterGoalForTextView = "/" + waterGoal + " ml";
        textView_progressBarWater.setText(tvDrunk + waterGoalForTextView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        tvDrunk = sharedPref.getInt("tvDrunk", 0);
        waterGoal = sharedPref.getInt("waterGoal", 2000);
        pbCounter = sharedPref.getInt("pbCounter", 0);
        pbGlassCapacity = sharedPref.getInt("pbGlassCapacity", 125000000);
        tvGlassCapacity = sharedPref.getInt("tvGlassCapacity", 250);
        buttonAddWater.setText(getString(R.string.ADD) + " " + tvGlassCapacity + " ML " + getString(R.string.Water2));
        progressBarWater.setProgress(pbCounter);
        waterGoalForTextView = "/" + waterGoal + " ml";
        textView_progressBarWater.setText(tvDrunk + waterGoalForTextView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("tvDrunk", tvDrunk);
        editor.putInt("waterGoal", waterGoal);
        editor.putInt("pbCounter", pbCounter);
        editor.putInt("pbGlassCapacity", pbGlassCapacity);
        editor.putInt("tvGlassCapacity", tvGlassCapacity);
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

    public void openDialogWater(View view) {
        DialogSetGoalWater dialogSetGoalWater = new DialogSetGoalWater();
        dialogSetGoalWater.show(getSupportFragmentManager(), "example dialog");
    }

    public void openDialogSetCup(View view) {
        DialogSetCupWater dialogSetCupWater = new DialogSetCupWater();
        dialogSetCupWater.show(getSupportFragmentManager(), "example dialog");
    }

    private void initViews() {
        progressBarWater = findViewById(R.id.progressBarWater);
        textView_progressBarWater = findViewById(R.id.textView_progressBarWater);
        buttonAddWater = findViewById(R.id.buttonAddWater);
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