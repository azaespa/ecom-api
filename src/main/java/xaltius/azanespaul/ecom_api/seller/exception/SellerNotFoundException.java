package xaltius.azanespaul.ecom_api.seller.exception;

public class SellerNotFoundException extends RuntimeException {
    public SellerNotFoundException(String id) {
        super("Could not find a user with id " + id + ".");
    }
}
