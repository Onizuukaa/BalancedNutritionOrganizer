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
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hfad.com.balancednutritionorganizer.R;
import hfad.com.balancednutritionorganizer.adapters.RecyclerViewCustomProductAdapter;
import hfad.com.balancednutritionorganizer.database_things.CustomProductsColumns;
import hfad.com.balancednutritionorganizer.database_things.CustomProductsDBHelper;

import static java.lang.Integer.parseInt;

public class CustomProductListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private SQLiteDatabase customProductsDatabase;
    private RecyclerViewCustomProductAdapter adapter;
    Cursor cursor;

    EditText editText_removeItem_CustomProduct;
    TextView textViewNoData_customProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_product_list);
        setTitle(R.string.Added_Food);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editText_removeItem_CustomProduct = findViewById(R.id.editText_removeItem_CustomProduct);
        textViewNoData_customProducts = findViewById(R.id.textViewNoData_customProducts);

        CustomProductsDBHelper dbHelperCustomProducts = new CustomProductsDBHelper(this);
        customProductsDatabase = dbHelperCustomProducts.getWritableDatabase();
        initRecyclerView();

        cursor = getAllItems();
        showOrHideNoDataTextView();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.customProductsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewCustomProductAdapter(this, getAllItems());
        recyclerView.setAdapter(adapter);
    }

    public void button_removeCustomProduct(View view) {
        String positionString = editText_removeItem_CustomProduct.getText().toString();
        int position;

        if (positionString.length() == 0) {
            Toast.makeText(this, getString(R.string.No_index_provided), Toast.LENGTH_SHORT).show();
        }
        if (positionString.length() != 0) {
            position = parseInt(positionString) - 1;

            if (position >= cursor.getCount() || position == -1) {
                Toast.makeText(this, getString(R.string.No_product_with_this_index), Toast.LENGTH_SHORT).show();
            } else {
                cursor.moveToPosition(position);
                int productPosition = cursor.getInt(0); //Pozycja kolumny z _id, które jest wyznacznikiem pozycji

                customProductsDatabase.delete(CustomProductsColumns.CustomProductsColumnsEntry.TABLE_NAME,
                        CustomProductsColumns.CustomProductsColumnsEntry._ID + "=" + productPosition, null);
                adapter.swapCursor(getAllItems());
                initRecyclerView();
                cursor = getAllItems();
                showOrHideNoDataTextView();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

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

    private void showOrHideNoDataTextView() {
        if (getAllItems().getCount() == 0)
            textViewNoData_customProducts.setVisibility(View.VISIBLE);
        else
            textViewNoData_customProducts.setVisibility(View.INVISIBLE);
    }
}