package com.medicalstore.model;

// INHERITANCE: LocalSupplier extends Supplier
public class LocalSupplier extends Supplier {

    public LocalSupplier(String supplierId, String name, String email, String phone, String address) {
        super(supplierId, name, email, phone, address);
    }

    @Override
    public String getRole() { return "local-supplier"; }

    @Override
    public String getDisplayInfo() {
        return "🏠 LOCAL: " + name + " | " + phone;
    }

    public String getDeliveryNote() {
        return "Same-day delivery available";
    }
}
