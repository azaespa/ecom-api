package xaltius.azanespaul.ecom_api.seller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SellerServiceTest {

    @Mock
    private SellerRepository sellerRepository;

    @InjectMocks
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
    void testSaveSellerSuccess() {
        // Given
        Seller seller = new Seller();
        seller.setId(1);
        seller.setName("Name Test Case");
        seller.setImageUrl("ImageUrl Test Case");
        seller.setMobileNumber("+123456789");

        BDDMockito.given(sellerRepository.save(Mockito.any(Seller.class))).willReturn(sellerList.get(0));

        // When
        Seller actualSeller = sellerService.saveSeller(seller);

        // Then
        assertThat(actualSeller.getId()).isEqualTo(sellerList.get(0).getId());
        assertThat(actualSeller.getName()).isEqualTo(sellerList.get(0).getName());
        assertThat(actualSeller.getImageUrl()).isEqualTo(sellerList.get(0).getImageUrl());
        assertThat(actualSeller.getMobileNumber()).isEqualTo(sellerList.get(0).getMobileNumber());
        verify(sellerRepository, times(1)).save(seller);
    }

    @Test
    void findSellerById() {
    }
}