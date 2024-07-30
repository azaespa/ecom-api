package xaltius.azanespaul.ecom_api.seller;

import jakarta.persistence.*;
import lombok.*;
import xaltius.azanespaul.ecom_api.product.Product;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seller implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String imageUrl;
    private String mobileNumber;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "seller")
    private List<Product> productList;
}
