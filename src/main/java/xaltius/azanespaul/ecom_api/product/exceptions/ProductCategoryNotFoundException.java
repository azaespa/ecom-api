package xaltius.azanespaul.ecom_api.product.exceptions;

public class ProductCategoryNotFoundException extends RuntimeException {
    public ProductCategoryNotFoundException(String category) {
        super("Could not find the category: " + category + ".");
    }
}
