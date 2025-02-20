package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {
    private final Map<String, Product> productData = new HashMap<>();

    public Product create(Product product) {
        // Pastikan ID selalu dihasilkan jika belum ada
        if (product.getProductId() == null || product.getProductId().isEmpty()) {
            product.setProductId(UUID.randomUUID().toString());
        }
        productData.put(product.getProductId(), product);
        return product;
    }

    public List<Product> findAll() {
        return new ArrayList<>(productData.values());
    }

    public Product findById(String productId) {
        return productData.get(productId);
    }

    public Product edit(Product product) {
        if (productData.containsKey(product.getProductId())) {
            productData.put(product.getProductId(), product);
            return product;
        }
        return null;
    }

    public void deleteProductById(String productId) {
        productData.remove(productId);
    }
}
