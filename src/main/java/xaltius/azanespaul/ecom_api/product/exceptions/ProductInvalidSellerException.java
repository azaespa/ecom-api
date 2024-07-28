package xaltius.azanespaul.ecom_api.product.exceptions;

public class ProductInvalidSellerException extends RuntimeException {
    public ProductInvalidSellerException() {
        super("Seller id doesn't match.");
    }
}