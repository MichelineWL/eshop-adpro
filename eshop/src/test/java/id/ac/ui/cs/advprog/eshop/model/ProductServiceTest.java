package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("12345");
        product.setProductName("Laptop");
        product.setProductQuantity(10);
    }

    @Test
    void testEditProduct_Success() {
        when(productRepository.findProductByID("12345")).thenReturn(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("12345");
        updatedProduct.setProductName("Gaming Laptop");
        updatedProduct.setProductQuantity(5);

        productService.edit(updatedProduct);

        assertEquals("Gaming Laptop", product.getProductName());
        assertEquals(5, product.getProductQuantity());
        verify(productRepository, times(1)).edit(updatedProduct);
    }

    @Test
    void testEditProduct_ProductNotFound() {
        when(productRepository.findProductByID("99999")).thenReturn(null);

        Product newProduct = new Product();
        newProduct.setProductId("99999");
        newProduct.setProductName("Tablet");
        newProduct.setProductQuantity(3);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            productService.edit(newProduct);
        });

        assertEquals("Product not found", exception.getMessage());
    }

    @Test
    void testDeleteProduct_Success() {
        when(productRepository.findProductByID("12345")).thenReturn(product);

        productService.deleteProductById("12345");

        verify(productRepository, times(1)).deleteProductById("12345");
    }

    @Test
    void testDeleteProduct_ProductNotFound() {
        when(productRepository.findProductByID("99999")).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            productService.deleteProductById("99999");
        });

        assertEquals("Product not found", exception.getMessage());
    }
}
