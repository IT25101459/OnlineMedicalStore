package com.medicalstore.controller;

import com.medicalstore.model.Category;
import com.medicalstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired private CategoryService categoryService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        return "category/list";
    }

    @GetMapping("/add")
    public String addPage() { return "category/add"; }

    @PostMapping("/add")
    public String add(@RequestParam String name, @RequestParam String description) {
        categoryService.add(new Category(null, name, description));
        return "redirect:/category";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable String id, Model model) {
        model.addAttribute("category", categoryService.findById(id));
        return "category/edit";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam String categoryId,
                       @RequestParam String name,
                       @RequestParam String description) {
        categoryService.update(new Category(categoryId, name, description));
        return "redirect:/category";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        categoryService.delete(id);
        return "redirect:/category";
    }
}
