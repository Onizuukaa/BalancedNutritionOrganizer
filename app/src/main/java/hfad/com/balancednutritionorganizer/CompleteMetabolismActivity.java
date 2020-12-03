package hfad.com.balancednutritionorganizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class CompleteMetabolismActivity extends AppCompatActivity {

    RadioGroup radioGroupGender;
    RadioButton radioButtonGender;
    EditText editTextAge, editTextHeight, editTextWeight;
    Spinner spinnerPhysicalActivity;
    TextView textViewTotalMetabolismProtein_Gram,
            textViewTotalMetabolismFats_Gram, textViewTotalMetabolismCarbo_Gram, textViewTotalMetabolismProtein_Kcal, textViewTotalMetabolismFats_Kcal,
            textViewTotalMetabolismCarbo_Kcal, textViewTotalMetabolismProtein_Percent, textViewTotalMetabolismFats_Percent, textViewTotalMetabolismCarbo_Percent, textViewTotalMetabolismTotal_Kcal;
    int selectedGender;
    double result, resultFull, selectedPhysicalActivity, proteinGram, proteinCalories, fatsGram, fatsCalories, carboGram,
            carboCalories, percentProtein, percentFats, percentCarbo;
    DecimalFormat format;
    DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
    Bundle bundleForWater;

    int waterNeeded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_metabolism);
        setTitle(R.string.Metabolism_Calculator);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();

        symbols.setDecimalSeparator('.');
        format = new DecimalFormat("#.#");
        format.setDecimalFormatSymbols(symbols);
        format.setMaximumFractionDigits(1);
        format.setDecimalSeparatorAlwaysShown(false);
    }

    public void buttonCalculateTotalMetabolism(View view) {
        int radioId = radioGroupGender.getCheckedRadioButtonId();
        radioButtonGender = findViewById(radioId);
        spinnerPhysicalActivity = findViewById(R.id.spinnerPhysicalActivity);
        if (String.valueOf(spinnerPhysicalActivity.getSelectedItem()).equals(getString(R.string.Inactivity)))
            selectedPhysicalActivity = 1.2;
        if (String.valueOf(spinnerPhysicalActivity.getSelectedItem()).equals(getString(R.string.Low_activity)))
            selectedPhysicalActivity = 1.35;
        if (String.valueOf(spinnerPhysicalActivity.getSelectedItem()).equals(getString(R.string.Average_activity)))
            selectedPhysicalActivity = 1.55;
        if (String.valueOf(spinnerPhysicalActivity.getSelectedItem()).equals(getString(R.string.High_activity)))
            selectedPhysicalActivity = 1.75;
        if (String.valueOf(spinnerPhysicalActivity.getSelectedItem()).equals(getString(R.string.Very_high_activity)))
            selectedPhysicalActivity = 2.05;

        if (radioButtonGender.getText().equals(R.string.Male)) {
            selectedGender = 5;
        } else selectedGender = -161;

        if (editTextWeight.getText().toString().equals("") || editTextHeight.getText().toString().equals(".")
        || editTextAge.getText().toString().equals(".")){

        }else{
            result = (9.99 * parseDouble(editTextWeight.getText().toString()))
                    + (6.25 * parseDouble(editTextHeight.getText().toString()))
                    - (4.92 * parseDouble(editTextAge.getText().toString()))
                    + selectedGender;

            resultFull = result * selectedPhysicalActivity;

            //waterNeededDouble = resultFull;
            waterNeeded = (int)resultFull;
            System.out.println("ILE WYSZŁO?: " + waterNeeded);
            bundleForWater = new Bundle();
            bundleForWater.putInt("key", waterNeeded);
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("dawid", waterNeeded);
            //MainActivity.fa.finish();

            proteinGram = selectedPhysicalActivity * parseDouble(editTextWeight.getText().toString());
            proteinCalories = proteinGram * 4;

            fatsCalories = resultFull * 0.25;
            fatsGram = fatsCalories / 9;

            carboCalories = (resultFull - fatsCalories) - proteinCalories;
            //System.out.println("carbo: " + carboCalories);
            carboGram = carboCalories / 4;

            percentProtein = (proteinCalories / resultFull) * 100;
            percentFats = (fatsCalories / resultFull) * 100;
            percentCarbo = (carboCalories / resultFull) * 100;

            //textViewResultCompleteMetabolism.setText("Dzienne zapotrzebowanie na kalorie: " + format.format(resultFull));
            // textViewResultCompleteMetabolismMacros.setText("Dzienne zapotrzebowanie na makroskładniki.");
//                "\n" +
//                "białko: " + format.format(proteinGram) + "g " + format.format(proteinCalories) + " kcal " + format.format(percentProtein) + "%" + "\n" +
//                "tłuszcze: " + format.format(fatsGram) + "g " + format.format(fatsCalories) + " kcal " + format.format(percentFats) + "%" + "\n" +
//                "węglowodany: " + format.format(carboGram) + "g " + format.format(carboCalories) + " kcal " + format.format(percentCarbo) + "%");

            textViewTotalMetabolismProtein_Gram.setText(format.format(proteinGram) + "g");
            textViewTotalMetabolismFats_Gram.setText(format.format(fatsGram) + "g");
            textViewTotalMetabolismCarbo_Gram.setText(format.format(carboGram) + "g");
            textViewTotalMetabolismProtein_Kcal.setText(format.format(proteinCalories) + "kcal");
            textViewTotalMetabolismFats_Kcal.setText(format.format(fatsCalories) + "kcal");
            textViewTotalMetabolismCarbo_Kcal.setText(format.format(carboCalories) + "kcal");
            textViewTotalMetabolismProtein_Percent.setText(format.format(percentProtein) + "%");
            textViewTotalMetabolismFats_Percent.setText(format.format(percentFats) + "%");
            textViewTotalMetabolismCarbo_Percent.setText(format.format(percentCarbo) + "%");
            textViewTotalMetabolismTotal_Kcal.setText(format.format(resultFull) + " kcal");
        }
    }
    private void initViews() {
        editTextAge = findViewById(R.id.editTextAge);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);
//        textViewResultCompleteMetabolism = findViewById(R.id.textViewResultCompleteMetabolism);
       // textViewResultCompleteMetabolismMacros = findViewById(R.id.textViewResultCompleteMetabolismMacros);
        radioGroupGender = findViewById(R.id.radioGroupGender);

        textViewTotalMetabolismProtein_Gram = findViewById(R.id.textViewTotalMetabolismProtein_Gram);
        textViewTotalMetabolismFats_Gram = findViewById(R.id.textViewTotalMetabolismFats_Gram);
        textViewTotalMetabolismCarbo_Gram = findViewById(R.id.textViewTotalMetabolismCarbo_Gram);
        textViewTotalMetabolismProtein_Kcal = findViewById(R.id.textViewTotalMetabolismProtein_Kcal);
        textViewTotalMetabolismFats_Kcal = findViewById(R.id.textViewTotalMetabolismFats_Kcal);
        textViewTotalMetabolismCarbo_Kcal = findViewById(R.id.textViewTotalMetabolismCarbo_Kcal);
        textViewTotalMetabolismProtein_Percent = findViewById(R.id.textViewTotalMetabolismProtein_Percent);
        textViewTotalMetabolismFats_Percent = findViewById(R.id.textViewTotalMetabolismFats_Percent);
        textViewTotalMetabolismCarbo_Percent = findViewById(R.id.textViewTotalMetabolismCarbo_Percent);
        textViewTotalMetabolismTotal_Kcal = findViewById(R.id.textViewTotalMetabolismTotal_Kcal);
    }
}