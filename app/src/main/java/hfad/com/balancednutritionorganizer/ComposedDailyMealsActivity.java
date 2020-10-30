package hfad.com.balancednutritionorganizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import java.text.DecimalFormat;

import hfad.com.balancednutritionorganizer.adapters.RecyclerViewComposedDailyMealsAdapter;
import hfad.com.balancednutritionorganizer.database_things.ComposedDailyMealsColumns;
import hfad.com.balancednutritionorganizer.database_things.ComposedDailyMealsDBHelper;

public class ComposedDailyMealsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private SQLiteDatabase mDatabaseComposedDailyMeals;
    private RecyclerViewComposedDailyMealsAdapter adapter;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composed_daily_meals);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.Composed_Daily_Meals);

        ComposedDailyMealsDBHelper dbHelperComposedDailyMeals = new ComposedDailyMealsDBHelper(this);
        mDatabaseComposedDailyMeals = dbHelperComposedDailyMeals.getWritableDatabase();
        initRecyclerView();

        cursor = getAllItems();
    }

    public void button_removeDailyMeal(View view) {
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.composedDailyMealsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewComposedDailyMealsAdapter(this, getAllItems());
        recyclerView.setAdapter(adapter);
    }

    private Cursor getAllItems() {
        return mDatabaseComposedDailyMeals.query(
                ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.TABLE_NAME,
                null, null, null, null, null, null
        );
    }
}