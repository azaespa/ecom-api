package xaltius.azanespaul.ecom_api.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xaltius.azanespaul.ecom_api.system.Result;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public Result saveProduct(@RequestBody Product product) {
        Product savedProduct = this.productService.saveProduct(product);
        return new Result("Save One Product Success", HttpStatus.OK.value(), savedProduct);
    }

    @GetMapping("/product/{id}")
    public Result getProductById(@PathVariable int id) {
        Product product = this.productService.findProductById(id);
        return new Result("Find One Product Success", HttpStatus.OK.value(), product);
    }
}
