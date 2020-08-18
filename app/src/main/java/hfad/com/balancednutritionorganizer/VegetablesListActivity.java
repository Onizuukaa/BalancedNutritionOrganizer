package hfad.com.balancednutritionorganizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;

import hfad.com.balancednutritionorganizer.adapters.RecyclerViewSpecificProductListAdapter;
import hfad.com.balancednutritionorganizer.database_things.DatabaseAccess;

public class VegetablesListActivity extends AppCompatActivity {
    DatabaseAccess databaseAccess;
    Cursor cursorForVegetables;
    private RecyclerViewSpecificProductListAdapter adapter;
    private ArrayList<ReturnItem> mExampleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetables_list);
        setTitle("Vegetables");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        cursorForVegetables = databaseAccess.getAllDataFromTableVegetables();
        EditText editText = findViewById(R.id.editText_vegetablesSearchItem);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        createExampleList();

    }

    public void createExampleList() {
        mExampleList = new ArrayList<>();

        while (cursorForVegetables.moveToNext()) {
            mExampleList.add(new ReturnItem(cursorForVegetables.getString(0), cursorForVegetables.getString(1),
                    cursorForVegetables.getString(2), cursorForVegetables.getString(3),
                    cursorForVegetables.getString(4), cursorForVegetables.getString(5),
                    cursorForVegetables.getString(6), cursorForVegetables.getString(7)));
        }
        cursorForVegetables.close();
        databaseAccess.close();
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.vegetablesRecyclerView);
        adapter = new RecyclerViewSpecificProductListAdapter(this, mExampleList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void filter(String text) {
        ArrayList<ReturnItem> filteredList = new ArrayList<>();
        for (ReturnItem item : mExampleList) {
            if (item.getProductName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }
}