package com.dsw.Project.controllers;

import com.dsw.Project.models.Cart;
import com.dsw.Project.models.Product;
import com.dsw.Project.services.CartService;
import com.dsw.Project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CarritoController {

    @Autowired
    private CartService cartService;

    @Autowired
    ProductService productService;


    @GetMapping("/carrito/agregar/{id}")
    public String addProductToCart(@PathVariable int id) {
        Product product = productService.getProduct(id);
        cartService.addProductToCart(cartService.provisionalCart, product );
        return "redirect: /carrito";
    }

    @GetMapping("/carrito/eliminar/{id}")
    public String removeProductFromCart(@PathVariable int id) {
        Product product = productService.getProduct(id);
        cartService.removeProductFromCart(cartService.provisionalCart, product );
        return "redirect: /carrito";
    }

    @GetMapping("/carrito")
    public String showCart(Model model) {
        model.addAttribute("cart", cartService.provisionalCart);
        return "cart/index";
    }
}
