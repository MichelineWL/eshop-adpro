package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {
    private final List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        // Pastikan ID selalu dihasilkan jika belum ada
        if (product.getProductId() == null || product.getProductId().isEmpty()) {
            product.setProductId(UUID.randomUUID().toString());
        }
        if (product.getProductQuantity() < 0) {
            product.setProductQuantity(0);
        }
        productData.add(product);
        return product;
    }

    public List<Product> findAll() {
        return new ArrayList<>(productData); // Return copy untuk mencegah modifikasi langsung
    }


    public Product findProductByID(String productId) {
        return productData.stream()
                .filter(product -> product.getProductId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    public Product edit(Product product) {
        for (int i = 0; i < productData.size(); i++) {
            if (productData.get(i).getProductId().equals(product.getProductId())) {
                productData.set(i, product); // Ganti produk dengan yang baru
                return product;
            }
        }
        return null;
    }

    public void deleteProductById(String productId) {
        productData.removeIf(product -> product.getProductId().equals(productId));
    }

}
