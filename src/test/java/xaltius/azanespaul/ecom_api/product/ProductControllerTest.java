package xaltius.azanespaul.ecom_api.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import xaltius.azanespaul.ecom_api.product.exceptions.ProductCategoryNotFoundException;
import xaltius.azanespaul.ecom_api.product.exceptions.ProductInvalidSellerException;
import xaltius.azanespaul.ecom_api.product.exceptions.ProductNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
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
        p1.setQuantity(0);
        p1.setPrice(100);
        p1.setSellerId(1);

        Product p2 = new Product();
        p2.setId(2);
        p2.setName("Product Name Test Case 2");
        p2.setCategory("Category Test Case 2");
        p2.setDescription("Description Test Case 2");
        p2.setImageUrl("ImageUrl Test Case 2");
        p2.setQuantity(0);
        p2.setPrice(200);
        p2.setSellerId(1);

        Product p3 = new Product();
        p3.setId(2);
        p3.setName("Product Name Test Case 3");
        p3.setCategory("Category Test Case 2");
        p3.setDescription("Description Test Case 3");
        p3.setImageUrl("ImageUrl Test Case 3");
        p3.setQuantity(0);
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
    void testSaveProductSuccess() throws Exception {
        // Given
        Map<String, Object> productBody = new HashMap<>();
        productBody.put("id", 1);
        productBody.put("name", "Name Test Case");
        productBody.put("category", "Category Test Case");
        productBody.put("description", "Description Test Case");
        productBody.put("imageUrl", "ImageUrl Test Case");
        productBody.put("price", 100);
        productBody.put("sellerId", 1);

        BDDMockito.given(this.productService.saveProduct(Mockito.any(Product.class))).willReturn(productList.get(0));

        // When and Then
        this.mockMvc.perform(MockMvcRequestBuilders.post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(productBody))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Save One Product Success"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(productList.get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value(productList.get(0).getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.category").value(productList.get(0).getCategory()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.description").value(productList.get(0).getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.imageUrl").value(productList.get(0).getImageUrl()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.price").value(productList.get(0).getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.sellerId").value(productList.get(0).getSellerId()));
    }

    @Test
    void testGetAllProductsSuccess() throws Exception {
        // Given
        BDDMockito.given(productService.findAllProducts()).willReturn(this.productList);

        // When and Then
        this.mockMvc.perform(MockMvcRequestBuilders.get("/products")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Find All Products Success"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id").value(productList.get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].name").value(productList.get(0).getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].category").value(productList.get(0).getCategory()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].description").value(productList.get(0).getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].imageUrl").value(productList.get(0).getImageUrl()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].quantity").value(productList.get(0).getQuantity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].price").value(productList.get(0).getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].sellerId").value(productList.get(0).getSellerId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].id").value(productList.get(1).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].name").value(productList.get(1).getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].category").value(productList.get(1).getCategory()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].description").value(productList.get(1).getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].imageUrl").value(productList.get(1).getImageUrl()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].quantity").value(productList.get(1).getQuantity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].price").value(productList.get(1).getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].sellerId").value(productList.get(1).getSellerId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[2].id").value(productList.get(2).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[2].name").value(productList.get(2).getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[2].category").value(productList.get(2).getCategory()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[2].description").value(productList.get(2).getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[2].imageUrl").value(productList.get(2).getImageUrl()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].quantity").value(productList.get(2).getQuantity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[2].price").value(productList.get(2).getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[2].sellerId").value(productList.get(2).getSellerId()));
    }

    @Test
    void testUpdateProductSuccess() throws Exception {
        // Given
        Map<String, Object> productBody = new HashMap<>();
        productBody.put("id", 1);
        productBody.put("name", "Product Name Updated Test Case 1");
        productBody.put("category", "Category Updated Test Case 1");
        productBody.put("description", "Description Updated Test Case 1");
        productBody.put("imageUrl", "ImageUrl Updated Test Case 1");
        productBody.put("quantity", 0);
        productBody.put("price", 100);
        productBody.put("sellerId", 1);

        Product updatedProduct = new Product();
        updatedProduct.setId(1);
        updatedProduct.setName("Product Name Updated Test Case 1");
        updatedProduct.setCategory("Category Updated Test Case 1");
        updatedProduct.setDescription("Description Updated Test Case 1");
        updatedProduct.setImageUrl("ImageUrl Updated Test Case 1");
        updatedProduct.setQuantity(0);
        updatedProduct.setPrice(100);
        updatedProduct.setSellerId(1);

        BDDMockito.given(this.productService.updateProduct(Mockito.any(Product.class))).willReturn(updatedProduct);

        // When and Then
        this.mockMvc.perform(MockMvcRequestBuilders.put("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(productBody))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Update One Product Success"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(updatedProduct.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value(updatedProduct.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.category").value(updatedProduct.getCategory()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.description").value(updatedProduct.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.imageUrl").value(updatedProduct.getImageUrl()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.quantity").value(updatedProduct.getQuantity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.price").value(updatedProduct.getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.sellerId").value(updatedProduct.getSellerId()));
    }

    @Test
    void testUpdateProductNotFound() throws Exception {
        // Given
        Map<String, Object> productBody = new HashMap<>();
        productBody.put("id", 1);
        productBody.put("name", "Product Name Updated Test Case 1");
        productBody.put("category", "Category Updated Test Case 1");
        productBody.put("description", "Description Updated Test Case 1");
        productBody.put("imageUrl", "ImageUrl Updated Test Case 1");
        productBody.put("price", 100);
        productBody.put("sellerId", 1);

        BDDMockito.given(this.productService.updateProduct(Mockito.any(Product.class))).willThrow(new ProductNotFoundException("1"));

        // When and Then
        this.mockMvc.perform(MockMvcRequestBuilders.put("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(productBody))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Could not find a product with id 1."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(400))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty());
    }

    @Test
    void testUpdateProductInvalidSeller() throws Exception {
        // Given
        Map<String, Object> productBody = new HashMap<>();
        productBody.put("id", 1);
        productBody.put("name", "Product Name Updated Test Case 1");
        productBody.put("category", "Category Updated Test Case 1");
        productBody.put("description", "Description Updated Test Case 1");
        productBody.put("imageUrl", "ImageUrl Updated Test Case 1");
        productBody.put("price", 100);
        productBody.put("sellerId", 2);

        BDDMockito.given(this.productService.updateProduct(Mockito.any(Product.class))).willThrow(new ProductInvalidSellerException());

        // When and Then
        this.mockMvc.perform(MockMvcRequestBuilders.put("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(productBody))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Seller id doesn't match."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(400))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty());
    }

    @Test
    void testGetProductByIdSuccess() throws Exception {
        //Given
        BDDMockito.given(this.productService.findProductById(1)).willReturn(productList.get(0));

        // When and Then
        this.mockMvc.perform(MockMvcRequestBuilders.get("/product/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Find One Product Success"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(productList.get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value(productList.get(0).getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.category").value(productList.get(0).getCategory()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.description").value(productList.get(0).getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.imageUrl").value(productList.get(0).getImageUrl()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.quantity").value(productList.get(0).getQuantity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.price").value(productList.get(0).getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.sellerId").value(productList.get(0).getSellerId()));
    }

    @Test
    void testGetProductByIdNotFound() throws Exception {
        //Given
        BDDMockito.given(this.productService.findProductById(1)).willThrow(new ProductNotFoundException("1"));

        // When and Then
        this.mockMvc.perform(MockMvcRequestBuilders.get("/product/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Could not find a product with id 1."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(400))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty());
    }

    @Test
    void testGetAllProductsByCategorySuccess() throws Exception {
        // Given
        List<Product> productCategory2List = productList.stream()
                .filter(product -> product.getCategory().equals("Category Test Case 2"))
                .collect(Collectors.toList());

        BDDMockito.given(productService.findAllProductsByCategory("Category Test Case 2")).willReturn(productCategory2List);

        // When and Then
        this.mockMvc.perform(MockMvcRequestBuilders.get("/products/Category Test Case 2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Find All Products By Category Success"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id").value(productList.get(1).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].name").value(productList.get(1).getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].category").value(productList.get(1).getCategory()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].description").value(productList.get(1).getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].imageUrl").value(productList.get(1).getImageUrl()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].quantity").value(productList.get(1).getQuantity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].price").value(productList.get(1).getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].sellerId").value(productList.get(1).getSellerId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].id").value(productList.get(2).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].name").value(productList.get(2).getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].category").value(productList.get(2).getCategory()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].description").value(productList.get(2).getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].imageUrl").value(productList.get(2).getImageUrl()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].quantity").value(productList.get(2).getQuantity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].price").value(productList.get(2).getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].sellerId").value(productList.get(2).getSellerId()));
    }

    @Test
    void testGetAllProductsByCategoryNotFound() throws Exception {
        // Given
        BDDMockito.given(productService.findAllProductsByCategory("Category Test Case 2")).willThrow(new ProductCategoryNotFoundException("Category Test Case 2"));

        // When and Then
        this.mockMvc.perform(MockMvcRequestBuilders.get("/products/Category Test Case 2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Could not find the category: Category Test Case 2."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(400))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty());
    }
}