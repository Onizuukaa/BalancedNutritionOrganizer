package hfad.com.balancednutritionorganizer;

import androidx.appcompat.app.AppCompatActivity;
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

import java.util.ArrayList;

import hfad.com.balancednutritionorganizer.adapters.RecyclerViewComposedMealsAdapter;
import hfad.com.balancednutritionorganizer.database_things.ComposedMealsColumns;
import hfad.com.balancednutritionorganizer.database_things.ComposedMealsDBHelper;

import static java.lang.Integer.parseInt;

public class ComposedMealsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private SQLiteDatabase mDatabaseComposedMeals;
    private RecyclerViewComposedMealsAdapter adapter;
    Cursor cursor;

    Button button_removeMeal;
    EditText editText_removeMeal;

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
        cursor = getAllItems();
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
        cursor = getAllItems();
    }
}