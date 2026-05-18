package com.medicalstore.model;

// INHERITANCE: RegularUser extends User
public class RegularUser extends User {

    public RegularUser(String userId, String username, String email, String password,
                       String phone, String address) {
        super(userId, username, email, password, phone, address, "user");
    }

    // POLYMORPHISM: Overrides getRole()
    @Override
    public String getRole() {
        return "user";
    }

    // POLYMORPHISM: Overrides getDisplayInfo() differently from AdminUser
    @Override
    public String getDisplayInfo() {
        return "👤 " + getUsername() + " - " + getEmail();
    }

    // Regular user specific method
    public boolean canPlaceOrder() {
        return true;
    }
}
