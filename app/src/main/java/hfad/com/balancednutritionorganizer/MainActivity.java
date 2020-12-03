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

public class MainActivity extends AppCompatActivity implements DialogSetGoalWater.WaterDialogListener, ExampleDialog.ExampleDialogListener {
    ProgressBar progressBarWater;
    TextView textView_progressBarWater;
    int glassOfWaterForProgressBarCounter = 0, waterForTextViewValueZero = 0, capacityForGlassOfWater_ForTextView = 250;
    int glassCapacity_ForProgressBar = 125000000;
    Bundle extras;
    int test;
    //public static Activity fa;
    String waterGoalForTextView = "/2000 ml";
    Button buttonAddWater;
    double newWaterGoalInDouble;
    int newWaterGoal;
    double waterNeedsDouble;

    double maksymalnaWartosc_double = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Balanced Nutrition Organizer");
        initViews();
        //fa = this;
        progressBarWater.setMax(1000000000);
    }

    @Override
    public void applyTextss(String username) {
        capacityForGlassOfWater_ForTextView = parseInt(username);
        buttonAddWater.setText(getString(R.string.ADD) + " " + capacityForGlassOfWater_ForTextView + " ML " + getString(R.string.Water));
        int proporcjaNowegoNaDwaTysiace = (int)((capacityForGlassOfWater_ForTextView / maksymalnaWartosc_double) * 1000000000 );
        glassCapacity_ForProgressBar = proporcjaNowegoNaDwaTysiace;
    }

    @Override
    public void applyWeight(String weight) {
        waterNeedsDouble = (parseDouble(weight) * 0.030) * 1000; //waga używana do policzenia zapotrzebowania, wynik 2009,9999998 coś takiego
        newWaterGoal = (int) Math.round(waterNeedsDouble); // zaokrąglony wynik do 2010
        waterGoalForTextView = "/" + newWaterGoal + " ml";
        textView_progressBarWater.setText(waterForTextViewValueZero + waterGoalForTextView + "");
        newWaterGoalInDouble = newWaterGoal; //2010 w double czyli może 2010.0 i po to żeby podzielić przez to

        glassCapacity_ForProgressBar = (int) ((capacityForGlassOfWater_ForTextView / newWaterGoalInDouble) * 1000000000);
        // pojemność szklanki w int dla progress bar ( pojemność szklanki dla textView / nowy cel do wypicia)
        maksymalnaWartosc_double = newWaterGoal;
    }

    public void openDialogWater(View view) {
        DialogSetGoalWater dialogSetGoalWater = new DialogSetGoalWater();
        dialogSetGoalWater.show(getSupportFragmentManager(), "example dialog");
    }

    public void openDialogSetCup(View view) {
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

    public void resetWater(View view) {
        progressBarWater.setProgress(0);
        glassOfWaterForProgressBarCounter = 0;
        waterForTextViewValueZero = 0;
        textView_progressBarWater.setText(waterForTextViewValueZero + waterGoalForTextView);
    }

    public void button_addWater(View view) {
        glassOfWaterForProgressBarCounter += glassCapacity_ForProgressBar;
        progressBarWater.setProgress(glassOfWaterForProgressBarCounter);
        waterForTextViewValueZero += capacityForGlassOfWater_ForTextView;
        textView_progressBarWater.setText(waterForTextViewValueZero + waterGoalForTextView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        textView_progressBarWater.setText(sharedPref.getString("textViewWater", "") + "/2000 ml");
        progressBarWater.setProgress(sharedPref.getInt("glassOfWaterForProgressBar", 0));
        glassOfWaterForProgressBarCounter = sharedPref.getInt("glassOfWaterForProgressBar", 0);
        waterForTextViewValueZero = sharedPref.getInt("textViewWater2", 0);

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
        editor.putString("textViewWater", String.valueOf(waterForTextViewValueZero));
        editor.putInt("glassOfWaterForProgressBar", glassOfWaterForProgressBarCounter);
        editor.putInt("textViewWater2", waterForTextViewValueZero);
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