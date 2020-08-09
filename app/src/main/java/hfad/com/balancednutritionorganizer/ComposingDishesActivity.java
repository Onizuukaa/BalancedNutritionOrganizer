package hfad.com.balancednutritionorganizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static java.lang.Double.parseDouble;

public class ComposingDishesActivity extends AppCompatActivity {

    String imageUrl;
    TextView textViewComposhingDishesKcal, textViewComposhingDishesCarbohydrates, textViewComposhingDishesGram,
            textViewComposhingDishesSugar, textViewComposhingDishesFats, textViewComposhingDishesSaturatedFats,
            textViewComposhingDishesProtein, textViewNoData;
    double caloriesSum, carbohydratesSum, gramSum, sugarSum, fatsSum, saturatedFatsSum, proteinSum;
    DecimalFormat format;
    EditText editText_removeItem;
    Button button_removeItem, button_removeAllItems;
    RecyclerViewAdapterComposhingDishes adapter;

    private ArrayList<String> productNameArrayList = new ArrayList<>();
    private ArrayList<String> productCaloriesArrayList = new ArrayList<>();
    private ArrayList<String> productGramArrayList = new ArrayList<>();
    private ArrayList<String> productCarbohydratesArrayList = new ArrayList<>();
    private ArrayList<String> productSugarArrayList = new ArrayList<>();
    private ArrayList<String> productFatsArrayList = new ArrayList<>();
    private ArrayList<String> productSaturatedFatsArrayList = new ArrayList<>();
    private ArrayList<String> productProteinArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composing_dishes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getIncomingIntent();
        format = new DecimalFormat("#.#");
        format.setDecimalSeparatorAlwaysShown(false);

