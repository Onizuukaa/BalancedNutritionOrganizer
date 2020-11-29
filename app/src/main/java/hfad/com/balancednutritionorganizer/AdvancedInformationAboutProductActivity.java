package hfad.com.balancednutritionorganizer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import hfad.com.balancednutritionorganizer.database_things.ComposeMealColumns;
import hfad.com.balancednutritionorganizer.database_things.ComposeMealDBHelper;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class AdvancedInformationAboutProductActivity extends AppCompatActivity {

    private SQLiteDatabase mDatabase;
    public double caloriesOneGramFood, carboOneGramFood, sugarOneGramFood, FatsOneGramFood,
            saturFatsOneGramFood, proteinOneGramFood, gramFromUser;
    public TextView resultCalories, resultCarboAndSugar,
            resultFatsAndSaturFats, resultProtein;
    public EditText editTextCustomNutritionalValues;
    String productName, imageUrl, gramFromUserString;
    DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
    DecimalFormat format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_information_about_product);
        setTitle(R.string.Advanced_information);

        ComposeMealDBHelper dbHelper = new ComposeMealDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        symbols.setDecimalSeparator('.');
        format = new DecimalFormat("#.#");
        format.setDecimalFormatSymbols(symbols);
        format.setMaximumFractionDigits(1);
        format.setDecimalSeparatorAlwaysShown(false);

        getIncomingIntent();
        initViews();
    }

    public void button_openComposhingDishesActivity(View view) {
        Intent intent = new Intent(this, ComposeMealActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuHome:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            case android.R.id.home:
                onBackPressed();
                finish();
            default:
               break;// return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void button_SendInformationAboutCurrentProductConfiguration(View view) {

        if (editTextCustomNutritionalValues.length() == 0) {
            Toast.makeText(this, R.string.No_data_to_send, Toast.LENGTH_SHORT).show();
            gramFromUser = 0;
        }

        if (editTextCustomNutritionalValues.getText().toString().equals("0")) {
            Toast.makeText(this, R.string.zero_will_not_be_sent, Toast.LENGTH_SHORT).show();
        }

        if (gramFromUser != 0) {
            System.out.println("wysłane dane: "+ gramFromUser);
            ContentValues cv = new ContentValues();
            cv.put(ComposeMealColumns.ComposeMealColumnsEntry.COLUMN_mealNAME, productName);
            cv.put(ComposeMealColumns.ComposeMealColumnsEntry.COLUMN_CALORIES, caloriesOneGramFood * gramFromUser);
            cv.put(ComposeMealColumns.ComposeMealColumnsEntry.COLUMN_CARBO, carboOneGramFood * gramFromUser);
            cv.put(ComposeMealColumns.ComposeMealColumnsEntry.COLUMN_SUGAR, sugarOneGramFood * gramFromUser);
            cv.put(ComposeMealColumns.ComposeMealColumnsEntry.COLUMN_FATS, FatsOneGramFood * gramFromUser);
            cv.put(ComposeMealColumns.ComposeMealColumnsEntry.COLUMN_saturatedFATS, saturFatsOneGramFood * gramFromUser);
            cv.put(ComposeMealColumns.ComposeMealColumnsEntry.COLUMN_PROTEIN, proteinOneGramFood * gramFromUser);
            cv.put(ComposeMealColumns.ComposeMealColumnsEntry.COLUMN_WEIGHT, gramFromUserString);
            mDatabase.insert(ComposeMealColumns.ComposeMealColumnsEntry.TABLE_NAME, null, cv);

            Toast.makeText(this, R.string.Macronutrients_has_been_sent, Toast.LENGTH_SHORT).show();
            //Kod poniżej żeby zapobiec crashowi w momencie wciśnięcia przycisku po usunięciu wpisanej wartości ale bez wychodzenia z aktywności
            gramFromUser = 0;
        }

    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("product_name") && getIntent().hasExtra("product_calories")
                && getIntent().hasExtra("product_carbohydrates")
                && getIntent().hasExtra("product_fats") && getIntent().hasExtra("product_saturatedfats")
                && getIntent().hasExtra("product_protein")) {

            productName = getIntent().getStringExtra("product_name");
            String productCaloriesFor100Gram = getIntent().getStringExtra("product_calories");
            String productCarbohydratesFor100Gram = getIntent().getStringExtra("product_carbohydrates");
            String productSugarFor100Gram = getIntent().getStringExtra("product_sugar");
            String productFatsFor100Gram = getIntent().getStringExtra("product_fats");
            String productSaturatedFatsFor100Gram = getIntent().getStringExtra("product_saturatedfats");
            String productProteinFor100Gram = getIntent().getStringExtra("product_protein");

            setBasicValues(imageUrl, productName, productCaloriesFor100Gram, productCarbohydratesFor100Gram, productSugarFor100Gram,
                    productFatsFor100Gram, productSaturatedFatsFor100Gram, productProteinFor100Gram);
        }
    }

    private void setBasicValues(String imageUrl, String productNames, String productsCaloriesFor100Gram, String productCarbohydratesFor100Gram,
                                String productSugarFor100Gram, String productFats, String productSaturatedFats, String productProtein) {

        TextView productName = findViewById(R.id.textViewProductName);
        TextView productCaloriesFor100Gram = findViewById(R.id.textViewCaloriesFor100Gram);
        TextView productCaloriesFor200Gram = findViewById(R.id.textViewCaloriesFor200Gram);
        TextView productCaloriesCarbohydratesAndSugarFor100Gram = findViewById(R.id.textViewCarbohydratesFor100Gram);
        TextView productCaloriesCarbohydratesAndSugarFor200Gram = findViewById(R.id.textViewCarbohydratesFor200Gram);
        TextView productFatsFor100Gram = findViewById(R.id.textViewFatsFor100Gram);
        TextView productFatsFor200Gram = findViewById(R.id.textViewFatsFor200Gram);
        TextView productProteinFor100Gram = findViewById(R.id.textViewProteinFor100Gram);
        TextView productProteinFor200Gram = findViewById(R.id.textViewProteinFor200Gram);

        resultCalories = (TextView) findViewById(R.id.textViewResultCustomValueCalories);
        resultCarboAndSugar = (TextView) findViewById(R.id.textViewResultCustomValueCarbohydratesAndSugar);
        resultFatsAndSaturFats = (TextView) findViewById(R.id.textViewResultCustomValueFatsAndSaturatedFats);
        resultProtein = (TextView) findViewById(R.id.textViewResultCustomValueProtein);

        caloriesOneGramFood = parseDouble(productsCaloriesFor100Gram) / 100.0;
        carboOneGramFood = parseDouble(productCarbohydratesFor100Gram) / 100.0;
        sugarOneGramFood = parseDouble(productSugarFor100Gram) / 100.0;
        FatsOneGramFood = parseDouble(productFats) / 100.0;
        saturFatsOneGramFood = parseDouble(productSaturatedFats) / 100.0;
        proteinOneGramFood = parseDouble(productProtein) / 100.0;

        productName.setText(productNames);

        productCaloriesFor100Gram.setText(format.format(parseDouble(productsCaloriesFor100Gram)));
        productCaloriesFor200Gram.setText(format.format(parseDouble(productsCaloriesFor100Gram) * 2));

        productCaloriesCarbohydratesAndSugarFor100Gram.setText(format.format(parseDouble(productCarbohydratesFor100Gram)) + " (" + format.format(parseDouble(productSugarFor100Gram)) + ")");
        productCaloriesCarbohydratesAndSugarFor200Gram.setText(format.format(parseDouble(productCarbohydratesFor100Gram) * 2) + " (" + format.format(parseDouble(productSugarFor100Gram) * 2) + ")");

        productFatsFor100Gram.setText(format.format(parseDouble(productFats)) + " (" + format.format(parseDouble(productSaturatedFats)) + ")");
        productFatsFor200Gram.setText(format.format(parseDouble(productFats) * 2) + " (" + format.format(parseDouble(productSaturatedFats) * 2) + ")");

        productProteinFor100Gram.setText(format.format(parseDouble(productProtein)));
        productProteinFor200Gram.setText(format.format(parseDouble(productProtein) * 2));
    }

    private void initViews() {
        editTextCustomNutritionalValues = findViewById(R.id.editTextCustomNutritionalValues);
        editTextCustomNutritionalValues.requestFocus();
        editTextCustomNutritionalValues.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                gramFromUserString = editTextCustomNutritionalValues.getText().toString();

                if (gramFromUserString.equals(".")){
                    gramFromUserString = "";
                    editTextCustomNutritionalValues.setText("");
                }
                if (gramFromUserString.equals("")) {
                    resultCalories.setText("0");
                    resultCarboAndSugar.setText("0");
                    resultFatsAndSaturFats.setText("0");
                    resultProtein.setText("0");
                } else {
                    gramFromUser = parseDouble(gramFromUserString);
                    resultCalories.setText(format.format(caloriesOneGramFood * gramFromUser));
                    resultCarboAndSugar.setText(format.format(carboOneGramFood * gramFromUser)
                            + " (" + format.format(sugarOneGramFood * gramFromUser) + ")");
                    resultFatsAndSaturFats.setText(format.format(FatsOneGramFood * gramFromUser)
                            + " (" + format.format(saturFatsOneGramFood * gramFromUser) + ")");
                    resultProtein.setText(format.format(proteinOneGramFood * gramFromUser));
                }
            }
        });
    }

}