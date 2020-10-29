package hfad.com.balancednutritionorganizer.database_things;

import android.provider.BaseColumns;

public class ComposedDailyMealsColumns {

    private ComposedDailyMealsColumns() {}

    public static final class ComposedDailyMealsColumnsEntry implements BaseColumns {
        public static final String TABLE_NAME = "composedDailyMeals";

        public static final String COLUMN_composedDailyMeals_Name = "composedDailyMeals_Name";
        public static final String COLUMN_ComposedDailyMeals_KCAL_SUM = "composedDailyMeals_KCAL_SUM";
        public static final String COLUMN_ComposedDailyMeals_WEIGHT_SUM = "composedDailyMeals_WEIGHT_SUM";
        public static final String COLUMN_ComposedDailyMeals_CARBOHYDRATES_SUM = "composedDailyMeals_CARBOHYDRATES_SUM";
        public static final String COLUMN_ComposedDailyMeals_SUGAR_SUM = "composedDailyMeals_SUGAR_SUM";
        public static final String COLUMN_ComposedDailyMeals_FATS_SUM = "composedDailyMeals_FATS_SUM";
        public static final String COLUMN_ComposedDailyMeals_saturatedFATS_SUM = "composedDailyMeals_saturatedFATS_SUM";
        public static final String COLUMN_ComposedDailyMeals_PROTEIN_SUM = "composedDailyMeals_PROTEIN_SUM";

        public static final String COLUMN_FirstProduct_NAME = "FirstProduct_NAME";
        public static final String COLUMN_SecondProduct_NAME = "SecondProduct_NAME";
        public static final String COLUMN_ThirdProduct_NAME = "ThirdProduct_NAME";
        public static final String COLUMN_FourthProduct_NAME = "FourthProduct_NAME";
        public static final String COLUMN_FifthProduct_NAME = "FifthProduct_NAME";

        public static final String COLUMN_FirstProduct_KCAL = "FirstProduct_KCAL";
        public static final String COLUMN_SecondProduct_KCAL = "SecondProduct_KCAL";
        public static final String COLUMN_ThirdProduct_KCAL = "ThirdProduct_KCAL";
        public static final String COLUMN_FourthProduct_KCAL = "FourthProduct_KCAL";
        public static final String COLUMN_FifthProduct_KCAL = "FifthProduct_KCAL";

        public static final String COLUMN_FirstProduct_WEIGHT = "FirstProduct_WEIGHT";
        public static final String COLUMN_SecondProduct_WEIGHT = "SecondProduct_WEIGHT";
        public static final String COLUMN_ThirdProduct_WEIGHT = "ThirdProduct_WEIGHT";
        public static final String COLUMN_FourthProduct_WEIGHT = "FourthProduct_WEIGHT";
        public static final String COLUMN_FifthProduct_WEIGHT = "FifthProduct_WEIGHT";

        public static final String COLUMN_FirstProduct_CARBOHYDRATES = "FirstProduct_CARBOHYDRATES";
        public static final String COLUMN_SecondProduct_CARBOHYDRATES = "SecondProduct_CARBOHYDRATES";
        public static final String COLUMN_ThirdProduct_CARBOHYDRATES = "ThirdProduct_CARBOHYDRATES";
        public static final String COLUMN_FourthProduct_CARBOHYDRATES = "FourthProduct_CARBOHYDRATES";
        public static final String COLUMN_FifthProduct_CARBOHYDRATES = "FifthProduct_CARBOHYDRATES";

        public static final String COLUMN_FirstProduct_SUGAR = "FirstProduct_SUGAR";
        public static final String COLUMN_SecondProduct_SUGAR = "SecondProduct_SUGAR";
        public static final String COLUMN_ThirdProduct_SUGAR = "ThirdProduct_SUGAR";
        public static final String COLUMN_FourthProduct_SUGAR = "FourthProduct_SUGAR";
        public static final String COLUMN_FifthProduct_SUGAR = "FifthProduct_SUGAR";

        public static final String COLUMN_FirstProduct_FATS = "FirstProduct_FATS";
        public static final String COLUMN_SecondProduct_FATS = "SecondProduct_FATS";
        public static final String COLUMN_ThirdProduct_FATS = "ThirdProduct_FATS";
        public static final String COLUMN_FourthProduct_FATS = "FourthProduct_FATS";
        public static final String COLUMN_FifthProduct_FATS = "FifthProduct_FATS";

        public static final String COLUMN_FirstProduct_saturatedFATS = "FirstProduct_saturatedFATS";
        public static final String COLUMN_SecondProduct_saturatedFATS = "SecondProduct_saturatedFATS";
        public static final String COLUMN_ThirdProduct_saturatedFATS = "ThirdProduct_saturatedFATS";
        public static final String COLUMN_FourthProduct_saturatedFATS = "FourthProduct_saturatedFATS";
        public static final String COLUMN_FifthProduct_saturatedFATS = "FifthProduct_saturatedFATS";

        public static final String COLUMN_FirstProduct_PROTEIN = "FirstProduct_PROTEIN";
        public static final String COLUMN_SecondProduct_PROTEIN = "SecondProduct_PROTEIN";
        public static final String COLUMN_ThirdProduct_PROTEIN = "ThirdProduct_PROTEIN";
        public static final String COLUMN_FourthProduct_PROTEIN = "FourthProduct_PROTEIN";
        public static final String COLUMN_FifthProduct_PROTEIN = "FifthProduct_PROTEIN";

        public static final String COLUMN_productsIncludedFirstProduct = "productsIncludedFirstProduct";
        public static final String COLUMN_productsIncludedSecondProduct = "productsIncludedSecondProduct";
        public static final String COLUMN_productsIncludedThirdProduct = "productsIncludedThirdProduct";
        public static final String COLUMN_productsIncludedFourthProduct = "productsIncludedFourthProduct";
        public static final String COLUMN_productsIncludedFifthProduct = "productsIncludedFifthProduct";
    }
}