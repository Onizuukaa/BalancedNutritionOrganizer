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
    int glassOfWaterForProgressBarCounter = 0, textViewWaterZero = 0, capacityForGlassOfWater_ForTextView = 250;
    int glassCapacity_ForProgressBar = 125000000;
    Bundle extras;
    int test;
    //public static Activity fa;
    String waterGoal = "/2000 ml";
    Button buttonAddWater;
    //BigInteger test2 =  new BigInteger("10000000000000000");
    double newWaterGoalInDouble;

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
        //System.out.println("MILILITRY YEA: " + username);
        capacityForGlassOfWater_ForTextView = parseInt(username);
        buttonAddWater.setText(getString(R.string.ADD) + " " + capacityForGlassOfWater_ForTextView + " ML " + getString(R.string.Water));
        int wynik = (int)(capacityForGlassOfWater_ForTextView/glassCapacity_ForProgressBar)*1000000000;
        glassCapacity_ForProgressBar *= wynik;
        System.out.println("SZKLANKA: " + wynik);
    }

    @Override
    public void applyWeight(String weight) {
        double waterNeeds = (parseDouble(weight) * 0.030) * 1000;
        int newWaterGoal = (int) Math.round(waterNeeds);
        waterGoal = "/" + newWaterGoal + " ml";
        textView_progressBarWater.setText(textViewWaterZero + waterGoal + "");
        newWaterGoalInDouble = newWaterGoal;
        glassCapacity_ForProgressBar = (int) ((capacityForGlassOfWater_ForTextView / newWaterGoalInDouble) * 1000000000);

        //System.out.println("TEST"+glassCapacity );
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
        textViewWaterZero = 0;
        textView_progressBarWater.setText(textViewWaterZero + waterGoal);
    }

    public void button_addWater(View view) {
        glassOfWaterForProgressBarCounter += glassCapacity_ForProgressBar;
        //System.out.println("CO JEST: " + glassOfWaterForProgressBar);
        progressBarWater.setProgress(glassOfWaterForProgressBarCounter);
        textViewWaterZero += capacityForGlassOfWater_ForTextView;
        textView_progressBarWater.setText(textViewWaterZero + waterGoal);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        textView_progressBarWater.setText(sharedPref.getString("textViewWater", "") + "/2000");
        progressBarWater.setProgress(sharedPref.getInt("glassOfWaterForProgressBar", 0));
        glassOfWaterForProgressBarCounter = sharedPref.getInt("glassOfWaterForProgressBar", 0);
        textViewWaterZero = sharedPref.getInt("textViewWater2", 0);

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
        editor.putString("textViewWater", String.valueOf(textViewWaterZero));
        editor.putInt("glassOfWaterForProgressBar", glassOfWaterForProgressBarCounter);
        editor.putInt("textViewWater2", textViewWaterZero);
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