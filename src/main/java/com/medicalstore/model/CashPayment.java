package com.medicalstore.model;

// INHERITANCE: CashPayment extends Payment
public class CashPayment extends Payment {

    public CashPayment(String paymentId, String orderId, String userId, double amount, String paymentDate) {
        super(paymentId, orderId, userId, amount, "cash", "completed", paymentDate);
    }

    // POLYMORPHISM: Cash payments have no processing fee
    @Override
    public double getProcessingFee() { return 0.0; }

    @Override
    public String getPaymentSummary() {
        return "Cash Payment of Rs." + getAmount() + " (No fee)";
    }
}
