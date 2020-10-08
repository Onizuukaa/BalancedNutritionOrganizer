package hfad.com.balancednutritionorganizer.productsList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import java.util.ArrayList;

import hfad.com.balancednutritionorganizer.R;
import hfad.com.balancednutritionorganizer.ReturnItem;
import hfad.com.balancednutritionorganizer.adapters.RecyclerViewSpecificProductListAdapter;
import hfad.com.balancednutritionorganizer.database_things.DatabaseAccess;

public class DairyListActivity extends AppCompatActivity {
    DatabaseAccess databaseAccess;
    Cursor cursorForDairy;
    private RecyclerViewSpecificProductListAdapter adapter;
    private ArrayList<ReturnItem> mExampleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dairy_list);
        setTitle(R.string.Dairy);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        cursorForDairy = databaseAccess.getAllDataFromTableDairy();

        createExampleList();
    }
    public void createExampleList() {
        mExampleList = new ArrayList<>();

        while (cursorForDairy.moveToNext()) {
            mExampleList.add(new ReturnItem(cursorForDairy.getString(0), cursorForDairy.getString(1),
                    cursorForDairy.getString(2), cursorForDairy.getString(3),
                    cursorForDairy.getString(4), cursorForDairy.getString(5),
                    cursorForDairy.getString(6), cursorForDairy.getString(7)));
        }
        cursorForDairy.close();
        databaseAccess.close();
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.dairyRecyclerView);
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
}