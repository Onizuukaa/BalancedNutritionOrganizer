package hfad.com.balancednutritionorganizer.database_things;

import android.provider.BaseColumns;

public class ComposedDailyMealsColumns {

    private ComposedDailyMealsColumns() {}

    public static final class ComposedDailyMealsColumnsEntry implements BaseColumns {
        public static final String TABLE_NAME = "composedDailyMeals";
        public static final String COLUMN_nameComposedDailyMeals = "nameComposedDailyMeals";
        public static final String COLUMN_nameComposedDailyMeals_KCAL_SUM = "nameComposedDailyMeals_KCAL_SUM";

        public static final String COLUMN_nameFirstProduct = "nameFirstProduct";
        public static final String COLUMN_nameSecondProduct = "nameSecondProduct";
        public static final String COLUMN_nameThirdProduct = "nameThirdProduct";
        public static final String COLUMN_nameFourthProduct = "nameFourthProduct";
        public static final String COLUMN_nameFifthProduct = "nameFifthProduct";

        public static final String COLUMN_macrosFirstProductsKcalWeight = "macrosFirstProductsKcalWeight";
        public static final String COLUMN_macrosSecondProductsKcalWeight = "macrosSecondProductsKcalWeight";
        public static final String COLUMN_macrosThirdProductsKcalWeight = "macrosThirdProductsKcalWeight";
        public static final String COLUMN_macrosFourthProductsKcalWeight = "macrosFourthProductsKcalWeight";
        public static final String COLUMN_macrosFifthProductsKcalWeight = "macrosFifthProductsKcalWeight";

        public static final String COLUMN_productsIncludedFirstProduct = "productsIncludedFirstProduct";
        public static final String COLUMN_productsIncludedSecondProduct = "productsIncludedSecondProduct";
        public static final String COLUMN_productsIncludedThirdProduct = "productsIncludedThirdProduct";
        public static final String COLUMN_productsIncludedFourthProduct = "productsIncludedFourthProduct";
        public static final String COLUMN_productsIncludedFifthProduct = "productsIncludedFifthProduct";
    }
}
