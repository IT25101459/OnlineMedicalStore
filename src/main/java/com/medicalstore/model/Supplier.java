package com.medicalstore.model;

// INHERITANCE: Supplier extends Person
public class Supplier extends Person {

    public Supplier() {}

    public Supplier(String supplierId, String name, String email, String phone, String address) {
        super(supplierId, name, email, phone, address);
    }

    // POLYMORPHISM: Implements abstract method from Person
    @Override
    public String getRole() { return "supplier"; }

    // POLYMORPHISM: Supplier-specific display
    @Override
    public String getDisplayInfo() {
        return "🏭 " + name + " | " + phone + " | " + email;
    }

    public String getSupplierId() { return id; }
    public void setSupplierId(String supplierId) { this.id = supplierId; }

    public String toFileString() {
        return id + "," + name + "," + email + "," + phone + "," + address;
    }

    public static Supplier fromFileString(String line) {
        String[] parts = line.split(",", 5);
        if (parts.length < 5) return null;
        return new Supplier(parts[0], parts[1], parts[2], parts[3], parts[4]);
    }
}
