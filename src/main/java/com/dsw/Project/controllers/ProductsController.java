package com.dsw.Project.controllers;

import com.dsw.Project.models.Product;
import com.dsw.Project.services.MunicipioService;
import com.dsw.Project.services.ProductService;
import com.dsw.Project.services.ProvinciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@PreAuthorize("isAuthenticated()")
@Controller
public class ProductsController {
    @Autowired
    private ProductService productService;

    @Autowired
    private MunicipioService municipioService;

    @Autowired
    private ProvinciaService provinciaService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/productos")
    public String listaProductos(Model model) {
        model.addAttribute("products", productService.list());
        return "products/index";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/productos/detalles/{id}")
    public String details(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.getProduct(id));
        return "products/details";
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping("/productos/crear")
    public String createProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("municipios", municipioService.list());
        model.addAttribute("provincias", provinciaService.list());
        return "products/create";
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PostMapping("/productos/crear")
    public String createProduct(@Valid @ModelAttribute("product") Product product,  BindingResult bindingResult) {
        String result;
        if(bindingResult.hasErrors()){
            result = "products/create";
        } else {
            productService.createProduct(product);
            result = "redirect:/productos";
        }
        return result;
    }
}
