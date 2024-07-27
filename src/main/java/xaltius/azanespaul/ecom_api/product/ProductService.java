package xaltius.azanespaul.ecom_api.product;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xaltius.azanespaul.ecom_api.product.exceptions.ProductNotFoundException;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return this.productRepository.save(product);
    }

    public Product findProductById(int id) {
        return this.productRepository.findProductById(id)
                .orElseThrow(() -> new ProductNotFoundException(Integer.toString(id)));
    }
}
