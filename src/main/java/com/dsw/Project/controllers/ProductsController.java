package com.dsw.Project.controllers;

import com.dsw.Project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductsController {
    @Autowired
    private ProductService productService;

    @GetMapping("/productos")
    public String listaProductos(Model model) {
        model.addAttribute("products", productService.list());
        return "products/index";
    }
}
