package com.medicalstore.model;

// INHERITANCE: AdminUser extends User (which extends Person) — multi-level inheritance
public class AdminUser extends User {

    public AdminUser(String userId, String username, String email, String password,
                     String phone, String address) {
        super(userId, username, email, password, phone, address, "admin");
    }

    // POLYMORPHISM: Overrides getRole() with admin-specific behavior
    @Override
    public String getRole() {
        return "admin";
    }

    // POLYMORPHISM: Overrides getDisplayInfo() differently from RegularUser
    @Override
    public String getDisplayInfo() {
        return "⭐ ADMIN: " + getUsername() + " - " + getEmail();
    }

    // Admin-specific method — only admins have this
    public boolean canManageUsers() {
        return true;
    }
}
