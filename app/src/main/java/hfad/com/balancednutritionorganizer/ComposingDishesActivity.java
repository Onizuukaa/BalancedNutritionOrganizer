package hfad.com.balancednutritionorganizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

import hfad.com.balancednutritionorganizer.adapters.GroceryAdapter;
import hfad.com.balancednutritionorganizer.adapters.RecyclerViewComposhingDishesAdapter;
import hfad.com.balancednutritionorganizer.database_things.GroceryContract;
import hfad.com.balancednutritionorganizer.database_things.GroceryDBHelper;

import static java.lang.Integer.parseInt;

public class ComposingDishesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private SQLiteDatabase mDatabase;
    private GroceryAdapter mAdapter;
    Cursor cursor;

    TextView textViewComposhingDishesKcal, textViewComposhingDishesCarbohydrates, textViewComposhingDishesGram,
            textViewComposhingDishesSugar, textViewComposhingDishesFats, textViewComposhingDishesSaturatedFats,
            textViewComposhingDishesProtein, textViewNoData;
    double caloriesSum, carbohydratesSum, sugarSum, fatsSum, saturatedFatsSum, proteinSum, gramSum;
    DecimalFormat format;
    EditText editText_removeItem;
    Button button_removeItem, button_removeAllItems;
    RecyclerViewComposhingDishesAdapter adapter;

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

        GroceryDBHelper dbHelper = new GroceryDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

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
                removeItem((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(recyclerView);

    }

    private void removeItem(long id) {
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
        textViewComposhingDishesKcal.setText(format.format(caloriesSum) + "\nKCAL");
        textViewComposhingDishesCarbohydrates.setText(format.format(carbohydratesSum) + "\ncarbohydrates");
        textViewComposhingDishesSugar.setText(format.format(sugarSum) + "\nsugar");
        textViewComposhingDishesFats.setText(format.format(fatsSum) + "\nfats");
        textViewComposhingDishesSaturatedFats.setText(format.format(saturatedFatsSum) + "\nsaturated fats");
        textViewComposhingDishesProtein.setText(format.format(proteinSum) + "\nprotein");
        textViewComposhingDishesGram.setText(format.format(gramSum) + "\nweight");

        showOrHideNoDataTextView();
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

            //sumAndViewMacros();

        }
        showOrHideNoDataTextView();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.composingDishesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new GroceryAdapter(this, getAllItems());
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
                GroceryContract.GroceryEntry.COLUMN_TIMESTAMP + " DESC"
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
        textViewComposhingDishesKcal.setText(format.format(caloriesSum) + "\nKCAL");
        textViewComposhingDishesCarbohydrates.setText(format.format(carbohydratesSum) + "\ncarbohydrates");
        textViewComposhingDishesSugar.setText(format.format(sugarSum) + "\nsugar");
        textViewComposhingDishesFats.setText(format.format(fatsSum) + "\nfats");
        textViewComposhingDishesSaturatedFats.setText(format.format(saturatedFatsSum) + "\nsaturated fats");
        textViewComposhingDishesProtein.setText(format.format(proteinSum) + "\nprotein");
        textViewComposhingDishesGram.setText(format.format(gramSum) + "\nweight");
    }

    public void button_ClearProductTable(View view) {

        mDatabase.delete(GroceryContract.GroceryEntry.TABLE_NAME,
                GroceryContract.GroceryEntry._ID + ">" + 1, null);
        mAdapter.swapCursor(getAllItems());


        sumAndViewMacros();
//        textViewNoData.setVisibility(View.VISIBLE);

        showOrHideNoDataTextView();
    }

    public void buttonSendDish(View view) {
    }

    private void initViews() {
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
                int position = parseInt(editText_removeItem.getText().toString());
                buttonRemoveItem(position);
            }
        });
    }

    private void showOrHideNoDataTextView(){
        if (getAllItems().getCount() == 0)
            textViewNoData.setVisibility(View.VISIBLE);
        else
            textViewNoData.setVisibility(View.INVISIBLE);
    }
}