package xaltius.azanespaul.ecom_api.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import xaltius.azanespaul.ecom_api.product.exceptions.ProductNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServicesTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    List<Product> productList;

    @BeforeEach
    void setUp() {
        Product p1 = new Product();
        p1.setId(1);
        p1.setName("Product Name Test Case 1");
        p1.setCategory("Category Test Case 1");
        p1.setDescription("Description Test Case 1");
        p1.setImageUrl("ImageUrl Test Case 1");
        p1.setPrice(100);
        p1.setSellerId(1);

        Product p2 = new Product();
        p2.setId(2);
        p2.setName("Product Name Test Case 2");
        p2.setCategory("Category Test Case 2");
        p2.setDescription("Description Test Case 2");
        p2.setImageUrl("ImageUrl Test Case 2");
        p2.setPrice(200);
        p2.setSellerId(1);

        Product p3 = new Product();
        p3.setId(2);
        p3.setName("Product Name Test Case 3");
        p3.setCategory("Category Test Case 3");
        p3.setDescription("Description Test Case 3");
        p3.setImageUrl("ImageUrl Test Case 3");
        p3.setPrice(300);
        p3.setSellerId(1);

        productList = new ArrayList<>();
        productList.add(p1);
        productList.add(p2);
        productList.add(p3);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testSaveProductSuccess() {
        // Given
        Product product = new Product();
        product.setId(1);
        product.setName("Name Test Case");
        product.setCategory("Category Test Case");
        product.setDescription("Description Test Case");
        product.setImageUrl("ImageUrl Test Case");
        product.setPrice(100);
        product.setSellerId(1);

        BDDMockito.given(productRepository.save(product)).willReturn(productList.get(0));

        // When
        Product actualProduct = productService.saveProduct(product);

        // Then
        Assertions.assertThat(actualProduct.getId()).isEqualTo(productList.get(0).getId());
        Assertions.assertThat(actualProduct.getName()).isEqualTo(productList.get(0).getName());
        Assertions.assertThat(actualProduct.getCategory()).isEqualTo(productList.get(0).getCategory());
        Assertions.assertThat(actualProduct.getDescription()).isEqualTo(productList.get(0).getDescription());
        Assertions.assertThat(actualProduct.getImageUrl()).isEqualTo(productList.get(0).getImageUrl());
        Assertions.assertThat(actualProduct.getPrice()).isEqualTo(productList.get(0).getPrice());
        Assertions.assertThat(actualProduct.getSellerId()).isEqualTo(productList.get(0).getSellerId());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testFindProductByIdSuccess() {
        // Given
        BDDMockito.given(productRepository.findProductById(1)).willReturn(Optional.of(productList.get(0)));

        // When
        Product actualProduct = productService.findProductById(1);

        // Then
        Assertions.assertThat(actualProduct.getId()).isEqualTo(productList.get(0).getId());
        Assertions.assertThat(actualProduct.getName()).isEqualTo(productList.get(0).getName());
        Assertions.assertThat(actualProduct.getCategory()).isEqualTo(productList.get(0).getCategory());
        Assertions.assertThat(actualProduct.getDescription()).isEqualTo(productList.get(0).getDescription());
        Assertions.assertThat(actualProduct.getImageUrl()).isEqualTo(productList.get(0).getImageUrl());
        Assertions.assertThat(actualProduct.getPrice()).isEqualTo(productList.get(0).getPrice());
        Assertions.assertThat(actualProduct.getSellerId()).isEqualTo(productList.get(0).getSellerId());
        verify(productRepository, times(1)).findProductById(1);
    }

    @Test
    void testFindProductByIdNotFound() {
        // Given
        BDDMockito.given(productRepository.findProductById(1)).willReturn(Optional.empty());

        // When
        assertThrows(ProductNotFoundException.class, () -> {
            productService.findProductById(1);
        });

        // Then
        verify(productRepository, times(1)).findProductById(1);
    }
}