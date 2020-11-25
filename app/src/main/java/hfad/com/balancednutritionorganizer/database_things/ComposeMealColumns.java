package hfad.com.balancednutritionorganizer.database_things;

import android.provider.BaseColumns;

public class ComposeMealColumns {
    private ComposeMealColumns() {}
    public static final class ComposeMealColumnsEntry implements BaseColumns {
        public static final String TABLE_NAME = "composeMeal";
        public static final String COLUMN_mealNAME = "mealName";
        public static final String COLUMN_CALORIES = "calories";
        public static final String COLUMN_CARBO = "carbo";
        public static final String COLUMN_SUGAR = "sugar";
        public static final String COLUMN_FATS = "fats";
        public static final String COLUMN_saturatedFATS = "saturatedFats";
        public static final String COLUMN_PROTEIN = "protein";
        public static final String COLUMN_WEIGHT = "weight";}}
