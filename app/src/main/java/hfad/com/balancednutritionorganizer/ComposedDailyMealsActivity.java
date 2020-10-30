package hfad.com.balancednutritionorganizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

import hfad.com.balancednutritionorganizer.adapters.RecyclerViewComposedDailyMealsAdapter;
import hfad.com.balancednutritionorganizer.database_things.ComposedDailyMealsColumns;
import hfad.com.balancednutritionorganizer.database_things.ComposedDailyMealsDBHelper;
import hfad.com.balancednutritionorganizer.database_things.ComposedMealsColumns;

import static java.lang.Integer.parseInt;

public class ComposedDailyMealsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private SQLiteDatabase mDatabaseComposedDailyMeals;
    private RecyclerViewComposedDailyMealsAdapter adapter;
    Cursor cursor;
    EditText editText_removeItem_ComposedDailyMeals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composed_daily_meals);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.Composed_Daily_Meals);

        editText_removeItem_ComposedDailyMeals = findViewById(R.id.editText_removeItem_ComposedDailyMeals);
        ComposedDailyMealsDBHelper dbHelperComposedDailyMeals = new ComposedDailyMealsDBHelper(this);
        mDatabaseComposedDailyMeals = dbHelperComposedDailyMeals.getWritableDatabase();
        initRecyclerView();

        cursor = getAllItems();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.composedDailyMealsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewComposedDailyMealsAdapter(this, getAllItems());
        recyclerView.setAdapter(adapter);
    }

    public void button_removeDailyMeal(View view) {
        String positionString = editText_removeItem_ComposedDailyMeals.getText().toString();
        int position;

        if (positionString.length() == 0) {
            Toast.makeText(this, "No index provided", Toast.LENGTH_SHORT).show();
        }
        if (positionString.length() != 0) {
            position = parseInt(positionString) - 1;

            if (position >= cursor.getCount() || position == -1) {
                Toast.makeText(this, "No product with this index", Toast.LENGTH_SHORT).show();
            } else {
                cursor.moveToPosition(position);
                int productPosition = cursor.getInt(0);

                mDatabaseComposedDailyMeals.delete(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.TABLE_NAME,
                        ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry._ID + "=" + productPosition, null);
                adapter.swapCursor(getAllItems());
            }
            cursor = getAllItems();
            //showOrHideNoDataTextView();
        }
    }

    private Cursor getAllItems() {
        return mDatabaseComposedDailyMeals.query(
                ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.TABLE_NAME,
                null, null, null, null, null, null
        );
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