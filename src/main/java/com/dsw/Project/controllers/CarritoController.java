package com.dsw.Project.controllers;

import com.dsw.Project.models.Cart;
import com.dsw.Project.models.Product;
import com.dsw.Project.models.User;
import com.dsw.Project.services.CartService;
import com.dsw.Project.services.ProductService;
import com.dsw.Project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.core.userdetails.UserDetails;

@PreAuthorize("hasRole('ADMIN')")
@Controller
public class CarritoController {

    @Autowired
    private CartService cartService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @GetMapping("/carrito/agregar/{id}")
    public String addProductToCart(@PathVariable int id) {
        Product product = productService.getProduct(id);
        User user = userService.findByUsername(getAuthenticatedUsername());
        Cart cart = cartService.details(user.getId());
        cartService.addProductToCart(cart, product );
        return "redirect:/carrito";
    }

    @GetMapping("/carrito/eliminar/{id}")
    public String removeProductFromCart(@PathVariable int id) {
        Product product = productService.getProduct(id);
        User user = userService.findByUsername(getAuthenticatedUsername());
        Cart cart = cartService.details(user.getId());
        cartService.removeProductFromCart(cart, product);
        return "redirect:/carrito";
    }

    @GetMapping("/carrito")
    public String showCart(Model model) {
        model.addAttribute("cart", userService.findByUsername(getAuthenticatedUsername()).getCart());
        return "cart/index";
    }

    public String getAuthenticatedUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getUsername();
        }
        return null;
    }
}
