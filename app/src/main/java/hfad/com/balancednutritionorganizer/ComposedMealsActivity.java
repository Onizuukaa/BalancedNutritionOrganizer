package hfad.com.balancednutritionorganizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

import hfad.com.balancednutritionorganizer.adapters.RecyclerViewComposedMealsAdapter;
import hfad.com.balancednutritionorganizer.database_things.ComposedDailyMealsColumns;
import hfad.com.balancednutritionorganizer.database_things.ComposedDailyMealsDBHelper;
import hfad.com.balancednutritionorganizer.database_things.ComposedMealsColumns;
import hfad.com.balancednutritionorganizer.database_things.ComposedMealsDBHelper;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class ComposedMealsActivity extends AppCompatActivity implements BottomSheetDialog.BottomSheetListener {
    RecyclerView recyclerView;
    private SQLiteDatabase mDatabaseComposedMeals, mDatabaseComposedDailyMeals;
    private RecyclerViewComposedMealsAdapter adapter;
    Cursor cursor;
    double kcal_Sum = 0.0, weight_Sum = 0.0, carbohydrates_Sum = 0.0, sugar_Sum = 0.0,
            fats_Sum = 0.0, saturatedFats_Sum = 0.0, protein_Sum = 0.0;
    Button button_addMealsToDailyMenu;
    EditText editText_removeMeal, editText_dailyMealName;
    TextView textViewNoDataComposed;
    String dailyMealName;

    //DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
    DecimalFormat format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composed_meals);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.Composed_Meals);

        //symbols.setDecimalSeparator('.');
        format = new DecimalFormat("#.#");
