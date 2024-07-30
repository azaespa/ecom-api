package xaltius.azanespaul.ecom_api.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xaltius.azanespaul.ecom_api.seller.Seller;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findProductById(int id);
    Optional<List<Product>> findAllProductsByCategory(String category);
    List<Product> findAllProductsBySellerId(Seller seller);
}
