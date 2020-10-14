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

import hfad.com.balancednutritionorganizer.adapters.RecyclerViewComposeMealAdapter;
import hfad.com.balancednutritionorganizer.database_things.ComposedMealsDBHelper;
import hfad.com.balancednutritionorganizer.database_things.GroceryContract;
import hfad.com.balancednutritionorganizer.database_things.ComposedMealsColumns;
import hfad.com.balancednutritionorganizer.database_things.GroceryDBHelper;

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
    double caloriesSum, carbohydratesSum, sugarSum, fatsSum, saturatedFatsSum, proteinSum, gramSum;
    DecimalFormat format;
    EditText editText_removeItem;
    Button button_removeItem, button_removeAllItems;
    String aaa="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_meal);

        GroceryDBHelper dbHelper = new GroceryDBHelper(this);
        ComposedMealsDBHelper dbHelperComposedMeals = new ComposedMealsDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();
        mDatabaseComposedMeals = dbHelperComposedMeals.getWritableDatabase();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        format = new DecimalFormat("#.#");
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
        mDatabase.delete(GroceryContract.GroceryEntry.TABLE_NAME,
                GroceryContract.GroceryEntry._ID + "=" + id, null);
        mAdapter.swapCursor(getAllItems());

        cursor = getAllItems();
        caloriesSum = 0;
        carbohydratesSum = 0;
        sugarSum = 0;
        fatsSum = 0;
        saturatedFatsSum = 0;
        proteinSum = 0;
        gramSum = 0;

        while (cursor.moveToNext()) {
            caloriesSum += cursor.getDouble(2);
            carbohydratesSum += cursor.getDouble(3);
            sugarSum += cursor.getDouble(4);
            fatsSum += cursor.getDouble(5);
            saturatedFatsSum += cursor.getDouble(6);
            proteinSum += cursor.getDouble(7);
            gramSum += cursor.getDouble(8);
        }
        textViewComposeMealKcal.setText(format.format(caloriesSum) + "\nKCAL");
        textViewComposeMealCarbohydrates.setText(format.format(carbohydratesSum) + "\ncarbohydrates");
        textViewComposeMealSugar.setText(format.format(sugarSum) + "\nsugar");
        textViewComposeMealFats.setText(format.format(fatsSum) + "\nfats");
        textViewComposeMealSaturatedFats.setText(format.format(saturatedFatsSum) + "\nsaturated fats");
        textViewComposeMealProtein.setText(format.format(proteinSum) + "\nprotein");
        textViewComposeMealGram.setText(format.format(gramSum) + "\nweight");

        showOrHideNoDataTextView();
    }

    private void buttonRemoveItem(int position) {
        if (position >= cursor.getCount() || position == -1) {
            Toast.makeText(this, R.string.No_product_with_this_index, Toast.LENGTH_SHORT).show();
        } else {
            cursor.moveToPosition(position);
             int productPosition = cursor.getInt(0);

            mDatabase.delete(GroceryContract.GroceryEntry.TABLE_NAME,
                    GroceryContract.GroceryEntry._ID + "=" + productPosition, null);
            mAdapter.swapCursor(getAllItems());

            sumAndViewMacros();
            //Pozostałości kodu, zostawiłem dla notifyRemoved(position) bo nie wiem co to robi.
//            productSaturatedFatsArrayList.remove(position);
//            productProteinArrayList.remove(position);
//            adapter.notifyItemRemoved(position);

        }
        showOrHideNoDataTextView();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.composingDishesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecyclerViewComposeMealAdapter(this, getAllItems());

        recyclerView.setAdapter(mAdapter);

        showOrHideNoDataTextView();
    }

    private Cursor getAllItems() {
        return mDatabase.query(
                GroceryContract.GroceryEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                //GroceryContract.GroceryEntry.COLUMN_TIMESTAMP + " DESC"
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
        textViewComposeMealKcal.setText(format.format(caloriesSum) + "\nKCAL");
        textViewComposeMealCarbohydrates.setText(format.format(carbohydratesSum) + "\ncarbohydrates");
        textViewComposeMealSugar.setText(format.format(sugarSum) + "\nsugar");
        textViewComposeMealFats.setText(format.format(fatsSum) + "\nfats");
        textViewComposeMealSaturatedFats.setText(format.format(saturatedFatsSum) + "\nsaturated fats");
        textViewComposeMealProtein.setText(format.format(proteinSum) + "\nprotein");
        textViewComposeMealGram.setText(format.format(gramSum) + "\nweight");
    }

    public void button_ClearProductTable(View view) {

        mDatabase.delete(GroceryContract.GroceryEntry.TABLE_NAME,
                GroceryContract.GroceryEntry._ID + ">" + 0, null);
        mAdapter.swapCursor(getAllItems());

        sumAndViewMacros();
        showOrHideNoDataTextView();
    }

    public void button_AddMeal(View view) {
        //W tym miejscu ma być kod, który wyśle do bazy produkty wraz z nazwą posiłku - jednak może na początku inaczej
        cursor = getAllItems();
        while (cursor.moveToNext()) {
            aaa += " | "+ cursor.getString(1) + " | calories " + format.format(cursor.getDouble(2))
                    + " | carbo " + format.format(cursor.getDouble(3)) + "g | "
                    + " | sugar " + format.format(cursor.getDouble(4)) + "g | "
                    + " | fats " + format.format(cursor.getDouble(5)) + "g | "
                    + " | saturated fats " + format.format(cursor.getDouble(6)) + "g | "
                    + " | protein " + format.format(cursor.getDouble(7)) + "g | "
                    + " | weight " + format.format(cursor.getDouble(8)) + "g | \n";
        }
        //aaa = "lala";
        //aaa = " | Apple | calories 52 | carbo 14g | sugar 10g | fats 0.2g | saturatedfats 0g | protein 0.3g | weight 100g";
        //aaa = "1. Apple | calories 52 | carbo 14g | sugar 10g | fats 0.2g | saturatedfats 0g | protein 0.3g | weight 100g";
        ContentValues cv = new ContentValues();
        cv.put(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_MEALNAME, editTextMealName.getText().toString());
        cv.put(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_CALORIES, format.format(caloriesSum));
        cv.put(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_WEIGHT, format.format(gramSum));
        cv.put(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_CARBO, format.format(carbohydratesSum));
        cv.put(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_SUGAR, format.format(sugarSum));
        cv.put(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_PROTEIN, format.format(proteinSum));
        cv.put(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_FATS, format.format(fatsSum));
        cv.put(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_SATURATEDFATS, format.format(saturatedFatsSum));
        cv.put(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_PRODUCTSINCLUDED, aaa);
        mDatabaseComposedMeals.insert(ComposedMealsColumns.ComposedMealsColumnsEntry.TABLE_NAME, null, cv);
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
        button_removeItem = (Button) findViewById(R.id.button_removeItem);
        button_removeAllItems = (Button) findViewById(R.id.buttonResetIngredients);

        button_removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = parseInt(editText_removeItem.getText().toString()) - 1;
                buttonRemoveItem(position);
            }
        });
        editTextMealName = (EditText) findViewById(R.id.editTextMealName);
    }

    private void showOrHideNoDataTextView() {
        if (getAllItems().getCount() == 0)
            textViewNoData.setVisibility(View.VISIBLE);
        else
            textViewNoData.setVisibility(View.INVISIBLE);
    }
}