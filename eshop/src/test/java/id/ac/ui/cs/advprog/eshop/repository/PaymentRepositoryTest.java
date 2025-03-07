package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {
    private PaymentRepository repository;
    private Order order;

    @BeforeEach
    void setUp() {
        repository = new PaymentRepository();
        order = new Order("101", 1700000000L, "Diana Putri");
    }

    @Test
    void testSavePayment() {
        List<String> paymentData = new ArrayList<>();
        paymentData.add("Jl. Mawar No. 3");
        paymentData.add("15000");

        Payment payment = new Payment("201", order, "COD", paymentData);
        repository.save(payment);

        assertEquals(1, repository.findAll().size());
    }
}