//        format.setDecimalFormatSymbols(symbols);
//        format.setMaximumFractionDigits(1);
        format.setDecimalSeparatorAlwaysShown(false);

        ComposedMealsDBHelper dbHelperComposedMeals = new ComposedMealsDBHelper(this);
        ComposedDailyMealsDBHelper dbHelperComposedDailyMeals = new ComposedDailyMealsDBHelper(this);
        mDatabaseComposedMeals = dbHelperComposedMeals.getWritableDatabase();
        mDatabaseComposedDailyMeals = dbHelperComposedDailyMeals.getWritableDatabase();
        initRecyclerView();
        editText_dailyMealName = findViewById(R.id.editText_dailyMealName);
        editText_removeMeal = findViewById(R.id.editText_removeItem_ComposedMeals);
        textViewNoDataComposed = findViewById(R.id.textViewNoDataComposed);

        cursor = getAllItems();
        showOrHideNoDataTextView();
        button_addMealsToDailyMenu = findViewById(R.id.button_addMealsToDailyMenu);
        button_addMealsToDailyMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ReturnItemComposedMeals> selectedMeals = adapter.getSelectedMeals();
                ContentValues cv = new ContentValues();
                dailyMealName = editText_dailyMealName.getText().toString();

                if (dailyMealName.length()==0){
                    Toast.makeText(ComposedMealsActivity.this, R.string.Name_was_not_provided, Toast.LENGTH_SHORT).show();
                } else{
                    if (selectedMeals.size() != 5) {
                        Toast.makeText(ComposedMealsActivity.this, R.string.Please_select_5_meals, Toast.LENGTH_SHORT).show();
                    } else {
//                StringBuilder composedMealsNames = new StringBuilder();
//                StringBuilder composedMealsCalories = new StringBuilder();

                        for (int i = 0; i < selectedMeals.size(); i++) {
                            // if (i == 0) {

                            //composedMealsNames.append(selectedMeals.get(i).getProductName());
                            //composedMealsCalories.append(selectedMeals.get(i).getProductWeight());

                            //kcal_Sum += parseDouble(selectedMeals.get(i).getProductCalories());

                            kcal_Sum += parseDouble(selectedMeals.get(i).getProductCalories());


                            weight_Sum += parseDouble(selectedMeals.get(i).getProductWeight());
                            carbohydrates_Sum += parseDouble(selectedMeals.get(i).getProductCarbohydrates());
                            sugar_Sum += parseDouble(selectedMeals.get(i).getProductSugar());
                            fats_Sum += parseDouble(selectedMeals.get(i).getProductFats());
                            saturatedFats_Sum += parseDouble(selectedMeals.get(i).getProductSaturatedFats());
                            protein_Sum += parseDouble(selectedMeals.get(i).getProductProtein());

                            // } else {
                            //composedMealsNames.append("\n").append(selectedMeals.get(i).getProductName());
                            //composedMealsCalories.append("\n").append(selectedMeals.get(i).getProductWeight());
//                        kcal_Sum += parseDouble(selectedMeals.get(i).getProductWeight());
//                        weight_Sum += parseDouble(selectedMeals.get(i).getProductWeight());
                            // }
                        }
                        //Toast.makeText(ComposedMealsActivity.this, composedMealsNames.toString(), Toast.LENGTH_SHORT).show();
                        //Toast.makeText(ComposedMealsActivity.this, composedMealsCalories.toString(), Toast.LENGTH_SHORT).show();
                        //Toast.makeText(ComposedMealsActivity.this, kcal_Sum + "", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(ComposedMealsActivity.this, weight_Sum + "", Toast.LENGTH_SHORT).show();

                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_composedDailyMeals_Name, dailyMealName);
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_ComposedDailyMeals_KCAL_SUM, format.format(kcal_Sum));
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_ComposedDailyMeals_WEIGHT_SUM, format.format(weight_Sum));
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_ComposedDailyMeals_CARBOHYDRATES_SUM, format.format(carbohydrates_Sum));
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_ComposedDailyMeals_SUGAR_SUM, format.format(sugar_Sum));
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_ComposedDailyMeals_FATS_SUM, format.format(fats_Sum));
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_ComposedDailyMeals_saturatedFATS_SUM, format.format(saturatedFats_Sum));
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_ComposedDailyMeals_PROTEIN_SUM, format.format(protein_Sum));

                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_FirstProduct_NAME, selectedMeals.get(0).getProductName());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_SecondProduct_NAME, selectedMeals.get(1).getProductName());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_ThirdProduct_NAME, selectedMeals.get(2).getProductName());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_FourthProduct_NAME, selectedMeals.get(3).getProductName());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_FifthProduct_NAME, selectedMeals.get(4).getProductName());

                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_FirstProduct_KCAL, selectedMeals.get(0).getProductCalories());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_SecondProduct_KCAL, selectedMeals.get(1).getProductCalories());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_ThirdProduct_KCAL, selectedMeals.get(2).getProductCalories());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_FourthProduct_KCAL, selectedMeals.get(3).getProductCalories());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_FifthProduct_KCAL, selectedMeals.get(4).getProductCalories());

                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_FirstProduct_WEIGHT, selectedMeals.get(0).getProductWeight());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_SecondProduct_WEIGHT, selectedMeals.get(1).getProductWeight());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_ThirdProduct_WEIGHT, selectedMeals.get(2).getProductWeight());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_FourthProduct_WEIGHT, selectedMeals.get(3).getProductWeight());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_FifthProduct_WEIGHT, selectedMeals.get(4).getProductWeight());

                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_FirstProduct_CARBOHYDRATES, selectedMeals.get(0).getProductCarbohydrates());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_SecondProduct_CARBOHYDRATES, selectedMeals.get(1).getProductCarbohydrates());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_ThirdProduct_CARBOHYDRATES, selectedMeals.get(2).getProductCarbohydrates());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_FourthProduct_CARBOHYDRATES, selectedMeals.get(3).getProductCarbohydrates());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_FifthProduct_CARBOHYDRATES, selectedMeals.get(4).getProductCarbohydrates());

                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_FirstProduct_SUGAR, selectedMeals.get(0).getProductSugar());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_SecondProduct_SUGAR, selectedMeals.get(1).getProductSugar());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_ThirdProduct_SUGAR, selectedMeals.get(2).getProductSugar());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_FourthProduct_SUGAR, selectedMeals.get(3).getProductSugar());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_FifthProduct_SUGAR, selectedMeals.get(4).getProductSugar());

                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_FirstProduct_FATS, selectedMeals.get(0).getProductFats());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_SecondProduct_FATS, selectedMeals.get(1).getProductFats());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_ThirdProduct_FATS, selectedMeals.get(2).getProductFats());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_FourthProduct_FATS, selectedMeals.get(3).getProductFats());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_FifthProduct_FATS, selectedMeals.get(4).getProductFats());

                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_FirstProduct_saturatedFATS, selectedMeals.get(0).getProductSaturatedFats());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_SecondProduct_saturatedFATS, selectedMeals.get(1).getProductSaturatedFats());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_ThirdProduct_saturatedFATS, selectedMeals.get(2).getProductSaturatedFats());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_FourthProduct_saturatedFATS, selectedMeals.get(3).getProductSaturatedFats());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_FifthProduct_saturatedFATS, selectedMeals.get(4).getProductSaturatedFats());

                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_FirstProduct_PROTEIN, selectedMeals.get(0).getProductProtein());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_SecondProduct_PROTEIN, selectedMeals.get(1).getProductProtein());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_ThirdProduct_PROTEIN, selectedMeals.get(2).getProductProtein());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_FourthProduct_PROTEIN, selectedMeals.get(3).getProductProtein());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_FifthProduct_PROTEIN, selectedMeals.get(4).getProductProtein());

                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_productsIncludedFirstProduct, selectedMeals.get(0).getProductMacros());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_productsIncludedSecondProduct, selectedMeals.get(1).getProductMacros());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_productsIncludedThirdProduct, selectedMeals.get(2).getProductMacros());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_productsIncludedFourthProduct, selectedMeals.get(3).getProductMacros());
                        cv.put(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_productsIncludedFifthProduct, selectedMeals.get(4).getProductMacros());
                        mDatabaseComposedDailyMeals.insert(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.TABLE_NAME, null, cv);

                        Toast.makeText(ComposedMealsActivity.this, R.string.Added_to_daily_meals, Toast.LENGTH_SHORT).show();

                        kcal_Sum = 0.0;
                        weight_Sum = 0.0;
                        carbohydrates_Sum = 0.0;
                        sugar_Sum = 0.0;
                        fats_Sum = 0.0;
                        saturatedFats_Sum = 0.0;
                        protein_Sum = 0.0;
                    }
                }
            }
        });
    }

    public interface testMetodyInterface {
        void testMetody(String test);

    }

    public void testMetody() {
        Toast.makeText(ComposedMealsActivity.this, "test", Toast.LENGTH_SHORT).show();
    }

    private Cursor getAllItems() {
        return mDatabaseComposedMeals.query(
                ComposedMealsColumns.ComposedMealsColumnsEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.composedMealsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewComposedMealsAdapter(this, getAllItems());
        recyclerView.setAdapter(adapter);
    }

    public void button_removeMeal(View view) {
        String positionString = editText_removeMeal.getText().toString();
        int position;

        if (positionString.length() == 0) {
            Toast.makeText(this, R.string.No_index_provided, Toast.LENGTH_SHORT).show();
        }
        if (positionString.length() != 0) {
            position = parseInt(positionString) - 1;

            if (position >= cursor.getCount() || position == -1) {
                Toast.makeText(this, R.string.No_product_with_this_index, Toast.LENGTH_SHORT).show();
            } else {
                cursor.moveToPosition(position);
                int productPosition = cursor.getInt(0);

                mDatabaseComposedMeals.delete(ComposedMealsColumns.ComposedMealsColumnsEntry.TABLE_NAME,
                        ComposedMealsColumns.ComposedMealsColumnsEntry._ID + "=" + productPosition, null);
                adapter.swapCursor(getAllItems());
                initRecyclerView();
                cursor = getAllItems();
                showOrHideNoDataTextView();
            }
//            cursor = getAllItems();
//            showOrHideNoDataTextView();
        }
    }

    @Override
    public void onButtonClicked(String text) {
    }

    private void showOrHideNoDataTextView() {
        if (getAllItems().getCount() == 0)
            textViewNoDataComposed.setVisibility(View.VISIBLE);
        else
            textViewNoDataComposed.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        return true;
    }
}