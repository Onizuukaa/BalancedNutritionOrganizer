package hfad.com.balancednutritionorganizer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BmiActivity extends AppCompatActivity {
    EditText editTextHeight, editTextWeight;
    TextView textViewResultBMI, textView_starvation, textView_starvationNumbers, textView_significantlyUnderweight,
            textView_significantlyUnderweightNumbers, textView_underweight, textView_underweightNumbers, textView_normal, textView_normalNumbers,
            textView_overweight, textView_overweightNumbers, textView_class1obesity, textView_class1obesityNumbers, textView_class2obesity,
            textView_class2obesityNumbers, textView_class3obesity, textView_class3obesityNumbers;
    String height, weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        setTitle("BMI CALCULATOR");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViews();
    }

    public void buttonCalculateBMI(View view) {
        height = editTextHeight.getText().toString();
        weight = editTextWeight.getText().toString();
        if (height != null && !"".equals(height) && weight != null && !"".equals(weight)) {
            float heightValue = Float.parseFloat(height) / 100;
            float weightValue = Float.parseFloat(weight);

            float bmi = weightValue / (heightValue * heightValue);

            textView_starvation.setTypeface(null, Typeface.NORMAL);
            textView_starvationNumbers.setTypeface(null, Typeface.NORMAL);
            textView_significantlyUnderweight.setTypeface(null, Typeface.NORMAL);
            textView_significantlyUnderweightNumbers.setTypeface(null, Typeface.NORMAL);
            textView_underweight.setTypeface(null, Typeface.NORMAL);
            textView_underweightNumbers.setTypeface(null, Typeface.NORMAL);
            textView_normal.setTypeface(null, Typeface.NORMAL);
            textView_normalNumbers.setTypeface(null, Typeface.NORMAL);
            textView_overweight.setTypeface(null, Typeface.NORMAL);
            textView_overweightNumbers.setTypeface(null, Typeface.NORMAL);
            textView_class1obesity.setTypeface(null, Typeface.NORMAL);
            textView_class1obesityNumbers.setTypeface(null, Typeface.NORMAL);
            textView_class2obesity.setTypeface(null, Typeface.NORMAL);
            textView_class2obesityNumbers.setTypeface(null, Typeface.NORMAL);
            textView_class3obesity.setTypeface(null, Typeface.NORMAL);
            textView_class3obesityNumbers.setTypeface(null, Typeface.NORMAL);

            displayBMI(bmi);
        }
    }

    private void displayBMI(float bmi) {
        if (bmi < 16) {
            textViewResultBMI.setText("Starvation \n" + String.format("%.1f", bmi));
            textView_starvation.setTypeface(null, Typeface.BOLD);
            textView_starvationNumbers.setTypeface(null, Typeface.BOLD);
        }
        if (bmi >= 16 && bmi <= 17) {
            textViewResultBMI.setText("Significantly underweight \n" + String.format("%.1f", bmi));
            textView_significantlyUnderweight.setTypeface(null, Typeface.BOLD);
            textView_significantlyUnderweightNumbers.setTypeface(null, Typeface.BOLD);
        }
        if (bmi > 17 && bmi <= 18.5) {
            textViewResultBMI.setText("Underweight \n" + String.format("%.1f", bmi));
            textView_underweight.setTypeface(null, Typeface.BOLD);
            textView_underweightNumbers.setTypeface(null, Typeface.BOLD);
        }
        if (bmi > 18.5 && bmi <= 25) {
            textViewResultBMI.setText("Normal \n" + String.format("%.1f", bmi));
            textView_normal.setTypeface(null, Typeface.BOLD);
            textView_normalNumbers.setTypeface(null, Typeface.BOLD);
        }
        if (bmi > 25 && bmi <= 30) {
            textViewResultBMI.setText("Overweight \n" + String.format("%.1f", bmi));
            textView_overweight.setTypeface(null, Typeface.BOLD);
            textView_overweightNumbers.setTypeface(null, Typeface.BOLD);
        }
        if (bmi > 30 && bmi <= 35) {
            textViewResultBMI.setText("Class 1 obesity \n" + String.format("%.1f", bmi));
            textView_class1obesity.setTypeface(null, Typeface.BOLD);
            textView_class1obesityNumbers.setTypeface(null, Typeface.BOLD);
        }
        if (bmi > 35 && bmi <= 40) {
            textViewResultBMI.setText("Class 2 obesity \n" + String.format("%.1f", bmi));
            textView_class2obesity.setTypeface(null, Typeface.BOLD);
            textView_class2obesityNumbers.setTypeface(null, Typeface.BOLD);
        }
        if (bmi > 40) {
            textViewResultBMI.setText("Class 3 obesity \n" + String.format("%.1f", bmi));
            textView_class3obesity.setTypeface(null, Typeface.BOLD);
            textView_class3obesityNumbers.setTypeface(null, Typeface.BOLD);
        }
    }

    private void initViews() {
        editTextHeight = (EditText) findViewById(R.id.editTextInsertHeight);
        editTextWeight = (EditText) findViewById(R.id.editTextInsertWeight);
        textViewResultBMI = (TextView) findViewById(R.id.textViewResultBMI);
        textView_starvation = (TextView) findViewById(R.id.textView_starvation);
        textView_starvationNumbers = (TextView) findViewById(R.id.textView_starvationNumbers);
        textView_significantlyUnderweight = (TextView) findViewById(R.id.textView_significantlyUnderweight);
        textView_significantlyUnderweightNumbers = (TextView) findViewById(R.id.textView_significantlyUnderweightNumbers);
        textView_underweight = (TextView) findViewById(R.id.textView_underweight);
        textView_underweightNumbers = (TextView) findViewById(R.id.textView_underweightNumbers);
        textView_normal = (TextView) findViewById(R.id.textView_normal);
        textView_normalNumbers = (TextView) findViewById(R.id.textView_normalNumbers);
        textView_overweight = (TextView) findViewById(R.id.textView_overweight);
        textView_overweightNumbers = (TextView) findViewById(R.id.textView_overweightNumbers);
        textView_class1obesity = (TextView) findViewById(R.id.textView_class1obesity);
        textView_class1obesityNumbers = (TextView) findViewById(R.id.textView_class1obesityNumbers);
        textView_class2obesity = (TextView) findViewById(R.id.textView_class2obesity);
        textView_class2obesityNumbers = (TextView) findViewById(R.id.textView_class2obesityNumbers);
        textView_class3obesity = (TextView) findViewById(R.id.textView_class3obesity);
        textView_class3obesityNumbers = (TextView) findViewById(R.id.textView_class3obesityNumbers);
    }
}