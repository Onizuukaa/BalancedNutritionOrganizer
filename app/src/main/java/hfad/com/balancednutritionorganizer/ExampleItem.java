package hfad.com.balancednutritionorganizer;

public class ExampleItem {
    private String productName;
    private String productCalories;
    private String productCarbohydrates;
    private String productSugar;
    private String productFats;
    private String productSaturatedFats;
    private String productProtein;

    public ExampleItem(String productName, String productCalories, String productCarbohydrates, String productSugar,
                       String productFats, String productSaturatedFats, String productProtein) {
        this.productName = productName;
        this.productCalories = productCalories;
        this.productCarbohydrates = productCarbohydrates;
        this.productSugar = productSugar;
        this.productFats = productFats;
        this.productSaturatedFats = productSaturatedFats;
        this.productProtein = productProtein;
    }

    public String getProductName() {
        return productName;
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
}
