package hfad.com.balancednutritionorganizer;

public class ReturnItemComposedDailyMeals {
        private String composedDailyMeals_Name;
        private String ComposedDailyMeals_KCAL_SUM;
        private String ComposedDailyMeals_WEIGHT_SUM;
        private String ComposedDailyMeals_CARBOHYDRATES_SUM;
        private String ComposedDailyMeals_SUGAR_SUM;
        private String ComposedDailyMeals_FATS_SUM;
        private String ComposedDailyMeals_saturatedFATS_SUM;
        private String ComposedDailyMeals_PROTEIN_SUM;

        private String FirstProduct_NAME;
        private String SecondProduct_NAME;
        private String ThirdProduct_NAME;
        private String FourthProduct_NAME;
        private String FifthProduct_NAME;

        private String FirstProduct_KCAL;
        private String SecondProduct_KCAL;
        private String ThirdProduct_KCAL;
        private String FourthProduct_KCAL;
        private String FifthProduct_KCAL;

        private String FirstProduct_WEIGHT;
        private String SecondProduct_WEIGHT;
        private String ThirdProduct_WEIGHT;
        private String FourthProduct_WEIGHT;
        private String FifthProduct_WEIGHT;

        private String FirstProduct_CARBOHYDRATES;
        private String SecondProduct_CARBOHYDRATES;
        private String ThirdProduct_CARBOHYDRATES;
        private String FourthProduct_CARBOHYDRATES;
        private String FifthProduct_CARBOHYDRATES;

        private String FirstProduct_SUGAR;
        private String SecondProduct_SUGAR;
        private String ThirdProduct_SUGAR;
        private String FourthProduct_SUGAR;
        private String FifthProduct_SUGAR;

        private String FirstProduct_FATS;
        private String SecondProduct_FATS;
        private String ThirdProduct_FATS;
        private String FourthProduct_FATS;
        private String FifthProduct_FATS;

        private String FirstProduct_saturatedFATS;
        private String SecondProduct_saturatedFATS;
        private String ThirdProduct_saturatedFATS;
        private String FourthProduct_saturatedFATS;
        private String FifthProduct_saturatedFATS;

        private String FirstProduct_PROTEIN;
        private String SecondProduct_PROTEIN;
        private String ThirdProduct_PROTEIN;
        private String FourthProduct_PROTEIN;
        private String FifthProduct_PROTEIN;

        private String productsIncludedFirstProduct;
        private String productsIncludedSecondProduct;
        private String productsIncludedThirdProduct;
        private String productsIncludedFourthProduct;
        private String productsIncludedFifthProduct;
        private int productDailyMealsPosition;

