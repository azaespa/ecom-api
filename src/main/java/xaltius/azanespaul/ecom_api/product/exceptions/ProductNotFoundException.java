package xaltius.azanespaul.ecom_api.product.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String id) {
        super("Could not find a product with id " + id + ".");
    }
}
