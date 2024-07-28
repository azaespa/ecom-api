package xaltius.azanespaul.ecom_api.product;

import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xaltius.azanespaul.ecom_api.product.exceptions.ProductCategoryNotFoundException;
import xaltius.azanespaul.ecom_api.product.exceptions.ProductInvalidSellerException;
import xaltius.azanespaul.ecom_api.product.exceptions.ProductNotFoundException;

import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return this.productRepository.save(product);
    }

    public List<Product> findAllProducts() {
        return this.productRepository.findAll();
    }

    public Product updateProduct(@NotNull Product updatedProduct) {
        Product originalProduct = this.productRepository.findProductById(updatedProduct.getId())
                .orElseThrow(() -> new ProductNotFoundException(Integer.toString(updatedProduct.getId())));

        if (originalProduct.getSellerId() != updatedProduct.getSellerId()) throw new ProductInvalidSellerException();

        originalProduct.setName(updatedProduct.getName());
        originalProduct.setDescription(updatedProduct.getDescription());
        originalProduct.setImageUrl(updatedProduct.getImageUrl());
        originalProduct.setCategory(updatedProduct.getCategory());
        originalProduct.setPrice(updatedProduct.getPrice());
        originalProduct.setQuantity(updatedProduct.getQuantity());

        return this.productRepository.save(originalProduct);
    }

    public Product findProductById(int id) {
        return this.productRepository.findProductById(id)
                .orElseThrow(() -> new ProductNotFoundException(Integer.toString(id)));
    }

    public List<Product> findAllProductsByCategory(String category) {
        return this.productRepository.findAllProductsByCategory(category)
                .orElseThrow(() -> new ProductCategoryNotFoundException(category));
    }
}