        public ReturnItemComposedDailyMeals(String composedDailyMeals_Name, String composedDailyMeals_KCAL_SUM, String composedDailyMeals_WEIGHT_SUM, String composedDailyMeals_CARBOHYDRATES_SUM,
                                            String composedDailyMeals_SUGAR_SUM, String composedDailyMeals_FATS_SUM, String composedDailyMeals_saturatedFATS_SUM, String composedDailyMeals_PROTEIN_SUM,
                                            String firstProduct_NAME, String secondProduct_NAME, String thirdProduct_NAME, String fourthProduct_NAME, String fifthProduct_NAME, String firstProduct_KCAL,
                                            String secondProduct_KCAL, String thirdProduct_KCAL, String fourthProduct_KCAL, String fifthProduct_KCAL, String firstProduct_WEIGHT,
                                            String secondProduct_WEIGHT, String thirdProduct_WEIGHT, String fourthProduct_WEIGHT, String fifthProduct_WEIGHT, String firstProduct_CARBOHYDRATES,
                                            String secondProduct_CARBOHYDRATES, String thirdProduct_CARBOHYDRATES, String fourthProduct_CARBOHYDRATES, String fifthProduct_CARBOHYDRATES,
                                            String firstProduct_SUGAR, String secondProduct_SUGAR, String thirdProduct_SUGAR, String fourthProduct_SUGAR, String fifthProduct_SUGAR,
                                            String firstProduct_FATS, String secondProduct_FATS, String thirdProduct_FATS, String fourthProduct_FATS, String fifthProduct_FATS,
                                            String firstProduct_saturatedFATS, String secondProduct_saturatedFATS, String thirdProduct_saturatedFATS, String fourthProduct_saturatedFATS,
                                            String fifthProduct_saturatedFATS, String firstProduct_PROTEIN, String secondProduct_PROTEIN, String thirdProduct_PROTEIN, String fourthProduct_PROTEIN,
                                            String fifthProduct_PROTEIN, String productsIncludedFirstProduct, String productsIncludedSecondProduct, String productsIncludedThirdProduct,
                                            String productsIncludedFourthProduct, String productsIncludedFifthProduct, int productDailyMealsPosition) {

                this.composedDailyMeals_Name = composedDailyMeals_Name;
                ComposedDailyMeals_KCAL_SUM = composedDailyMeals_KCAL_SUM;
                ComposedDailyMeals_WEIGHT_SUM = composedDailyMeals_WEIGHT_SUM;
                ComposedDailyMeals_CARBOHYDRATES_SUM = composedDailyMeals_CARBOHYDRATES_SUM;
                ComposedDailyMeals_SUGAR_SUM = composedDailyMeals_SUGAR_SUM;
                ComposedDailyMeals_FATS_SUM = composedDailyMeals_FATS_SUM;
                ComposedDailyMeals_saturatedFATS_SUM = composedDailyMeals_saturatedFATS_SUM;
                ComposedDailyMeals_PROTEIN_SUM = composedDailyMeals_PROTEIN_SUM;
                FirstProduct_NAME = firstProduct_NAME;
                SecondProduct_NAME = secondProduct_NAME;
                ThirdProduct_NAME = thirdProduct_NAME;
                FourthProduct_NAME = fourthProduct_NAME;
                FifthProduct_NAME = fifthProduct_NAME;
                FirstProduct_KCAL = firstProduct_KCAL;
                SecondProduct_KCAL = secondProduct_KCAL;
                ThirdProduct_KCAL = thirdProduct_KCAL;
                FourthProduct_KCAL = fourthProduct_KCAL;
                FifthProduct_KCAL = fifthProduct_KCAL;
                FirstProduct_WEIGHT = firstProduct_WEIGHT;
                SecondProduct_WEIGHT = secondProduct_WEIGHT;
                ThirdProduct_WEIGHT = thirdProduct_WEIGHT;
                FourthProduct_WEIGHT = fourthProduct_WEIGHT;
                FifthProduct_WEIGHT = fifthProduct_WEIGHT;
                FirstProduct_CARBOHYDRATES = firstProduct_CARBOHYDRATES;
                SecondProduct_CARBOHYDRATES = secondProduct_CARBOHYDRATES;
                ThirdProduct_CARBOHYDRATES = thirdProduct_CARBOHYDRATES;
                FourthProduct_CARBOHYDRATES = fourthProduct_CARBOHYDRATES;
                FifthProduct_CARBOHYDRATES = fifthProduct_CARBOHYDRATES;
                FirstProduct_SUGAR = firstProduct_SUGAR;
                SecondProduct_SUGAR = secondProduct_SUGAR;
                ThirdProduct_SUGAR = thirdProduct_SUGAR;
                FourthProduct_SUGAR = fourthProduct_SUGAR;
                FifthProduct_SUGAR = fifthProduct_SUGAR;
                FirstProduct_FATS = firstProduct_FATS;
                SecondProduct_FATS = secondProduct_FATS;
                ThirdProduct_FATS = thirdProduct_FATS;
                FourthProduct_FATS = fourthProduct_FATS;
                FifthProduct_FATS = fifthProduct_FATS;
                FirstProduct_saturatedFATS = firstProduct_saturatedFATS;
                SecondProduct_saturatedFATS = secondProduct_saturatedFATS;
                ThirdProduct_saturatedFATS = thirdProduct_saturatedFATS;
                FourthProduct_saturatedFATS = fourthProduct_saturatedFATS;
                FifthProduct_saturatedFATS = fifthProduct_saturatedFATS;
                FirstProduct_PROTEIN = firstProduct_PROTEIN;
                SecondProduct_PROTEIN = secondProduct_PROTEIN;
                ThirdProduct_PROTEIN = thirdProduct_PROTEIN;
                FourthProduct_PROTEIN = fourthProduct_PROTEIN;
                FifthProduct_PROTEIN = fifthProduct_PROTEIN;
                this.productsIncludedFirstProduct = productsIncludedFirstProduct;
                this.productsIncludedSecondProduct = productsIncludedSecondProduct;
                this.productsIncludedThirdProduct = productsIncludedThirdProduct;
                this.productsIncludedFourthProduct = productsIncludedFourthProduct;
                this.productsIncludedFifthProduct = productsIncludedFifthProduct;
                this.productDailyMealsPosition = productDailyMealsPosition;
        }

        public int getProductDailyMealsPosition() {
                return productDailyMealsPosition;
        }

        public String getComposedDailyMeals_Name() {
                return composedDailyMeals_Name;
        }

        public String getComposedDailyMeals_KCAL_SUM() {
                return ComposedDailyMeals_KCAL_SUM;
        }

        public String getComposedDailyMeals_WEIGHT_SUM() {
                return ComposedDailyMeals_WEIGHT_SUM;
        }

        public String getComposedDailyMeals_CARBOHYDRATES_SUM() {
                return ComposedDailyMeals_CARBOHYDRATES_SUM;
        }

        public String getComposedDailyMeals_SUGAR_SUM() {
                return ComposedDailyMeals_SUGAR_SUM;
        }

