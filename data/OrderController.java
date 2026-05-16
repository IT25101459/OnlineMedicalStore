package com.medicalstore.controller;

import com.medicalstore.model.Order;
import com.medicalstore.model.User;
import com.medicalstore.service.CartService;
import com.medicalstore.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired private OrderService orderService;
    @Autowired private CartService cartService;

    private void addCounts(List<Order> orders, Model model) {
        long pending   = orders.stream().filter(o -> "pending".equals(o.getStatus())).count();
        long delivered = orders.stream().filter(o -> "delivered".equals(o.getStatus())).count();
        model.addAttribute("pendingCount", pending);
        model.addAttribute("deliveredCount", delivered);
    }

    @GetMapping
    public String myOrders(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedUser");
        if (user == null) return "redirect:/user/login";
        List<Order> orders = orderService.getByUser(user.getUserId());
        model.addAttribute("orders", orders);
        addCounts(orders, model);
        return "order/list";
    }

    @GetMapping("/all")
    public String allOrders(Model model) {
        List<Order> orders = orderService.getAll();
        model.addAttribute("orders", orders);
        addCounts(orders, model);
        return "order/list";
    }

    @PostMapping("/place")
    public String place(@RequestParam String medicineId,
                        @RequestParam String medicineName,
                        @RequestParam int quantity,
                        @RequestParam double totalAmount,
                        HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        if (user == null) return "redirect:/user/login";
        Order order = new Order(null, user.getUserId(), medicineId, medicineName,
                quantity, totalAmount, null, null);
        orderService.place(order);
        cartService.clearCart(user.getUserId());
        return "redirect:/order?placed";
    }

    @PostMapping("/status/{orderId}")
    public String updateStatus(@PathVariable String orderId, @RequestParam String status) {
        orderService.updateStatus(orderId, status);
        return "redirect:/order/all";
    }

    @PostMapping("/delete/{orderId}")
    public String delete(@PathVariable String orderId, HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        if (user == null) return "redirect:/user/login";
        orderService.delete(orderId);
        return "redirect:/order";
    }
}
