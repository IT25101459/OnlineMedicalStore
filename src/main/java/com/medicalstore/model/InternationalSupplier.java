package com.medicalstore.model;

// INHERITANCE: InternationalSupplier extends Supplier
public class InternationalSupplier extends Supplier {

    public InternationalSupplier(String supplierId, String name, String email, String phone, String address) {
        super(supplierId, name, email, phone, address);
    }

    @Override
    public String getRole() { return "international-supplier"; }

    @Override
    public String getDisplayInfo() {
        return "🌍 INTERNATIONAL: " + name + " | " + email;
    }

    public String getDeliveryNote() {
        return "7-14 days international shipping";
    }
}