        public String getComposedDailyMeals_FATS_SUM() {
                return ComposedDailyMeals_FATS_SUM;
        }

        public String getComposedDailyMeals_saturatedFATS_SUM() {
                return ComposedDailyMeals_saturatedFATS_SUM;
        }

        public String getComposedDailyMeals_PROTEIN_SUM() {
                return ComposedDailyMeals_PROTEIN_SUM;
        }

        public String getFirstProduct_NAME() {
                return FirstProduct_NAME;
        }

        public String getSecondProduct_NAME() {
                return SecondProduct_NAME;
        }

        public String getThirdProduct_NAME() {
                return ThirdProduct_NAME;
        }

        public String getFourthProduct_NAME() {
                return FourthProduct_NAME;
        }

        public String getFifthProduct_NAME() {
                return FifthProduct_NAME;
        }

        public String getFirstProduct_KCAL() {
                return FirstProduct_KCAL;
        }

        public String getSecondProduct_KCAL() {
                return SecondProduct_KCAL;
        }

        public String getThirdProduct_KCAL() {
                return ThirdProduct_KCAL;
        }

        public String getFourthProduct_KCAL() {
                return FourthProduct_KCAL;
        }

        public String getFifthProduct_KCAL() {
                return FifthProduct_KCAL;
        }

        public String getFirstProduct_WEIGHT() {
                return FirstProduct_WEIGHT;
        }

        public String getSecondProduct_WEIGHT() {
                return SecondProduct_WEIGHT;
        }

        public String getThirdProduct_WEIGHT() {
                return ThirdProduct_WEIGHT;
        }

        public String getFourthProduct_WEIGHT() {
                return FourthProduct_WEIGHT;
        }

        public String getFifthProduct_WEIGHT() {
                return FifthProduct_WEIGHT;
        }

        public String getFirstProduct_CARBOHYDRATES() {
                return FirstProduct_CARBOHYDRATES;
        }

        public String getSecondProduct_CARBOHYDRATES() {
                return SecondProduct_CARBOHYDRATES;
        }

        public String getThirdProduct_CARBOHYDRATES() {
                return ThirdProduct_CARBOHYDRATES;
        }

        public String getFourthProduct_CARBOHYDRATES() {
                return FourthProduct_CARBOHYDRATES;
        }

        public String getFifthProduct_CARBOHYDRATES() {
                return FifthProduct_CARBOHYDRATES;
        }

        public String getFirstProduct_SUGAR() {
                return FirstProduct_SUGAR;
        }

        public String getSecondProduct_SUGAR() {
                return SecondProduct_SUGAR;
        }

        public String getThirdProduct_SUGAR() {
                return ThirdProduct_SUGAR;
        }

        public String getFourthProduct_SUGAR() {
                return FourthProduct_SUGAR;
        }

        public String getFifthProduct_SUGAR() {
                return FifthProduct_SUGAR;
        }

        public String getFirstProduct_FATS() {
                return FirstProduct_FATS;
        }

        public String getSecondProduct_FATS() {
                return SecondProduct_FATS;
        }

        public String getThirdProduct_FATS() {
                return ThirdProduct_FATS;
        }

        public String getFourthProduct_FATS() {
                return FourthProduct_FATS;
        }

        public String getFifthProduct_FATS() {
                return FifthProduct_FATS;
        }

        public String getFirstProduct_saturatedFATS() {
                return FirstProduct_saturatedFATS;
        }

        public String getSecondProduct_saturatedFATS() {
                return SecondProduct_saturatedFATS;
        }

        public String getThirdProduct_saturatedFATS() {
                return ThirdProduct_saturatedFATS;
        }

        public String getFourthProduct_saturatedFATS() {
                return FourthProduct_saturatedFATS;
        }

        public String getFifthProduct_saturatedFATS() {
                return FifthProduct_saturatedFATS;
        }

        public String getFirstProduct_PROTEIN() {
                return FirstProduct_PROTEIN;
        }

        public String getSecondProduct_PROTEIN() {
                return SecondProduct_PROTEIN;
        }

        public String getThirdProduct_PROTEIN() {
                return ThirdProduct_PROTEIN;
        }

        public String getFourthProduct_PROTEIN() {
                return FourthProduct_PROTEIN;
        }

        public String getFifthProduct_PROTEIN() {
                return FifthProduct_PROTEIN;
        }

        public String getProductsIncludedFirstProduct() {
                return productsIncludedFirstProduct;
        }

        public String getProductsIncludedSecondProduct() {
                return productsIncludedSecondProduct;
        }

        public String getProductsIncludedThirdProduct() {
                return productsIncludedThirdProduct;
        }

        public String getProductsIncludedFourthProduct() {
                return productsIncludedFourthProduct;
        }

        public String getProductsIncludedFifthProduct() {
                return productsIncludedFifthProduct;
        }
}
