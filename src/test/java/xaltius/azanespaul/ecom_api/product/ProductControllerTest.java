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
import xaltius.azanespaul.ecom_api.product.exceptions.ProductNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}