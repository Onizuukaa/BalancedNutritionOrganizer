package hfad.com.balancednutritionorganizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import hfad.com.balancednutritionorganizer.adapters.RecyclerViewComposeMealAdapter;
import hfad.com.balancednutritionorganizer.database_things.ComposedMealsDBHelper;
import hfad.com.balancednutritionorganizer.database_things.ComposeMealColumns;
import hfad.com.balancednutritionorganizer.database_things.ComposedMealsColumns;
import hfad.com.balancednutritionorganizer.database_things.ComposeMealDBHelper;

import static java.lang.Integer.parseInt;

public class ComposeMealActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private SQLiteDatabase mDatabase, mDatabaseComposedMeals;
    private RecyclerViewComposeMealAdapter mAdapter;
    Cursor cursor;

    TextView textViewComposeMealKcal, textViewComposeMealCarbohydrates, textViewComposeMealGram,
            textViewComposeMealSugar, textViewComposeMealFats, textViewComposeMealSaturatedFats,
            textViewComposeMealProtein, textViewNoData;
    EditText editTextMealName;
    double caloriesSum = 0.0, carbohydratesSum = 0.0, sugarSum = 0.0, fatsSum = 0.0, saturatedFatsSum = 0.0,
            proteinSum = 0.0, gramSum = 0.0;
    DecimalFormat format;
    EditText editText_removeItem;
    Button button_removeAllItems;
    String macrosForMeal = "";
    DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_meal);
        setTitle(R.string.Compose_The_Meal);

        ComposeMealDBHelper dbHelper = new ComposeMealDBHelper(this);
        ComposedMealsDBHelper dbHelperComposedMeals = new ComposedMealsDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();
        mDatabaseComposedMeals = dbHelperComposedMeals.getWritableDatabase();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        symbols.setDecimalSeparator('.');
        format = new DecimalFormat("#.#");
        format.setDecimalFormatSymbols(symbols);
        format.setMaximumFractionDigits(1);
        format.setDecimalSeparatorAlwaysShown(false);

        initViews();
        initRecyclerView();
        sumAndViewMacros();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                removeItemSwiped((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(recyclerView);
    }

    private void removeItemSwiped(long id) {
        mDatabase.delete(ComposeMealColumns.ComposeMealColumnsEntry.TABLE_NAME,
                ComposeMealColumns.ComposeMealColumnsEntry._ID + "=" + id, null);
        mAdapter.swapCursor(getAllItems());
        sumAndViewMacros();
        showOrHideNoDataTextView();
    }

    public void buttonRemoveItem(View view) {
        String positionString = editText_removeItem.getText().toString();
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

                mDatabase.delete(ComposeMealColumns.ComposeMealColumnsEntry.TABLE_NAME,
                        ComposeMealColumns.ComposeMealColumnsEntry._ID + "=" + productPosition, null);
                mAdapter.swapCursor(getAllItems());

                sumAndViewMacros();
            }
            showOrHideNoDataTextView();
        }
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.composingDishesRecyclerView);
        mAdapter = new RecyclerViewComposeMealAdapter(this, getAllItems());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        showOrHideNoDataTextView();
    }

    private Cursor getAllItems() {
        return mDatabase.query(
                ComposeMealColumns.ComposeMealColumnsEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    private void sumAndViewMacros() {

        cursor = getAllItems();

        caloriesSum = 0.0;
        carbohydratesSum = 0.0;
        gramSum = 0.0;
        sugarSum = 0.0;
        fatsSum = 0.0;
        saturatedFatsSum = 0.0;
        proteinSum = 0.0;

        while (cursor.moveToNext()) {
            caloriesSum += cursor.getDouble(2);
            carbohydratesSum += cursor.getDouble(3);
            sugarSum += cursor.getDouble(4);
            fatsSum += cursor.getDouble(5);
            saturatedFatsSum += cursor.getDouble(6);
            proteinSum += cursor.getDouble(7);
            gramSum += cursor.getDouble(8);
        }
        textViewComposeMealKcal.setText(format.format(caloriesSum) + "\n"+getString(R.string.calories_small));
        textViewComposeMealCarbohydrates.setText(format.format(carbohydratesSum) + "\n" + getString(R.string.carbohydrates_small));
        textViewComposeMealSugar.setText(format.format(sugarSum) + "\n" + getString(R.string.sugar_small));
        textViewComposeMealFats.setText(format.format(fatsSum) + "\n" + getString(R.string.fats_small));
        textViewComposeMealSaturatedFats.setText(format.format(saturatedFatsSum) + "\n" + getString(R.string.saturated_fats_small));
        textViewComposeMealProtein.setText(format.format(proteinSum) + "\n" + getString(R.string.protein_small));
        textViewComposeMealGram.setText(format.format(gramSum) + "g\n" + getString(R.string.weight_small));
    }

    public void button_ClearFoodList(View view) {

        mDatabase.delete(ComposeMealColumns.ComposeMealColumnsEntry.TABLE_NAME,
                ComposeMealColumns.ComposeMealColumnsEntry._ID + ">" + 0, null);
        mAdapter.swapCursor(getAllItems());

        sumAndViewMacros();
        showOrHideNoDataTextView();
    }

    public void button_AddMeal(View view) {
        if ( editTextMealName.getText().toString().length() == 0 ){
            Toast.makeText(this, R.string.Name_was_not_provided, Toast.LENGTH_SHORT).show();
        }else{
            cursor = getAllItems();

            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    macrosForMeal += "" + cursor.getString(1) + " | calories " + format.format(cursor.getDouble(2))
                            + " | carbo " + format.format(cursor.getDouble(3)) + "g | "
                            + " sugar " + format.format(cursor.getDouble(4)) + "g | "
                            + " fats " + format.format(cursor.getDouble(5)) + "g | "
                            + " saturated fats " + format.format(cursor.getDouble(6)) + "g | "
                            + " protein " + format.format(cursor.getDouble(7)) + "g | "
                            + " weight " + format.format(cursor.getDouble(8)) + "g" +
                            "\n\n";
                }

                ContentValues cv = new ContentValues();
                cv.put(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_MEALNAME, editTextMealName.getText().toString());
                cv.put(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_CALORIES, format.format(caloriesSum));
                cv.put(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_WEIGHT, format.format(gramSum));
                cv.put(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_CARBO, format.format(carbohydratesSum));
                cv.put(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_SUGAR, format.format(sugarSum));
                cv.put(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_PROTEIN, format.format(proteinSum));
                cv.put(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_FATS, format.format(fatsSum));
                cv.put(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_SATURATEDFATS, format.format(saturatedFatsSum));
                cv.put(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_PRODUCTSINCLUDED, macrosForMeal);
                mDatabaseComposedMeals.insert(ComposedMealsColumns.ComposedMealsColumnsEntry.TABLE_NAME, null, cv);

                Toast.makeText(this, R.string.Food_added, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.First_add_food, Toast.LENGTH_SHORT).show();
            }
            macrosForMeal = "";
        }
    }

    private void initViews() {
        textViewComposeMealKcal = (TextView) findViewById(R.id.textViewComposhingDishesKcal);
        textViewComposeMealCarbohydrates = (TextView) findViewById(R.id.textViewComposhingDishesCarbohydrates);
        textViewComposeMealGram = (TextView) findViewById(R.id.textViewComposhingDishesGram);
        textViewComposeMealSugar = (TextView) findViewById(R.id.textViewComposhingDishesSugar);
        textViewComposeMealFats = (TextView) findViewById(R.id.textViewComposhingDishesFats);
        textViewComposeMealSaturatedFats = (TextView) findViewById(R.id.textViewComposhingDishesSaturatedFats);
        textViewComposeMealProtein = (TextView) findViewById(R.id.textViewComposhingDishesProtein);
        textViewNoData = (TextView) findViewById(R.id.textViewNoData);
        textViewNoData.setVisibility(View.INVISIBLE);
        editText_removeItem = (EditText) findViewById(R.id.editText_removeItem);
        button_removeAllItems = (Button) findViewById(R.id.buttonResetIngredients);
        editTextMealName = (EditText) findViewById(R.id.editTextMealName);
    }

    private void showOrHideNoDataTextView() {
        if (getAllItems().getCount() == 0)
            textViewNoData.setVisibility(View.VISIBLE);
        else
            textViewNoData.setVisibility(View.INVISIBLE);
    }
}