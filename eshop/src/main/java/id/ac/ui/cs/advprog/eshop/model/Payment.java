package id.ac.ui.cs.advprog.eshop.model;

import java.util.List;

public class Payment {
    private String id;
    private Order order;
    private String method;
    private List<String> paymentData;
    private String status;

    public Payment(String id, Order order, String method, List<String> paymentData) {
        this.id = id;
        this.order = order;
        this.method = method;
        this.paymentData = paymentData;
        this.status = "PENDING";
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
}
