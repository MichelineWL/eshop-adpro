package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void initialize() {
    }

    @Test
    void shouldCreateAndRetrieveProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product retrievedProduct = productIterator.next();
        assertEquals(product.getProductId(), retrievedProduct.getProductId());
        assertEquals(product.getProductName(), retrievedProduct.getProductName());
        assertEquals(product.getProductQuantity(), retrievedProduct.getProductQuantity());
    }

    @Test
    void shouldReturnEmptyIfNoProductsExist() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void shouldRetrieveMultipleProducts() {
        Product firstProduct = new Product();
        firstProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        firstProduct.setProductName("Sampo Cap Bambang");
        firstProduct.setProductQuantity(100);
        productRepository.create(firstProduct);

        Product secondProduct = new Product();
        secondProduct.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        secondProduct.setProductName("Sampo Cap Usep");
        secondProduct.setProductQuantity(50);
        productRepository.create(secondProduct);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product retrievedProduct = productIterator.next();
        assertEquals(firstProduct.getProductId(), retrievedProduct.getProductId());

        retrievedProduct = productIterator.next();
        assertEquals(secondProduct.getProductId(), retrievedProduct.getProductId());

        assertFalse(productIterator.hasNext());
    }

    @Test
    void shouldHandleNegativeQuantityAsZero() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(-99);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product retrievedProduct = productIterator.next();
        assertEquals(product.getProductId(), retrievedProduct.getProductId());
        assertEquals(product.getProductName(), retrievedProduct.getProductName());
        assertEquals(0, retrievedProduct.getProductQuantity());
    }

    @Test
    void shouldFindProductById() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Nasi Duduk");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Cakar Ayam");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Product found1 = productRepository.findProductByID(product1.getProductId());
        Product found2 = productRepository.findProductByID(product2.getProductId());

        assertNotNull(found1);
        assertNotNull(found2);
        assertEquals("Nasi Duduk", found1.getProductName());
        assertEquals("Cakar Ayam", found2.getProductName());
    }

    @Test
    void shouldReturnNullForNonExistentProductId() {
        Product result = productRepository.findProductByID("fffffff");
        assertNull(result);
    }
}
