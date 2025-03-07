package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private List<Payment> payments;
    private List<Order> orders;

    @BeforeEach
    void setUp() {
        this.orders = new ArrayList<>();

        Order order1 = new Order("101", 1700000000L, "Diana Putri");
        Order order2 = new Order("102", 1800000000L, "Andi Wijaya");

        orders.add(order1);
        orders.add(order2);
    }

    @Test
    void testCreatePaymentCODValid() {
        List<String> paymentData = new ArrayList<>();
        paymentData.add("Jl. Mawar No. 3");
        paymentData.add("15000");

        Payment payment = new Payment("201", orders.get(0), "COD", paymentData);

        assertEquals("COD", payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentCODMissingAddress() {
        List<String> paymentData = new ArrayList<>();
        paymentData.add(""); // Missing address
        paymentData.add("15000");

        assertThrows(IllegalArgumentException.class, () ->
                new Payment("202", orders.get(1), "COD", paymentData)
        );
    }

    @Test
    void testSetPaymentStatus() {
        List<String> paymentData = new ArrayList<>();
        paymentData.add("Jl. Mawar No. 3");
        paymentData.add("15000");

        Payment payment = new Payment("203", orders.get(0), "COD", paymentData);
        payment.setStatus("COMPLETED");

        assertEquals("COMPLETED", payment.getStatus());
    }
}
