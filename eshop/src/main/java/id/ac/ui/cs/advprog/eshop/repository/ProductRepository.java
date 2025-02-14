package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product edit(Product product) {
        for (Product editingProduct : productData) {
            if (editingProduct.getProductId().equals(product.getProductId())) {
                //updating product attributes (name and quantity)
                editingProduct.setProductName(product.getProductName());
                editingProduct.setProductQuantity(product.getProductQuantity());
                return editingProduct;
            }
        }
        return null;
    }

}