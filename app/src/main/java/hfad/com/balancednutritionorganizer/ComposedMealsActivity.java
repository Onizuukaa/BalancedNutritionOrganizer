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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hfad.com.balancednutritionorganizer.adapters.RecyclerViewComposedMealsAdapter;
import hfad.com.balancednutritionorganizer.database_things.ComposedMealsColumns;
import hfad.com.balancednutritionorganizer.database_things.ComposedMealsDBHelper;

import static java.lang.Integer.parseInt;

public class ComposedMealsActivity extends AppCompatActivity implements BottomSheetDialog.BottomSheetListener {
    RecyclerView recyclerView;
    private SQLiteDatabase mDatabaseComposedMeals;
    private RecyclerViewComposedMealsAdapter adapter;
    Cursor cursor;
    Button button_removeMeal;
    EditText editText_removeMeal;
    TextView textViewNoDataComposed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composed_meals);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ComposedMealsDBHelper dbHelperComposedMeals = new ComposedMealsDBHelper(this);
        mDatabaseComposedMeals = dbHelperComposedMeals.getWritableDatabase();
        initRecyclerView();
        editText_removeMeal = findViewById(R.id.editText_removeItem_ComposedMeals);
        button_removeMeal = findViewById(R.id.button_removeItem_ComposedMeals);
        button_removeMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = parseInt(editText_removeMeal.getText().toString()) -1;
                button_removeMeal(position);
            }
        });
        textViewNoDataComposed = findViewById(R.id.textViewNoDataComposed);

        cursor = getAllItems();
        showOrHideNoDataTextView();
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

    public void button_removeMeal(int position){
        if (position >= cursor.getCount() || position == -1) {
            Toast.makeText(this, "No product with this index", Toast.LENGTH_SHORT).show();
        } else {
            cursor.moveToPosition(position);
            int productPosition = cursor.getInt(0);

            mDatabaseComposedMeals.delete(ComposedMealsColumns.ComposedMealsColumnsEntry.TABLE_NAME,
                    ComposedMealsColumns.ComposedMealsColumnsEntry._ID + "=" + productPosition, null);
            adapter.swapCursor(getAllItems());
        }
        //cursor = getAllItems();
        showOrHideNoDataTextView();
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.example_menu, menu);
//
//        MenuItem searchItem = menu.findItem(R.id.action_search);
//        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) searchItem.getActionView();
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                adapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
//        return true;
//    }
}