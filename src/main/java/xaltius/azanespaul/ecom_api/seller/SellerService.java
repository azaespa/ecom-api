package xaltius.azanespaul.ecom_api.seller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xaltius.azanespaul.ecom_api.seller.exception.SellerNotFoundException;

@Service
@Transactional
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    public Seller saveSeller(Seller seller) {
        return this.sellerRepository.save(seller);
    }

    public Seller findSellerById(int id) {
        return this.sellerRepository.findSellerById(id)
                .orElseThrow(() -> new SellerNotFoundException(Integer.toString(id)));
    }
}

