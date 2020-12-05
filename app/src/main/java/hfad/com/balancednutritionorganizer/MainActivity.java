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
import android.widget.Toast;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity implements DialogSetGoalWater.WaterDialogListener, ExampleDialog.ExampleDialogListener {
    ProgressBar progressBarWater;
    TextView textView_progressBarWater;
    Button buttonAddWater;
    int glassOfWaterForProgressBarCounter = 0, waterForTextViewValueStart = 0, capacityForGlassOfWater_ForTextView = 250,
            glassCapacity_ForProgressBar = 125000000, waterGoal = 2000;
    double maksymalnaWartosc_double = 2000;
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
        capacityForGlassOfWater_ForTextView = parseInt(username);
        buttonAddWater.setText(getString(R.string.ADD) + " " + capacityForGlassOfWater_ForTextView + " ML " + getString(R.string.Water));
        glassCapacity_ForProgressBar = (int) ((capacityForGlassOfWater_ForTextView / maksymalnaWartosc_double) * 1000000000);
    }

    @Override
    public void setGoal(String weight) {
        waterGoal = (int) Math.round( (parseDouble(weight) * 0.030) * 1000 ); // //waga używana do policzenia zapotrzebowania, wynik 2009,9999998 coś takiego i zaokrąglony wynik do 2010
        waterGoalForTextView = waterForTextViewValueStart + "/" + waterGoal + " ml";
        textView_progressBarWater.setText(waterGoalForTextView + "");
        glassCapacity_ForProgressBar = (int) ((capacityForGlassOfWater_ForTextView / (double) waterGoal) * 1000000000); //2010 w double czyli może 2010.0 i po to żeby podzielić przez to
        glassOfWaterForProgressBarCounter = (int) (((double) waterForTextViewValueStart / (double) waterGoal) * 1000000000);
        progressBarWater.setProgress(glassOfWaterForProgressBarCounter);
        // pojemność szklanki w int dla progress bar ( pojemność szklanki dla textView / nowy cel do wypicia)
        maksymalnaWartosc_double = waterGoal;
    }

    public void resetWater(View view) {
        progressBarWater.setProgress(0);
        glassOfWaterForProgressBarCounter = 0;
        waterForTextViewValueStart = 0;
        textView_progressBarWater.setText(waterForTextViewValueStart + waterGoalForTextView);
    }

    public void button_addWater(View view) {
        glassOfWaterForProgressBarCounter += glassCapacity_ForProgressBar;
        progressBarWater.setProgress(glassOfWaterForProgressBarCounter);
        waterForTextViewValueStart += capacityForGlassOfWater_ForTextView;
        waterGoalForTextView = "/" + waterGoal + " ml";
        textView_progressBarWater.setText(waterForTextViewValueStart + waterGoalForTextView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        waterForTextViewValueStart = sharedPref.getInt("waterForTextViewValueStart", 0);
        waterGoal = sharedPref.getInt("waterGoal", 2000);
        glassOfWaterForProgressBarCounter = sharedPref.getInt("glassOfWaterForProgressBarCounter", 0);
        glassCapacity_ForProgressBar = sharedPref.getInt("glassCapacity_ForProgressBar", 125000000);
        capacityForGlassOfWater_ForTextView = sharedPref.getInt("capacityForGlassOfWater_ForTextView", 250);
        buttonAddWater.setText(getString(R.string.ADD) + " " + capacityForGlassOfWater_ForTextView + " ML " + getString(R.string.Water));
        progressBarWater.setProgress(glassOfWaterForProgressBarCounter);
        waterGoalForTextView = "/" + waterGoal + " ml";
        textView_progressBarWater.setText(waterForTextViewValueStart + waterGoalForTextView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("waterForTextViewValueStart", waterForTextViewValueStart);
        editor.putInt("waterGoal", waterGoal);
        editor.putInt("glassOfWaterForProgressBarCounter", glassOfWaterForProgressBarCounter);
        editor.putInt("glassCapacity_ForProgressBar", glassCapacity_ForProgressBar);
        editor.putInt("capacityForGlassOfWater_ForTextView", capacityForGlassOfWater_ForTextView);
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
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
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