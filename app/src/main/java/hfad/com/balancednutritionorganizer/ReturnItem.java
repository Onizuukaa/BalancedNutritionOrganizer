package hfad.com.balancednutritionorganizer;

public class ReturnItem {
    private String productName;
    private String productImage;
    private String productCalories;
    private String productCarbohydrates;
    private String productSugar;
    private String productFats;
    private String productSaturatedFats;
    private String productProtein;

    public ReturnItem(String productName, String productImage, String productCalories, String productCarbohydrates, String productSugar,
                      String productFats, String productSaturatedFats, String productProtein) {
        this.productName = productName;
        this.productImage = productImage;
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

    public String getProductImage() {
        return productImage;
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
