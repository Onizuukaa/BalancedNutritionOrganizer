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
    public double caloriesInOneGramProduct, carbohydratesInOneGramProduct, sugarInOneGramProduct, FatsInOneGramProduct,
            saturatedFatsInOneGramProduct, proteinInOneGramProduct, theNumberOfGramsEnteredByTheUser;
    public TextView textViewResultForCustomValueCalories, textViewResultForCustomValueCarbohydratesAndSugar,
            textViewResultForCustomValueFatsAndSaturatedFats, textViewResultForCustomValueProtein;
    public EditText editTextCustomNutritionalValues;
    String productName, imageUrl, theNumberOfGramsEnteredByTheUserString;
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
            case R.id.item1:
                Intent intent = new Intent(this, MainActivity.class);
                //Zapobiega odnowieniu MainActivity
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            case android.R.id.home:
                onBackPressed();
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void button_SendInformationAboutCurrentProductConfiguration(View view) {

        if (editTextCustomNutritionalValues.length() == 0) {
            Toast.makeText(this, R.string.No_data_to_send, Toast.LENGTH_SHORT).show();
            theNumberOfGramsEnteredByTheUser = 0;
        }

        //if (editTextCustomNutritionalValues.getText().toString().contains("0")) {
        if (editTextCustomNutritionalValues.getText().toString().equals("0")) {
            Toast.makeText(this, R.string.zero_will_not_be_sent, Toast.LENGTH_SHORT).show();
        }

        if (theNumberOfGramsEnteredByTheUser != 0) {
            String wlasnaNazwaTabeli = "testowa";
            System.out.println("wysłane dane: "+theNumberOfGramsEnteredByTheUser);
            ContentValues cv = new ContentValues();
            cv.put(ComposeMealColumns.GroceryEntry.COLUMN_NAME, productName);
            cv.put(ComposeMealColumns.GroceryEntry.COLUMN_AMOUNT, caloriesInOneGramProduct * theNumberOfGramsEnteredByTheUser);
            cv.put(ComposeMealColumns.GroceryEntry.COLUMN_CARBO, carbohydratesInOneGramProduct * theNumberOfGramsEnteredByTheUser);
            cv.put(ComposeMealColumns.GroceryEntry.COLUMN_SUGAR, sugarInOneGramProduct * theNumberOfGramsEnteredByTheUser);
            cv.put(ComposeMealColumns.GroceryEntry.COLUMN_FATS, FatsInOneGramProduct * theNumberOfGramsEnteredByTheUser);
            cv.put(ComposeMealColumns.GroceryEntry.COLUMN_SATURATEDFATS, saturatedFatsInOneGramProduct * theNumberOfGramsEnteredByTheUser);
            cv.put(ComposeMealColumns.GroceryEntry.COLUMN_PROTEIN, proteinInOneGramProduct * theNumberOfGramsEnteredByTheUser);
            cv.put(ComposeMealColumns.GroceryEntry.COLUMN_WEIGHT, theNumberOfGramsEnteredByTheUserString);
            mDatabase.insert(ComposeMealColumns.GroceryEntry.TABLE_NAME, null, cv);
            //mDatabase.insert(wlasnaNazwaTabeli, null, cv);

            Toast.makeText(this, R.string.Macronutrients_has_been_sent, Toast.LENGTH_SHORT).show();
            //Kod poniżej żeby zapobiec crashowi w momencie wciśnięcia przycisku po usunięciu wpisanej wartości ale bez wychodzenia z aktywności
            theNumberOfGramsEnteredByTheUser = 0;
        }

    }

    private void getIncomingIntent() {
//        if (getIntent().hasExtra("product_name") && getIntent().hasExtra("product_calories")
//                && getIntent().hasExtra("product_image") && getIntent().hasExtra("product_carbohydrates")
//                && getIntent().hasExtra("product_fats") && getIntent().hasExtra("product_saturatedfats")
//                && getIntent().hasExtra("product_protein")) {
        if (getIntent().hasExtra("product_name") && getIntent().hasExtra("product_calories")
                && getIntent().hasExtra("product_carbohydrates")
                && getIntent().hasExtra("product_fats") && getIntent().hasExtra("product_saturatedfats")
                && getIntent().hasExtra("product_protein")) {

            productName = getIntent().getStringExtra("product_name");
            String productCaloriesFor100Gram = getIntent().getStringExtra("product_calories");
            //imageUrl = getIntent().getStringExtra("product_image"); // Obrazek
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

        textViewResultForCustomValueCalories = (TextView) findViewById(R.id.textViewResultCustomValueCalories);
        textViewResultForCustomValueCarbohydratesAndSugar = (TextView) findViewById(R.id.textViewResultCustomValueCarbohydratesAndSugar);
        textViewResultForCustomValueFatsAndSaturatedFats = (TextView) findViewById(R.id.textViewResultCustomValueFatsAndSaturatedFats);
        textViewResultForCustomValueProtein = (TextView) findViewById(R.id.textViewResultCustomValueProtein);

        caloriesInOneGramProduct = parseDouble(productsCaloriesFor100Gram) / 100.0;
        carbohydratesInOneGramProduct = parseDouble(productCarbohydratesFor100Gram) / 100.0;
        sugarInOneGramProduct = parseDouble(productSugarFor100Gram) / 100.0;
        FatsInOneGramProduct = parseDouble(productFats) / 100.0;
        saturatedFatsInOneGramProduct = parseDouble(productSaturatedFats) / 100.0;
        proteinInOneGramProduct = parseDouble(productProtein) / 100.0;

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
        editTextCustomNutritionalValues = (EditText) findViewById(R.id.editTextCustomNutritionalValues);
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
                theNumberOfGramsEnteredByTheUserString = editTextCustomNutritionalValues.getText().toString();

                if (theNumberOfGramsEnteredByTheUserString.equals("")) {
                    textViewResultForCustomValueCalories.setText("0");
                    textViewResultForCustomValueCarbohydratesAndSugar.setText("0");
                    textViewResultForCustomValueFatsAndSaturatedFats.setText("0");
                    textViewResultForCustomValueProtein.setText("0");
                } else {
                    theNumberOfGramsEnteredByTheUser = parseDouble(theNumberOfGramsEnteredByTheUserString);
                    textViewResultForCustomValueCalories.setText(format.format(caloriesInOneGramProduct * theNumberOfGramsEnteredByTheUser));
                    textViewResultForCustomValueCarbohydratesAndSugar.setText(format.format(carbohydratesInOneGramProduct * theNumberOfGramsEnteredByTheUser) + " (" + format.format(sugarInOneGramProduct * theNumberOfGramsEnteredByTheUser) + ")");
                    textViewResultForCustomValueFatsAndSaturatedFats.setText(format.format(FatsInOneGramProduct * theNumberOfGramsEnteredByTheUser) + " (" + format.format(saturatedFatsInOneGramProduct * theNumberOfGramsEnteredByTheUser) + ")");
                    textViewResultForCustomValueProtein.setText(format.format(proteinInOneGramProduct * theNumberOfGramsEnteredByTheUser));
                }
            }
        });
    }

}