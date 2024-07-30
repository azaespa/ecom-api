package xaltius.azanespaul.ecom_api.seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xaltius.azanespaul.ecom_api.system.Result;

@RestController
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PostMapping("/addseller")
    public Result saveSeller(@RequestBody Seller seller) {
        Seller savedSeller = this.sellerService.saveSeller(seller);
        return new Result("Save One Seller Success", HttpStatus.OK.value(), savedSeller);
    }

    @GetMapping("/seller/{sellerId}")
    public Result getSellerById(@PathVariable int sellerId) {
        Seller seller = this.sellerService.findSellerById(sellerId);
        return new Result("Find One Seller Success", HttpStatus.OK.value(), seller);
    }
}
