package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

import java.util.List;

public class Payment {
    private String id;
    private Order order;
    private String method;
    private List<String> paymentData;
    private String status;

    public Payment(String id, Order order, String method, List<String> paymentData) {
        if (method.equals("COD")) {
            validateCOD(paymentData);
        }
        this.id = id;
        this.order = order;
        this.method = method;
        this.paymentData = paymentData;
        this.status = PaymentStatus.PENDING.getValue();
    }

    private void validateCOD(List<String> data) {
        if (data.get(0) == null || data.get(0).isEmpty()) {
            throw new IllegalArgumentException("Address is required for COD");
        }
    }

    public String getMethod() {
        return method;
    }

    public List<String> getPaymentData() {
        return paymentData;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

}
