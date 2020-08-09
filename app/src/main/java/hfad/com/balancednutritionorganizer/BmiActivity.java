package hfad.com.balancednutritionorganizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BmiActivity extends AppCompatActivity {
    EditText editTextHeight, editTextWeight;
    TextView textViewResultBMI;
    String height, weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        initViews();
    }

    public void buttonCalculateBMI(View view){
        height = editTextHeight.getText().toString();
        weight = editTextWeight.getText().toString();
        if (height != null && !"".equals(height) && weight != null && !"".equals(weight)) {
            float heightValue = Float.parseFloat(height) / 100;
            float weightValue = Float.parseFloat(weight);

            float bmi = weightValue / (heightValue * heightValue);

            displayBMI(bmi);
        }
    }

    private void displayBMI(float bmi) {
        if (bmi < 16) {
            textViewResultBMI.setText("Wygłodzenie \n" + String.format("%.1f", bmi));
        }
        if (bmi >= 16 && bmi <= 17) {
            textViewResultBMI.setText("Znaczna niedowaga \n" + String.format("%.1f", bmi));
        }
        if (bmi > 17 && bmi <= 18.5) {
            textViewResultBMI.setText("Niedowaga \n" + String.format("%.1f", bmi));
        }
        if (bmi > 18.5 && bmi <= 25) {
            textViewResultBMI.setText("W normie \n" + String.format("%.1f", bmi));
        }
        if (bmi > 25 && bmi <= 30) {
            textViewResultBMI.setText("Nadwaga \n" + String.format("%.1f", bmi));
        }
        if (bmi > 30 && bmi <= 35) {
            textViewResultBMI.setText("Otyłość klasy 1 \n" + String.format("%.1f", bmi));
        }
        if (bmi > 35 && bmi <= 40) {
            textViewResultBMI.setText("Otyłość klasy 2 \n" + String.format("%.1f", bmi));
        }
        if (bmi > 40) {
            textViewResultBMI.setText("Otyłość klasy 3 \n" + String.format("%.1f", bmi));
        }
    }

    private void initViews(){
        editTextHeight = (EditText) findViewById(R.id.editTextInsertHeight);
        editTextWeight = (EditText) findViewById(R.id.editTextInsertWeight);
        textViewResultBMI = (TextView) findViewById(R.id.textViewResultBMI);
    }
}