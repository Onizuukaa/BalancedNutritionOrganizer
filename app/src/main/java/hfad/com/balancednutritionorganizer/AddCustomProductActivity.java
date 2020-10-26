package hfad.com.balancednutritionorganizer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;

import hfad.com.balancednutritionorganizer.database_things.ComposedMealsColumns;
import hfad.com.balancednutritionorganizer.database_things.CustomProductsColumns;
import hfad.com.balancednutritionorganizer.database_things.CustomProductsDBHelper;

public class AddCustomProductActivity extends AppCompatActivity {
    private SQLiteDatabase databaseCustomProduct;
    EditText customProductName, customProductCalories, customProductCarbohydrates, customProductSugar,
            customProductFats, customProductSaturatedFats, customProductProtein;
    ImageView customProductImage;
    Uri imageData;

    private static final int GALLERY_REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_custom_product);

        CustomProductsDBHelper customProductDBHelper = new CustomProductsDBHelper(this);
        databaseCustomProduct = customProductDBHelper.getWritableDatabase();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        customProductName = findViewById(R.id.customProductName);
        customProductCalories = findViewById(R.id.customProductCalories);
        customProductCarbohydrates = findViewById(R.id.customProductCarbohydrates);
        customProductSugar = findViewById(R.id.customProductSugar);
        customProductFats = findViewById(R.id.customProductFats);
        customProductSaturatedFats = findViewById(R.id.customProductSaturatedFats);
        customProductProtein = findViewById(R.id.customProductProtein);

        customProductImage = findViewById(R.id.customProductImage);
        customProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true); // nowe
                intent.setAction(Intent.ACTION_OPEN_DOCUMENT); // nowe
                // intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pick an image"), GALLERY_REQUEST_CODE);
            }
        });

    }

    public void addCustomProduct(View view) {

        ContentValues cv = new ContentValues();
        cv.put(CustomProductsColumns.CustomProductsColumnsEntry.COLUMN_productName, customProductName.getText().toString());
        cv.put(CustomProductsColumns.CustomProductsColumnsEntry.COLUMN_productImage, imageData.toString());
        cv.put(CustomProductsColumns.CustomProductsColumnsEntry.COLUMN_productCalories, customProductCalories.getText().toString());
        cv.put(CustomProductsColumns.CustomProductsColumnsEntry.COLUMN_productCarbohydrates, customProductCarbohydrates.getText().toString());
        cv.put(CustomProductsColumns.CustomProductsColumnsEntry.COLUMN_productSugar, customProductSugar.getText().toString());
        cv.put(CustomProductsColumns.CustomProductsColumnsEntry.COLUMN_productFats, customProductFats.getText().toString());
        cv.put(CustomProductsColumns.CustomProductsColumnsEntry.COLUMN_productSaturatedFats, customProductSaturatedFats.getText().toString());
        cv.put(CustomProductsColumns.CustomProductsColumnsEntry.COLUMN_productProtein, customProductProtein.getText().toString());
        databaseCustomProduct.insert(CustomProductsColumns.CustomProductsColumnsEntry.TABLE_NAME, null, cv);
        Toast.makeText(this, "Meal added", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            imageData = data.getData();

            Glide.with(AddCustomProductActivity.this)
                    .asBitmap()
                    .load(imageData)
                    .into(customProductImage);
        }
    }
}