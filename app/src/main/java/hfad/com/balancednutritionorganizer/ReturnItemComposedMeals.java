package hfad.com.balancednutritionorganizer;

public class ReturnItemComposedMeals {
    private String productName;
    private String productCalories;
    private String productWeight;
    private String productCarbohydrates;
    private String productSugar;
    private String productFats;
    private String productSaturatedFats;
    private String productProtein;
    private String productMacros;
    Boolean isSelected = false;
    private int productPosition;

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public ReturnItemComposedMeals(String productName, String productCalories, String productWeight, String productCarbohydrates, String productSugar,
                                   String productProtein, String productFats, String productSaturatedFats, String productMacros, int productPosition) {
        this.productName = productName;
        this.productCalories = productCalories;
        this.productWeight = productWeight;
        this.productCarbohydrates = productCarbohydrates;
        this.productSugar = productSugar;
        this.productFats = productFats;
        this.productSaturatedFats = productSaturatedFats;
        this.productProtein = productProtein;
        this.productMacros = productMacros;
        this.productPosition = productPosition;
    }

    public int getProductPosition() {
        return productPosition;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductWeight() {
        return productWeight;
    }

    public String getProductCalories() {
        return productCalories;
    }

    public String getProductCarbohydrates() {
        return productCarbohydrates;
    }

    public String getProductSugar() {
        return productSugar;
    }

    public String getProductFats() {
        return productFats;
    }

    public String getProductSaturatedFats() {
        return productSaturatedFats;
    }

    public String getProductProtein() {
        return productProtein;
    }

    public String getProductMacros() {
        return productMacros;
    }
}

