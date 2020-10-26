package hfad.com.balancednutritionorganizer.productsList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

    //private ArrayList<ReturnItem> mExampleList;
    EditText editText_removeItem_CustomProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_product_list);
        editText_removeItem_CustomProduct = findViewById(R.id.editText_removeItem_CustomProduct);


        CustomProductsDBHelper dbHelperCustomProducts = new CustomProductsDBHelper(this);
        customProductsDatabase = dbHelperCustomProducts.getWritableDatabase();
        initRecyclerView();
        //cursorForCustomProducts = getAllItems();
        //createExampleList();

        cursor = getAllItems();
    }

//    public void createExampleList() {
//        mExampleList = new ArrayList<>();
//
//        while (cursorForCustomProducts.moveToNext()) {
//            mExampleList.add(new ReturnItem(cursorForCustomProducts.getString(0), cursorForCustomProducts.getString(1),
//                    cursorForCustomProducts.getString(2), cursorForCustomProducts.getString(3),
//                    cursorForCustomProducts.getString(4), cursorForCustomProducts.getString(5),
//                    cursorForCustomProducts.getString(6), cursorForCustomProducts.getString(7)));
//        }
//
//        cursorForCustomProducts.close();
//        initRecyclerView();
//    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.customProductsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //adapter = new RecyclerViewSpecificProductListAdapter(this, mExampleList);
        adapter = new RecyclerViewCustomProductAdapter(this, getAllItems());
        recyclerView.setAdapter(adapter);
    }

    public void button_removeCustomProduct(View view) {

        int position = parseInt(editText_removeItem_CustomProduct.getText().toString()) - 1;
        if (position >= cursor.getCount() || position == -1) {
            Toast.makeText(this, "No product with this index", Toast.LENGTH_SHORT).show();
        } else {
            cursor.moveToPosition(position);
            int productPosition = cursor.getInt(0);

            customProductsDatabase.delete(CustomProductsColumns.CustomProductsColumnsEntry.TABLE_NAME,
                    CustomProductsColumns.CustomProductsColumnsEntry._ID + "=" + productPosition, null);
            adapter.swapCursor(getAllItems());
        }
        cursor = getAllItems();
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