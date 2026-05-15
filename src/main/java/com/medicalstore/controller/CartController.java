package com.medicalstore.controller;

import com.medicalstore.model.CartItem;
import com.medicalstore.model.Medicine;
import com.medicalstore.model.User;
import com.medicalstore.service.CartService;
import com.medicalstore.service.MedicineService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired private CartService cartService;
    @Autowired private MedicineService medicineService;

    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedUser");
        if (user == null) return "redirect:/user/login";
        model.addAttribute("cartItems", cartService.getCartByUser(user.getUserId()));
        model.addAttribute("total", cartService.getCartTotal(user.getUserId()));
        return "cart/view";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam String medicineId,
                            @RequestParam int quantity,
                            HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        if (user == null) return "redirect:/user/login";
        Medicine m = medicineService.findById(medicineId);
        if (m != null) {
            CartItem item = new CartItem(null, user.getUserId(), medicineId,
                    m.getName(), quantity, m.getPrice());
            cartService.addItem(item);
        }
        return "redirect:/cart";
    }

    @PostMapping("/update/{cartItemId}")
    public String updateQty(@PathVariable String cartItemId,
                            @RequestParam int quantity,
                            HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        if (user == null) return "redirect:/user/login";
        cartService.updateQuantity(cartItemId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/remove/{cartItemId}")
    public String remove(@PathVariable String cartItemId, HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        if (user == null) return "redirect:/user/login";
        cartService.removeItem(cartItemId);
        return "redirect:/cart";
    }
}
