package com.medicalstore.model;

// INHERITANCE: CardPayment extends Payment
public class CardPayment extends Payment {

    public CardPayment(String paymentId, String orderId, String userId, double amount, String paymentDate) {
        super(paymentId, orderId, userId, amount, "card", "completed", paymentDate);
    }

    // POLYMORPHISM: Card payments have a 1.5% processing fee
    @Override
    public double getProcessingFee() { return getAmount() * 0.015; }

    @Override
    public String getPaymentSummary() {
        return "Card Payment of Rs." + getAmount() + " + Rs." + String.format("%.2f", getProcessingFee()) + " fee";
    }
}
