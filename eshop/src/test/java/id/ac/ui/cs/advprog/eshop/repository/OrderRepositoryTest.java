package id.ac.ui.cs.advprog.eshop.repository;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class PaymentServiceImplTest {

    private PaymentService service = new PaymentServiceImpl(); // BELUM ADA IMPLEMENTASI

    @Test
    void testSaveMultiplePayments() {
        List<String> paymentData1 = new ArrayList<>();
        paymentData1.add("Jl. Mawar No. 3");
        paymentData1.add("15000");

        List<String> paymentData2 = new ArrayList<>();
        paymentData2.add("Jl. Melati No. 7");
        paymentData2.add("20000");

        Payment payment1 = new Payment("301", order, "COD", paymentData1);
        Payment payment2 = new Payment("302", order, "COD", paymentData2);

        service.save(payment1);
        service.save(payment2);

        assertEquals(2, service.findAll().size());
    }

    @Test
    void testFindPaymentById() {
        List<String> paymentData = new ArrayList<>();
        paymentData.add("Jl. Mawar No. 3");
        paymentData.add("15000");

        Payment payment = new Payment("303", order, "COD", paymentData);
        service.save(payment);

        Payment foundPayment = service.findById("303");

        assertNotNull(foundPayment);
        assertEquals("303", foundPayment.getId());
    }

    @Test
    void testFindPaymentByIdNotFound() {
        assertNull(service.findById("999"));
    }

    @Test
    void testDeletePayment() {
        List<String> paymentData = new ArrayList<>();
        paymentData.add("Jl. Mawar No. 3");
        paymentData.add("15000");

        Payment payment = new Payment("304", order, "COD", paymentData);
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

        Payment payment = new Payment("305", order, "COD", paymentData);
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
