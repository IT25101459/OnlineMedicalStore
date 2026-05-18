package com.medicalstore.model;

// INHERITANCE: Medicine extends MedicineBase
public class Medicine extends MedicineBase {

    public Medicine() {}

    public Medicine(String medicineId, String name, String categoryId, String supplierId,
                    double price, int stock, String description, String expiryDate) {
        super(medicineId, name, categoryId, supplierId, price, stock, description, expiryDate);
    }

    // POLYMORPHISM: Implements abstract method
    @Override
    public String getMedicineType() { return "General"; }

    public String toFileString() {
        return medicineId + "," + name + "," + categoryId + "," + supplierId + "," +
               price + "," + stock + "," + description + "," + expiryDate;
    }

    public static Medicine fromFileString(String line) {
        String[] parts = line.split(",", 8);
        if (parts.length < 8) return null;
        return new Medicine(parts[0], parts[1], parts[2], parts[3],
                Double.parseDouble(parts[4]), Integer.parseInt(parts[5]), parts[6], parts[7]);
    }
}
