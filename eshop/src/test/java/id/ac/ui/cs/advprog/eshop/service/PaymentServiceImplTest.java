package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceImplTest {
    private PaymentServiceImpl service;
    private PaymentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new PaymentRepository();
        service = new PaymentServiceImpl(repository);
    }

    @Test
    void testSaveMultiplePayments() {
        List<String> paymentData1 = new ArrayList<>();
        paymentData1.add("Jl. Mawar No. 3");
        paymentData1.add("15000");

        List<String> paymentData2 = new ArrayList<>();
        paymentData2.add("Jl. Melati No. 7");
        paymentData2.add("20000");

        Payment payment1 = new Payment("301", null, "COD", paymentData1);
        Payment payment2 = new Payment("302", null, "COD", paymentData2);

        service.save(payment1);
        service.save(payment2);

        assertEquals(2, service.findAll().size());
    }

    @Test
    void testFindPaymentById() {
        List<String> paymentData = new ArrayList<>();
        paymentData.add("Jl. Mawar No. 3");
        paymentData.add("15000");

        Payment payment = new Payment("303", null, "COD", paymentData);
        service.save(payment);

        Payment foundPayment = service.findById("303");

        assertNotNull(foundPayment);
        assertEquals("303", foundPayment.getId());
    }

    @Test
    void testFindPaymentByIdNotFound() {
        assertNull(service.findById("999")); // Should return null if not found
    }

    @Test
    void testDeletePayment() {
        List<String> paymentData = new ArrayList<>();
        paymentData.add("Jl. Mawar No. 3");
        paymentData.add("15000");

        Payment payment = new Payment("304", null, "COD", paymentData);
        service.save(payment);
        assertEquals(1, service.findAll().size());

        service.delete("304");

        assertEquals(0, service.findAll().size());
        assertNull(service.findById("304"));
    }

    @Test
    void testUpdatePaymentStatus() {
        List<String> paymentData = new ArrayList<>();
        paymentData.add("Jl. Mawar No. 3");
        paymentData.add("15000");

        Payment payment = new Payment("305", null, "COD", paymentData);
        service.save(payment);

        service.updateStatus("305", "COMPLETED");

        Payment updatedPayment = service.findById("305");

        assertEquals("COMPLETED", updatedPayment.getStatus());
    }

    @Test
    void testUpdatePaymentStatusInvalidId() {
        assertThrows(IllegalArgumentException.class, () -> service.updateStatus("999", "COMPLETED"));
    }
}
