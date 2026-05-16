package com.medicalstore.service;

import com.medicalstore.model.CartItem;
import com.medicalstore.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartService {

    private static final String FILE = "cart.txt";

    @Autowired
    private FileUtil fileUtil;

    public boolean addItem(CartItem item) {
        item.setCartItemId(fileUtil.generateId("CI"));
        return fileUtil.append(FILE, item.toFileString());
    }

    public List<CartItem> getAll() {
        return fileUtil.readAll(FILE).stream()
                .map(CartItem::fromFileString)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    // Get cart items for a specific user
    public List<CartItem> getCartByUser(String userId) {
        return getAll().stream()
                .filter(c -> c.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public double getCartTotal(String userId) {
        return getCartByUser(userId).stream()
                .mapToDouble(CartItem::getTotal)
                .sum();
    }

    // UPDATE quantity
    public boolean updateQuantity(String cartItemId, int newQty) {
        List<String> lines = getAll().stream()
                .map(c -> {
                    if (c.getCartItemId().equals(cartItemId)) {
                        c.setQuantity(newQty);
                        return c.toFileString();
                    }
                    return c.toFileString();
                })
                .collect(Collectors.toList());
        return fileUtil.writeAll(FILE, lines);
    }

    // DELETE single item
    public boolean removeItem(String cartItemId) {
        List<String> lines = getAll().stream()
                .filter(c -> !c.getCartItemId().equals(cartItemId))
                .map(CartItem::toFileString)
                .collect(Collectors.toList());
        return fileUtil.writeAll(FILE, lines);
    }

    // Clear entire cart for a user (after order placed)
    public boolean clearCart(String userId) {
        List<String> lines = getAll().stream()
                .filter(c -> !c.getUserId().equals(userId))
                .map(CartItem::toFileString)
                .collect(Collectors.toList());
        return fileUtil.writeAll(FILE, lines);
    }
}
