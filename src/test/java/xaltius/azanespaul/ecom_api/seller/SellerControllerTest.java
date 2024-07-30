package xaltius.azanespaul.ecom_api.seller;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class SellerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SellerService sellerService;

    List<Seller> sellerList;

    @BeforeEach
    void setUp() {
        Seller s1 = new Seller();
        s1.setId(1);
        s1.setName("Name Test Case");
        s1.setImageUrl("ImageUrl Test Case");
        s1.setMobileNumber("+123456789");

        sellerList = new ArrayList<>();
        sellerList.add(s1);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testSaveSellerSuccess() throws Exception{
        // Given
        Map<String, Object> sellerBody = new HashMap<>();
        sellerBody.put("id", 1);
        sellerBody.put("name", "Name Test Case");
        sellerBody.put("imageUrl", "ImageUrl Test Case");
        sellerBody.put("mobileNumber", "+123456789");

        BDDMockito.given(sellerService.saveSeller(Mockito.any(Seller.class))).willReturn(sellerList.get(0));

        // When and Then
        this.mockMvc.perform(MockMvcRequestBuilders.post("/addseller")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(sellerBody))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Save One Seller Success"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(sellerList.get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value(sellerList.get(0).getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.imageUrl").value(sellerList.get(0).getImageUrl()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.mobileNumber").value(sellerList.get(0).getMobileNumber()));
    }

    @Test
    void getSellerById() {
    }
}