        initViews();
        initRecyclerView();
        sumAndViewMacros();
    }

    private void buttonRemoveItem(int position) {
        if (position >= productNameArrayList.size()) {
            Toast.makeText(this, "No item with this index", Toast.LENGTH_SHORT).show();
        } else {
            productNameArrayList.remove(position);
            productCaloriesArrayList.remove(position);
            productCarbohydratesArrayList.remove(position);
            productSugarArrayList.remove(position);
            productFatsArrayList.remove(position);
            productSaturatedFatsArrayList.remove(position);
            productProteinArrayList.remove(position);
            adapter.notifyItemRemoved(position);

            caloriesSum = 0.0;
            carbohydratesSum = 0.0;
            gramSum = 0.0;
            sugarSum = 0.0;
            fatsSum = 0.0;
            saturatedFatsSum = 0.0;
            proteinSum = 0.0;

            sumAndViewMacros();
        }
        if (productNameArrayList.size() == 0){
            textViewNoData.setVisibility(View.VISIBLE);
        }
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.composingDishesRecyclerView);
        adapter = new RecyclerViewAdapterComposhingDishes(productNameArrayList, productCaloriesArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (productNameArrayList.isEmpty()) {
            textViewNoData.setVisibility(View.VISIBLE);
        }
    }

    private void getIncomingIntent() {
        productNameArrayList = getIntent().getStringArrayListExtra("product_name");
        imageUrl = getIntent().getStringExtra("product_image");
        productCaloriesArrayList = getIntent().getStringArrayListExtra("product_calories");
        productGramArrayList = getIntent().getStringArrayListExtra("product_gram");
        productCarbohydratesArrayList = getIntent().getStringArrayListExtra("product_carbohydrates");
        productSugarArrayList = getIntent().getStringArrayListExtra("product_sugar");
        productFatsArrayList = getIntent().getStringArrayListExtra("product_fats");
        productSaturatedFatsArrayList = getIntent().getStringArrayListExtra("product_saturatedFats");
        productProteinArrayList = getIntent().getStringArrayListExtra("product_protein");
    }

    private void sumAndViewMacros() {
        for (int i = 0; i < productCaloriesArrayList.size(); i++) {
            caloriesSum += parseDouble(productCaloriesArrayList.get(i));
            carbohydratesSum += parseDouble(productCarbohydratesArrayList.get(i));
            gramSum += parseDouble(productGramArrayList.get(i));
            sugarSum += parseDouble(productSugarArrayList.get(i));
            fatsSum += parseDouble(productFatsArrayList.get(i));
            saturatedFatsSum += parseDouble(productSaturatedFatsArrayList.get(i));
            proteinSum += parseDouble(productProteinArrayList.get(i));
        }

        textViewComposhingDishesKcal.setText(format.format(caloriesSum) + "\nKCAL");
        textViewComposhingDishesCarbohydrates.setText(format.format(carbohydratesSum) + "g\ncarbo");
        textViewComposhingDishesGram.setText(format.format(gramSum) + "g\nweight");
        textViewComposhingDishesSugar.setText(format.format(sugarSum) + "g\nsugar");
        textViewComposhingDishesFats.setText(format.format(fatsSum) + "g\nfats");
        textViewComposhingDishesSaturatedFats.setText(format.format(saturatedFatsSum) + "g\nsaturated fats");
        textViewComposhingDishesProtein.setText(format.format(proteinSum) + "g\nprotein");
    }

    public void buttonClearAllArrayList(View view) {

        productNameArrayList.removeAll(productNameArrayList);
        productCaloriesArrayList.removeAll(productCaloriesArrayList);
        productCarbohydratesArrayList.removeAll(productCarbohydratesArrayList);
        productSugarArrayList.removeAll(productSugarArrayList);
        productFatsArrayList.removeAll(productFatsArrayList);
        productSaturatedFatsArrayList.removeAll(productSaturatedFatsArrayList);
        productProteinArrayList.removeAll(productProteinArrayList);

//        productNameArrayList.clear();
//        productCaloriesArrayList.clear();
//        productCarbohydratesArrayList.clear();
//        productSugarArrayList.clear();
//        productFatsArrayList.clear();
//        productSaturatedFatsArrayList.clear();
//        productProteinArrayList.clear();

        adapter.notifyItemRangeRemoved(0,productNameArrayList.size());
        

        caloriesSum = 0.0;
        carbohydratesSum = 0.0;
        gramSum = 0.0;
        sugarSum = 0.0;
        fatsSum = 0.0;
        saturatedFatsSum = 0.0;
        proteinSum = 0.0;

        sumAndViewMacros();
        textViewNoData.setVisibility(View.VISIBLE);
    }

    public void buttonSendDish(View view){
//        Intent intent = new Intent("INTENT_fromComposhingDishesActivity").putExtra("product_name", productName);
//        LocalBroadcastManager.getInstance(ComposingDishesActivity.this).sendBroadcast(intent);
//        intent.putExtra("product_image", imageUrl);
//
//        //To niżej crashowało, dziwne :O // przez to crashuje :O
//        //intent.putExtra("product_calories", String.format("%.1f", caloriesInOneGramProduct * theNumberOfGramsEnteredByTheUser)+"");
//        intent.putExtra("product_calories", caloriesInOneGramProduct * theNumberOfGramsEnteredByTheUser + "");
//
//        intent.putExtra("product_carbohydrates", carbohydratesInOneGramProduct * theNumberOfGramsEnteredByTheUser + "");
//        intent.putExtra("product_sugar", sugarInOneGramProduct * theNumberOfGramsEnteredByTheUser + "");
//        intent.putExtra("product_fats", FatsInOneGramProduct * theNumberOfGramsEnteredByTheUser + "");
//        intent.putExtra("product_saturatedFats", saturatedFatsInOneGramProduct * theNumberOfGramsEnteredByTheUser + "");
//        intent.putExtra("product_protein", proteinInOneGramProduct * theNumberOfGramsEnteredByTheUser + "");
//        intent.putExtra("product_gram", theNumberOfGramsEnteredByTheUserString + "");
//        Toast.makeText(this, "The data has been sent", Toast.LENGTH_SHORT).show();
//        //Kod poniżej żeby zapobiec crashowi w momencie wciśnięcia przycisku po usunięciu wpisanej wartości ale bez wychodzenia z aktywności
//        theNumberOfGramsEnteredByTheUser = 0;
    }

    private void initViews(){
        textViewComposhingDishesKcal = (TextView) findViewById(R.id.textViewComposhingDishesKcal);
        textViewComposhingDishesCarbohydrates = (TextView) findViewById(R.id.textViewComposhingDishesCarbohydrates);
        textViewComposhingDishesGram = (TextView) findViewById(R.id.textViewComposhingDishesGram);
        textViewComposhingDishesSugar = (TextView) findViewById(R.id.textViewComposhingDishesSugar);
        textViewComposhingDishesFats = (TextView) findViewById(R.id.textViewComposhingDishesFats);
        textViewComposhingDishesSaturatedFats = (TextView) findViewById(R.id.textViewComposhingDishesSaturatedFats);
        textViewComposhingDishesProtein = (TextView) findViewById(R.id.textViewComposhingDishesProtein);
        textViewNoData = (TextView) findViewById(R.id.textViewNoData);
        textViewNoData.setVisibility(View.INVISIBLE);
        editText_removeItem = (EditText) findViewById(R.id.editText_removeItem);
        button_removeItem = (Button) findViewById(R.id.button_removeItem);
        button_removeAllItems = (Button) findViewById(R.id.buttonResetIngredients);

        button_removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(editText_removeItem.getText().toString());
                buttonRemoveItem(position);
            }
        });
    }
}