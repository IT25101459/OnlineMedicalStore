package com.medicalstore.model;

// INHERITANCE: PrescriptionMedicine extends Medicine
public class PrescriptionMedicine extends Medicine {

    public PrescriptionMedicine(String medicineId, String name, String categoryId,
                                 String supplierId, double price, int stock,
                                 String description, String expiryDate) {
        super(medicineId, name, categoryId, supplierId, price, stock, description, expiryDate);
    }

    // POLYMORPHISM: Different type label
    @Override
    public String getMedicineType() { return "Prescription Required"; }

    // POLYMORPHISM: Different availability — needs stock AND prescription note
    @Override
    public String getFormattedPrice() {
        return "Rs. " + String.format("%.2f", price) + " (Rx)";
    }
}
