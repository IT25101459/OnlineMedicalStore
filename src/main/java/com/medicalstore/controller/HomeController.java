package com.medicalstore.controller;

import com.medicalstore.model.Medicine;
import com.medicalstore.model.User;
import com.medicalstore.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired private MedicineService medicineService;
    @Autowired private CategoryService categoryService;
    @Autowired private OrderService orderService;

    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedUser");
        List<Medicine> medicines = medicineService.getAll();

        long inStock = medicines.stream().filter(m -> m.getStock() > 0).count();

        model.addAttribute("user", user);
        model.addAttribute("medicines", medicines);
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("inStockCount", inStock);

        if (user != null) {
            model.addAttribute("myOrders", orderService.getByUser(user.getUserId()));
        }
        return "home";
    }
}
