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
            if (Integer.parseInt(editingProduct.getProductId()) == Integer.parseInt(product.getProductId())) {
                // Mengupdate atribut produk (nama dan jumlah)
                editingProduct.setProductName(product.getProductName());
                editingProduct.setProductQuantity(product.getProductQuantity());
                return editingProduct;
            }
        }
        return null;

    }

    public void delete(Product deleteProduct){
        productData.remove(deleteProduct);
    }

    public void deleteProductById(String deleteId){
        delete(findProductByID(deleteId));
    }
  
    public Product findProductByID(String productId) {
        for(Product currentProduct : productData){
            String currentProductID = currentProduct.getProductId();
            if(currentProductID.equals(productId)){
                return currentProduct;

        return null;
    }

}