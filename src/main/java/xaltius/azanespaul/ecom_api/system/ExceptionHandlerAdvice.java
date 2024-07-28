package xaltius.azanespaul.ecom_api.system;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xaltius.azanespaul.ecom_api.product.exceptions.ProductCategoryNotFoundException;
import xaltius.azanespaul.ecom_api.product.exceptions.ProductInvalidSellerException;
import xaltius.azanespaul.ecom_api.product.exceptions.ProductNotFoundException;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Result handleProductNotFoundException(ProductNotFoundException ex) {
        return new Result(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), null);
    }

    @ExceptionHandler(ProductInvalidSellerException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Result handleProductInvalidSellerException(ProductInvalidSellerException ex) {
        return new Result(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), null);
    }

    @ExceptionHandler(ProductCategoryNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Result handleProductCategoryNotFoundException(ProductCategoryNotFoundException ex) {
        return new Result(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), null);
    }


}
