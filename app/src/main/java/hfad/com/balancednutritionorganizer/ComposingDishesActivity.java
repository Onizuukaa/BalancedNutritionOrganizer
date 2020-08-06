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
    Button button_removeItem;
    RecyclerViewAdapter2 adapter;

    private ArrayList<String> productName = new ArrayList<>();
    private ArrayList<String> productCaloriesArrayList = new ArrayList<>();
    private ArrayList<String> productGramArrayList = new ArrayList<>();
    private ArrayList<String> productCarbohydratesArrayList = new ArrayList<>();
    private ArrayList<String> productSugarArrayList = new ArrayList<>();
    private ArrayList<String> productFatsArrayList = new ArrayList<>();
    private ArrayList<String> productSaturatedFatsArrayList = new ArrayList<>();
    private ArrayList<String> productProteinArrayList = new ArrayList<>();

    private ArrayList<String> mProductImage = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composing_dishes);
        getIncomingIntent();
        format = new DecimalFormat("#.#");
        format.setDecimalSeparatorAlwaysShown(false);

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

        button_removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(editText_removeItem.getText().toString());
                buttonRemoveItem(position);
            }
        });

        initRecyclerView();
        sumAndViewMacros();
    }

    private void buttonRemoveItem(int position) {
        productName.remove(position);
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

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.composingDishesRecyclerView);
        adapter = new RecyclerViewAdapter2(this, mProductImage, productName, productCaloriesArrayList, productCarbohydratesArrayList,
                productSugarArrayList, productFatsArrayList, productSaturatedFatsArrayList, productProteinArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (productName.isEmpty()) {
            textViewNoData.setVisibility(View.VISIBLE);
        }
    }

    private void getIncomingIntent() {
        productName = getIntent().getStringArrayListExtra("product_name");
        imageUrl = getIntent().getStringExtra("product_image");
        productCaloriesArrayList = getIntent().getStringArrayListExtra("product_calories");
        productGramArrayList = getIntent().getStringArrayListExtra("product_gram");
        productCarbohydratesArrayList = getIntent().getStringArrayListExtra("product_carbohydrates");
        productSugarArrayList = getIntent().getStringArrayListExtra("product_sugar");
        productFatsArrayList = getIntent().getStringArrayListExtra("product_fats");
        productSaturatedFatsArrayList = getIntent().getStringArrayListExtra("product_saturatedFats");
        productProteinArrayList = getIntent().getStringArrayListExtra("product_protein");
    }
    private void sumAndViewMacros(){
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
}