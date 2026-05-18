package com.medicalstore.model;

// INHERITANCE: OTCMedicine (Over The Counter) extends Medicine
public class OTCMedicine extends Medicine {

    public OTCMedicine(String medicineId, String name, String categoryId,
                       String supplierId, double price, int stock,
                       String description, String expiryDate) {
        super(medicineId, name, categoryId, supplierId, price, stock, description, expiryDate);
    }

    // POLYMORPHISM: Different type label
    @Override
    public String getMedicineType() { return "Over The Counter"; }

    // POLYMORPHISM: OTC medicines available freely
    @Override
    public String getFormattedPrice() {
        return "Rs. " + String.format("%.2f", price) + " (OTC)";
    }
}
