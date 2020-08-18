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

public class FruitsListActivity extends AppCompatActivity {
    DatabaseAccess databaseAccess;
    Cursor cursorForFruits;
private RecyclerViewSpecificProductListAdapter adapter;
private ArrayList<ReturnItem> mExampleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits_list);
        setTitle("Fruits");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        cursorForFruits = databaseAccess.getAllDataFromTableFruits();
        EditText editText = findViewById(R.id.editText_fruitsSearchItem);
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

        while (cursorForFruits.moveToNext()) {
            mExampleList.add(new ReturnItem(cursorForFruits.getString(0), cursorForFruits.getString(1),
                    cursorForFruits.getString(2), cursorForFruits.getString(3),
                    cursorForFruits.getString(4), cursorForFruits.getString(5),
                    cursorForFruits.getString(6), cursorForFruits.getString(7)));
        }
        cursorForFruits.close();
        databaseAccess.close();
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.fruitsRecyclerView);
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