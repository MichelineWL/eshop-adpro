package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String productId;
    private String productName;
    private int productQuantity;

    // Generate UUID jika objek dibuat tanpa ID
    public Product(String productName, int productQuantity) {
        this.productId = UUID.randomUUID().toString();
        this.productName = productName;
        this.productQuantity = productQuantity;
    }
}
