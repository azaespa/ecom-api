package xaltius.azanespaul.ecom_api.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import xaltius.azanespaul.ecom_api.product.exceptions.ProductCategoryNotFoundException;
import xaltius.azanespaul.ecom_api.product.exceptions.ProductInvalidSellerException;
import xaltius.azanespaul.ecom_api.product.exceptions.ProductNotFoundException;
import xaltius.azanespaul.ecom_api.seller.Seller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    Seller seller;

    @BeforeEach
    void setUp() {
        seller = new Seller();
        seller.setId(1);
        seller.setName("Name Test Case");
        seller.setImageUrl("ImageUrl Test Case");
        seller.setMobileNumber("+123456789");

        Product p1 = new Product();
        p1.setId(1);
        p1.setName("Product Name Test Case 1");
        p1.setCategory("Category Test Case 1");
        p1.setDescription("Description Test Case 1");
        p1.setImageUrl("ImageUrl Test Case 1");
        p1.setQuantity(0);
        p1.setPrice(100);
        p1.setSeller(seller);

        Product p2 = new Product();
        p2.setId(2);
        p2.setName("Product Name Test Case 2");
        p2.setCategory("Category Test Case 2");
        p2.setDescription("Description Test Case 2");
        p2.setImageUrl("ImageUrl Test Case 2");
        p2.setQuantity(0);
        p2.setPrice(200);
        p2.setSeller(seller);

        Product p3 = new Product();
        p3.setId(2);
        p3.setName("Product Name Test Case 3");
        p3.setCategory("Category Test Case 2");
        p3.setDescription("Description Test Case 3");
        p3.setImageUrl("ImageUrl Test Case 3");
        p3.setQuantity(0);
        p3.setPrice(300);
        p3.setSeller(seller);

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
        product.setSeller(seller);

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
        Assertions.assertThat(actualProduct.getSeller().getId()).isEqualTo(productList.get(0).getSeller().getId());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testFindAllProductsSuccess() {
        // Given
        BDDMockito.given(productRepository.findAll()).willReturn(productList);

        // When
        List<Product> actualProductList = productService.findAllProducts();

        // Then
        Assertions.assertThat(actualProductList.size()).isEqualTo(this.productList.size());
        Assertions.assertThat(actualProductList.get(0).getId()).isEqualTo(productList.get(0).getId());
        Assertions.assertThat(actualProductList.get(0).getName()).isEqualTo(productList.get(0).getName());
        Assertions.assertThat(actualProductList.get(0).getCategory()).isEqualTo(productList.get(0).getCategory());
        Assertions.assertThat(actualProductList.get(0).getDescription()).isEqualTo(productList.get(0).getDescription());
        Assertions.assertThat(actualProductList.get(0).getImageUrl()).isEqualTo(productList.get(0).getImageUrl());
        Assertions.assertThat(actualProductList.get(0).getPrice()).isEqualTo(productList.get(0).getPrice());
        Assertions.assertThat(actualProductList.get(0).getSeller().getId()).isEqualTo(productList.get(0).getSeller().getId());
        Assertions.assertThat(actualProductList.get(1).getId()).isEqualTo(productList.get(1).getId());
        Assertions.assertThat(actualProductList.get(1).getName()).isEqualTo(productList.get(1).getName());
        Assertions.assertThat(actualProductList.get(1).getCategory()).isEqualTo(productList.get(1).getCategory());
        Assertions.assertThat(actualProductList.get(1).getDescription()).isEqualTo(productList.get(1).getDescription());
        Assertions.assertThat(actualProductList.get(1).getImageUrl()).isEqualTo(productList.get(1).getImageUrl());
        Assertions.assertThat(actualProductList.get(1).getPrice()).isEqualTo(productList.get(1).getPrice());
        Assertions.assertThat(actualProductList.get(1).getSeller().getId()).isEqualTo(productList.get(1).getSeller().getId());
        Assertions.assertThat(actualProductList.get(2).getId()).isEqualTo(productList.get(2).getId());
        Assertions.assertThat(actualProductList.get(2).getName()).isEqualTo(productList.get(2).getName());
        Assertions.assertThat(actualProductList.get(2).getCategory()).isEqualTo(productList.get(2).getCategory());
        Assertions.assertThat(actualProductList.get(2).getDescription()).isEqualTo(productList.get(2).getDescription());
        Assertions.assertThat(actualProductList.get(2).getImageUrl()).isEqualTo(productList.get(2).getImageUrl());
        Assertions.assertThat(actualProductList.get(2).getPrice()).isEqualTo(productList.get(2).getPrice());
        Assertions.assertThat(actualProductList.get(2).getSeller().getId()).isEqualTo(productList.get(2).getSeller().getId());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testUpdateProductSuccess() {
        // Given
        Product updatedProduct = new Product();
        updatedProduct.setId(1);
        updatedProduct.setName("Product Name Updated Test Case 1");
        updatedProduct.setCategory("Category Updated Test Case 1");
        updatedProduct.setDescription("Description Updated Test Case 1");
        updatedProduct.setImageUrl("ImageUrl Updated Test Case 1");
        updatedProduct.setQuantity(0);
        updatedProduct.setPrice(100);
        updatedProduct.setSeller(seller);

        BDDMockito.given(productRepository.findProductById(updatedProduct.getId())).willReturn(Optional.of(this.productList.get(0)));
        BDDMockito.given(productRepository.save(this.productList.get(0))).willReturn(this.productList.get(0));

        // When
        Product actualUpdatedProduct = this.productService.updateProduct(updatedProduct);

        // Then
        Assertions.assertThat(actualUpdatedProduct.getId()).isEqualTo(updatedProduct.getId());
        Assertions.assertThat(actualUpdatedProduct.getName()).isEqualTo(updatedProduct.getName());
        Assertions.assertThat(actualUpdatedProduct.getCategory()).isEqualTo(updatedProduct.getCategory());
        Assertions.assertThat(actualUpdatedProduct.getDescription()).isEqualTo(updatedProduct.getDescription());
        Assertions.assertThat(actualUpdatedProduct.getImageUrl()).isEqualTo(updatedProduct.getImageUrl());
        Assertions.assertThat(actualUpdatedProduct.getQuantity()).isEqualTo(updatedProduct.getQuantity());
        Assertions.assertThat(actualUpdatedProduct.getPrice()).isEqualTo(updatedProduct.getPrice());
        Assertions.assertThat(actualUpdatedProduct.getSeller().getId()).isEqualTo(updatedProduct.getSeller().getId());
        verify(productRepository, times(1)).findProductById(1);
        verify(productRepository, times(1)).save(this.productList.get(0));
    }

    @Test
    void testUpdateProductNotFound() {
        // Given
        Product updatedProduct = new Product();
        updatedProduct.setId(1);
        updatedProduct.setName("Product Name Updated Test Case 1");
        updatedProduct.setCategory("Category Updated Test Case 1");
        updatedProduct.setDescription("Description Updated Test Case 1");
        updatedProduct.setImageUrl("ImageUrl Updated Test Case 1");
        updatedProduct.setPrice(100);
        updatedProduct.setSeller(seller);

        BDDMockito.given(productRepository.findProductById(updatedProduct.getId())).willReturn(Optional.empty());

        // When
        assertThrows(ProductNotFoundException.class, () -> {
            productService.updateProduct(updatedProduct);
        });

        // Then
        verify(productRepository, times(1)).findProductById(updatedProduct.getId());
    }

    @Test
    void testUpdateProductInvalidSeller() {
        // Given
        Seller s2 = new Seller();
        s2.setId(2);
        s2.setName("Test Case");
        s2.setImageUrl("ImageUrl Test Case");
        s2.setMobileNumber("+123456");

        Product updatedProduct = new Product();
        updatedProduct.setId(1);
        updatedProduct.setName("Product Name Updated Test Case 1");
        updatedProduct.setCategory("Category Updated Test Case 1");
        updatedProduct.setDescription("Description Updated Test Case 1");
        updatedProduct.setImageUrl("ImageUrl Updated Test Case 1");
        updatedProduct.setPrice(100);
        updatedProduct.setSeller(s2);

        BDDMockito.given(productRepository.findProductById(updatedProduct.getId())).willReturn(Optional.of(this.productList.get(0)));

        // When
        assertThrows(ProductInvalidSellerException.class, () -> {
            productService.updateProduct(updatedProduct);
        });

        // Then
        verify(productRepository, times(1)).findProductById(updatedProduct.getId());
    }

    @Test
    void testUpdateProductQuantitySuccess() {
        Product updatedProduct = new Product();
        updatedProduct.setId(1);
        updatedProduct.setQuantity(5);

        updatedProduct.setName(this.productList.get(0).getName());
        updatedProduct.setCategory(this.productList.get(0).getCategory());
        updatedProduct.setDescription(this.productList.get(0).getDescription());
        updatedProduct.setImageUrl(this.productList.get(0).getImageUrl());
        updatedProduct.setPrice(this.productList.get(0).getPrice());
        updatedProduct.setSeller(this.productList.get(0).getSeller());

        BDDMockito.given(productRepository.findProductById(updatedProduct.getId())).willReturn(Optional.of(this.productList.get(0)));
        BDDMockito.given(productRepository.save(this.productList.get(0))).willReturn(this.productList.get(0));

        // When
        Product actualUpdatedProduct = this.productService.updateProductQuantity(updatedProduct.getId(), updatedProduct.getQuantity());

        // Then
        Assertions.assertThat(actualUpdatedProduct.getId()).isEqualTo(updatedProduct.getId());
        Assertions.assertThat(actualUpdatedProduct.getName()).isEqualTo(updatedProduct.getName());
        Assertions.assertThat(actualUpdatedProduct.getCategory()).isEqualTo(updatedProduct.getCategory());
        Assertions.assertThat(actualUpdatedProduct.getDescription()).isEqualTo(updatedProduct.getDescription());
        Assertions.assertThat(actualUpdatedProduct.getImageUrl()).isEqualTo(updatedProduct.getImageUrl());
        Assertions.assertThat(actualUpdatedProduct.getQuantity()).isEqualTo(updatedProduct.getQuantity());
        Assertions.assertThat(actualUpdatedProduct.getPrice()).isEqualTo(updatedProduct.getPrice());
        Assertions.assertThat(actualUpdatedProduct.getSeller().getId()).isEqualTo(updatedProduct.getSeller().getId());
        verify(productRepository, times(1)).findProductById(updatedProduct.getId());
        verify(productRepository, times(1)).save(this.productList.get(0));
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
        Assertions.assertThat(actualProduct.getQuantity()).isEqualTo(productList.get(0).getQuantity());
        Assertions.assertThat(actualProduct.getPrice()).isEqualTo(productList.get(0).getPrice());
        Assertions.assertThat(actualProduct.getSeller().getId()).isEqualTo(productList.get(0).getSeller().getId());
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

    @Test
    void testFindAllProductsByCategorySuccess() {
        // Given
        List<Product> productCategory2List = productList.stream()
                .filter(product -> product.getCategory().equals("Category Test Case 2"))
                        .collect(Collectors.toList());

        BDDMockito.given(this.productRepository.findAllProductsByCategory("Category Test Case 2")).willReturn(Optional.of(productCategory2List));

        // When
        List<Product> actualProductCat2List = this.productService.findAllProductsByCategory("Category Test Case 2");

        // Then
        Assertions.assertThat(actualProductCat2List.get(0).getId()).isEqualTo(productList.get(1).getId());
        Assertions.assertThat(actualProductCat2List.get(0).getName()).isEqualTo(productList.get(1).getName());
        Assertions.assertThat(actualProductCat2List.get(0).getCategory()).isEqualTo(productList.get(1).getCategory());
        Assertions.assertThat(actualProductCat2List.get(0).getDescription()).isEqualTo(productList.get(1).getDescription());
        Assertions.assertThat(actualProductCat2List.get(0).getImageUrl()).isEqualTo(productList.get(1).getImageUrl());
        Assertions.assertThat(actualProductCat2List.get(0).getQuantity()).isEqualTo(productList.get(1).getQuantity());
        Assertions.assertThat(actualProductCat2List.get(0).getPrice()).isEqualTo(productList.get(1).getPrice());
        Assertions.assertThat(actualProductCat2List.get(0).getSeller().getId()).isEqualTo(productList.get(1).getSeller().getId());
        Assertions.assertThat(actualProductCat2List.get(1).getId()).isEqualTo(productList.get(2).getId());
        Assertions.assertThat(actualProductCat2List.get(1).getName()).isEqualTo(productList.get(2).getName());
        Assertions.assertThat(actualProductCat2List.get(1).getCategory()).isEqualTo(productList.get(2).getCategory());
        Assertions.assertThat(actualProductCat2List.get(1).getDescription()).isEqualTo(productList.get(2).getDescription());
        Assertions.assertThat(actualProductCat2List.get(1).getImageUrl()).isEqualTo(productList.get(2).getImageUrl());
        Assertions.assertThat(actualProductCat2List.get(1).getQuantity()).isEqualTo(productList.get(2).getQuantity());
        Assertions.assertThat(actualProductCat2List.get(1).getPrice()).isEqualTo(productList.get(2).getPrice());
        Assertions.assertThat(actualProductCat2List.get(1).getSeller().getId()).isEqualTo(productList.get(2).getSeller().getId());
        verify(productRepository, times(1)).findAllProductsByCategory("Category Test Case 2");
    }

    @Test
    void testFindAllProductsByCategoryNotFound() {
        // Given
        BDDMockito.given(this.productRepository.findAllProductsByCategory("Category Test Case 3")).willReturn(Optional.empty());

        // When
        assertThrows(ProductCategoryNotFoundException.class, () -> {
            this.productService.findAllProductsByCategory("Category Test Case 3");
        });

        // Then
        verify(productRepository, times(1)).findAllProductsByCategory("Category Test Case 3");
    }

    @Test
    void testFindAllProductsBySellerIdSuccess() {
        // Given
        List<Product> productsSellerList = productList.stream()
                .filter(product -> product.getSeller().getId() == 1)
                .toList();

        BDDMockito.given(this.productRepository.findAllProductsBySellerId(Mockito.any(Seller.class))).willReturn(productsSellerList);

        // When
        List<Product> actualProductsSellerList = this.productService.findAllProductsBySellerId(seller);

        // Then
        Assertions.assertThat(actualProductsSellerList.get(0).getId()).isEqualTo(productList.get(0).getId());
        Assertions.assertThat(actualProductsSellerList.get(0).getName()).isEqualTo(productList.get(0).getName());
        Assertions.assertThat(actualProductsSellerList.get(0).getCategory()).isEqualTo(productList.get(0).getCategory());
        Assertions.assertThat(actualProductsSellerList.get(0).getDescription()).isEqualTo(productList.get(0).getDescription());
        Assertions.assertThat(actualProductsSellerList.get(0).getImageUrl()).isEqualTo(productList.get(0).getImageUrl());
        Assertions.assertThat(actualProductsSellerList.get(0).getQuantity()).isEqualTo(productList.get(0).getQuantity());
        Assertions.assertThat(actualProductsSellerList.get(0).getPrice()).isEqualTo(productList.get(0).getPrice());
        Assertions.assertThat(actualProductsSellerList.get(0).getSeller().getId()).isEqualTo(productList.get(0).getSeller().getId());
        Assertions.assertThat(actualProductsSellerList.get(1).getId()).isEqualTo(productList.get(1).getId());
        Assertions.assertThat(actualProductsSellerList.get(1).getName()).isEqualTo(productList.get(1).getName());
        Assertions.assertThat(actualProductsSellerList.get(1).getCategory()).isEqualTo(productList.get(1).getCategory());
        Assertions.assertThat(actualProductsSellerList.get(1).getDescription()).isEqualTo(productList.get(1).getDescription());
        Assertions.assertThat(actualProductsSellerList.get(1).getImageUrl()).isEqualTo(productList.get(1).getImageUrl());
        Assertions.assertThat(actualProductsSellerList.get(1).getQuantity()).isEqualTo(productList.get(1).getQuantity());
        Assertions.assertThat(actualProductsSellerList.get(1).getPrice()).isEqualTo(productList.get(1).getPrice());
        Assertions.assertThat(actualProductsSellerList.get(1).getSeller().getId()).isEqualTo(productList.get(1).getSeller().getId());
        Assertions.assertThat(actualProductsSellerList.get(2).getId()).isEqualTo(productList.get(2).getId());
        Assertions.assertThat(actualProductsSellerList.get(2).getName()).isEqualTo(productList.get(2).getName());
        Assertions.assertThat(actualProductsSellerList.get(2).getCategory()).isEqualTo(productList.get(2).getCategory());
        Assertions.assertThat(actualProductsSellerList.get(2).getDescription()).isEqualTo(productList.get(2).getDescription());
        Assertions.assertThat(actualProductsSellerList.get(2).getImageUrl()).isEqualTo(productList.get(2).getImageUrl());
        Assertions.assertThat(actualProductsSellerList.get(2).getQuantity()).isEqualTo(productList.get(2).getQuantity());
        Assertions.assertThat(actualProductsSellerList.get(2).getPrice()).isEqualTo(productList.get(2).getPrice());
        Assertions.assertThat(actualProductsSellerList.get(2).getSeller().getId()).isEqualTo(productList.get(2).getSeller().getId());
        verify(productRepository, times(1)).findAllProductsBySellerId(seller);
    }

    @Test
    void testDeleteProductByIdSuccess() {
        // Given
        BDDMockito.given(this.productRepository.findProductById(1)).willReturn(Optional.of(this.productList.get(0)));
        BDDMockito.doNothing().when(this.productRepository).deleteById(1);

        // When
        this.productService.deleteProductById(1);

        // Then
        verify(this.productRepository, times(1)).deleteById(1);
    }

}