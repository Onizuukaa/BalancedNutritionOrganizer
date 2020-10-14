package hfad.com.balancednutritionorganizer.database_things;

import android.provider.BaseColumns;

public class ComposedMealsColumns {

    private ComposedMealsColumns() {}

    public static final class ComposedMealsColumnsEntry implements BaseColumns {
        public static final String TABLE_NAME = "composedMeals";
        public static final String COLUMN_MEALNAME = "mealName";
        public static final String COLUMN_CALORIES = "calories";
        public static final String COLUMN_WEIGHT = "weight";
        public static final String COLUMN_CARBO = "carbo";
        public static final String COLUMN_SUGAR = "sugar";
        public static final String COLUMN_PROTEIN = "protein";
        public static final String COLUMN_FATS = "fats";
        public static final String COLUMN_SATURATEDFATS = "saturatedFats";
        public static final String COLUMN_PRODUCTSINCLUDED = "productsIncluded";
    }

}
