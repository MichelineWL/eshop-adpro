package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

import java.util.List;

public class PaymentServiceImpl {
    private PaymentRepository repository;

    public PaymentServiceImpl(PaymentRepository repository) {
        this.repository = repository;
    }

    public void save(Payment payment) {
        repository.save(payment);
    }

    public List<Payment> findAll() {
        return repository.findAll();
    }

    public Payment findById(String id) {
        return repository.findAll().stream()
                .filter(payment -> payment.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void delete(String id) {
        repository.findAll().removeIf(payment -> payment.getId().equals(id));
    }

    public void updateStatus(String id, String status) {
        Payment payment = findById(id);
        if (payment == null) {
            throw new IllegalArgumentException("Payment not found");
        }
        payment.setStatus(status);
    }
}
