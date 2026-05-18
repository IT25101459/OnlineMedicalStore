package com.medicalstore.model;

// ABSTRACTION: Abstract-style base — Payment has abstract processing behavior
public class Payment {
    private String paymentId;
    private String orderId;
    private String userId;
    private double amount;
    private String method; // "cash", "card", "online"
    private String status;
    private String paymentDate;

    public Payment() {}

    public Payment(String paymentId, String orderId, String userId, double amount,
                   String method, String status, String paymentDate) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.userId = userId;
        this.amount = amount;
        this.method = method;
        this.status = status;
        this.paymentDate = paymentDate;
    }

    // POLYMORPHISM: Can be overridden in subclasses
    public String getPaymentSummary() {
        return "Payment of Rs." + amount + " via " + method;
    }

    // POLYMORPHISM: Overridable processing fee
    public double getProcessingFee() { return 0.0; }

    public double getFinalAmount() { return amount + getProcessingFee(); }

    public String toFileString() {
        return paymentId + "," + orderId + "," + userId + "," +
               amount + "," + method + "," + status + "," + paymentDate;
    }

    public static Payment fromFileString(String line) {
        String[] parts = line.split(",", 7);
        if (parts.length < 7) return null;
        return new Payment(parts[0], parts[1], parts[2],
                Double.parseDouble(parts[3]), parts[4], parts[5], parts[6]);
    }

    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getPaymentDate() { return paymentDate; }
    public void setPaymentDate(String paymentDate) { this.paymentDate = paymentDate; }
}
