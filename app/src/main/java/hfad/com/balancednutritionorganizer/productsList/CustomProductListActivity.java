package hfad.com.balancednutritionorganizer.productsList;

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
import android.view.inputmethod.EditorInfo;

import java.util.ArrayList;

import hfad.com.balancednutritionorganizer.R;
import hfad.com.balancednutritionorganizer.ReturnItem;
import hfad.com.balancednutritionorganizer.adapters.RecyclerViewSpecificProductListAdapter;
import hfad.com.balancednutritionorganizer.database_things.CustomProductsColumns;
import hfad.com.balancednutritionorganizer.database_things.CustomProductsDBHelper;
import hfad.com.balancednutritionorganizer.database_things.DatabaseAccess;

public class CustomProductListActivity extends AppCompatActivity {
    DatabaseAccess databaseAccess;
    Cursor cursorForCustomProducts;
    private SQLiteDatabase customProductsDatabase;
    private RecyclerViewSpecificProductListAdapter adapter;
    private ArrayList<ReturnItem> mExampleList;
    String test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_product_list);
        CustomProductsDBHelper dbHelperCustomProducts = new CustomProductsDBHelper(this);
        customProductsDatabase = dbHelperCustomProducts.getWritableDatabase();
        cursorForCustomProducts = getAllItems();
        test = "https://firebasestorage.googleapis.com/v0/b/nutritionbalancedorganizer.appspot.com/o/soya_milk.jpeg?alt=media&token=b8895708-0dea-45b3-a053-bc275f2bb4ab";
        createExampleList();
    }

    public void createExampleList() {
        mExampleList = new ArrayList<>();

//        while (cursorForCustomProducts.moveToNext()) {
//            mExampleList.add(new ReturnItem(cursorForCustomProducts.getString(0), cursorForCustomProducts.getString(1),
//                    cursorForCustomProducts.getString(2), cursorForCustomProducts.getString(3),
//                    cursorForCustomProducts.getString(4), cursorForCustomProducts.getString(5),
//                    cursorForCustomProducts.getString(6), cursorForCustomProducts.getString(7)));
//        }

        while (cursorForCustomProducts.moveToNext()) {
            mExampleList.add(new ReturnItem(cursorForCustomProducts.getString(0), test,
                    cursorForCustomProducts.getString(1), cursorForCustomProducts.getString(2),
                    cursorForCustomProducts.getString(3), cursorForCustomProducts.getString(4),
                    cursorForCustomProducts.getString(5), cursorForCustomProducts.getString(6)));
        }

        cursorForCustomProducts.close();
        //databaseAccess.close();
        cursorForCustomProducts.close();
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.customProductsRecyclerView);
        adapter = new RecyclerViewSpecificProductListAdapter(this, mExampleList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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

    private Cursor getAllItems() {
        return customProductsDatabase.query(
                CustomProductsColumns.CustomProductsColumnsEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

}