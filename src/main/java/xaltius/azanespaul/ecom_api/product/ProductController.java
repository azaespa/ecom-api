package xaltius.azanespaul.ecom_api.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xaltius.azanespaul.ecom_api.system.Result;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public Result saveProduct(@RequestBody Product product) {
        Product savedProduct = this.productService.saveProduct(product);
        return new Result("Save One Product Success", HttpStatus.OK.value(), savedProduct);
    }

    @GetMapping("/products")
    public Result getAllProducts(){
        List<Product> productList = this.productService.findAllProducts();
        return new Result("Find All Products Success", HttpStatus.OK.value(), productList);
    }

    @PutMapping("/products")
    public Result updateProduct(@RequestBody Product product) {
        Product updatedProduct = this.productService.updateProduct(product);
        return new Result("Update One Product Success", HttpStatus.OK.value(), updatedProduct);
    }

    @PutMapping("/products/{id}")
    public Result updateProductQuantity(@PathVariable int id, @RequestBody Product product) {
        Product updatedProduct = this.productService.updateProductQuantity(id, product.getQuantity());
        return new Result("Update One Product's Quantity Success", HttpStatus.OK.value(), updatedProduct);
    }

    @GetMapping("/product/{id}")
    public Result getProductById(@PathVariable int id) {
        Product product = this.productService.findProductById(id);
        return new Result("Find One Product Success", HttpStatus.OK.value(), product);
    }

    @GetMapping("/products/{category}")
    public Result getAllProductsByCategory(@PathVariable String category) {
        List<Product> productListByCategory = this.productService.findAllProductsByCategory(category);
        return new Result("Find All Products By Category Success", HttpStatus.OK.value(), productListByCategory);
    }

    @GetMapping("/products/seller/{id}")
    public Result getAllProductsSellerId(@PathVariable int id) {
        List<Product> productListBySellerId = this.productService.findAllProductsBySellerId(id);
        return new Result("Find All Products By Seller Id Success", HttpStatus.OK.value(), productListBySellerId);
    }

    @DeleteMapping("/product/{id}")
    public Result deleteProductById(@PathVariable int id) {
        this.productService.deleteProductById(1);
        return new Result("Delete One Product Success", HttpStatus.OK.value(), null);
    }

}
