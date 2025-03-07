package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Payment;

import java.util.List;

public interface PaymentService {
    void save(Payment payment);
    Payment findById(String id);
    List<Payment> findAll();
    void delete(String id);
    void updateStatus(String id, String status);
}
