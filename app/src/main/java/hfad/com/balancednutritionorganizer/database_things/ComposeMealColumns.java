package hfad.com.balancednutritionorganizer.database_things;

import android.provider.BaseColumns;

public class ComposeMealColumns {

    private ComposeMealColumns() {}

    public static final class GroceryEntry implements BaseColumns {
        public static final String TABLE_NAME = "composeMlea";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_CARBO = "carbo";
        public static final String COLUMN_SUGAR = "sugar";
        public static final String COLUMN_FATS = "fats";
        public static final String COLUMN_SATURATEDFATS = "saturatedfats";
        public static final String COLUMN_PROTEIN = "protein";
        public static final String COLUMN_WEIGHT = "weight";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
//    public static final class GroceryEntry2 implements BaseColumns {
//        public static final String TABLE_NAME = "groceryList2";
//        public static final String COLUMN_NAME = "name";
//        public static final String COLUMN_AMOUNT = "amount";
//        public static final String COLUMN_CARBO = "carbo";
//        public static final String COLUMN_SUGAR = "sugar";
//        public static final String COLUMN_FATS = "fats";
//        public static final String COLUMN_SATURATEDFATS = "saturatedfats";
//        public static final String COLUMN_PROTEIN = "protein";
//        public static final String COLUMN_WEIGHT = "weight";
//        public static final String COLUMN_TIMESTAMP = "timestamp";
//    }
}
