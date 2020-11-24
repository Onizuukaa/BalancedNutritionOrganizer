package hfad.com.balancednutritionorganizer.database_things;

import android.provider.BaseColumns;

public class CustomProductsColumns {
    private CustomProductsColumns() {}
    public static final class CustomProductsColumnsEntry implements BaseColumns {
        public static final String TABLE_NAME = "customProducts";
        public static final String COLUMN_productName = "productName";
        public static final String COLUMN_productImage = "productImage";
        public static final String COLUMN_productCalories = "productCalories";
        public static final String COLUMN_productCarbohydrates = "productCarbohydrates";
        public static final String COLUMN_productSugar = "productSugar";
        public static final String COLUMN_productFats = "productFats";
        public static final String COLUMN_productSaturatedFats = "productSaturatedFats";
        public static final String COLUMN_productProtein = "productProtein"; }}
