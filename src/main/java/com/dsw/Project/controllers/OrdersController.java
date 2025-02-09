package com.dsw.Project.controllers;

import com.dsw.Project.models.Cart;
import com.dsw.Project.models.Order;
import com.dsw.Project.models.User;
import com.dsw.Project.services.CartService;
import com.dsw.Project.services.OrderService;
import com.dsw.Project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.ArrayList;
import java.util.List;

@PreAuthorize("hasRole('ADMIN')")
@Controller
public class OrdersController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    @GetMapping("/pedidos")
    public String index(Model model) {
        User user = userService.findByUsername(getAuthenticatedUsername());
        List<Order> orders = orderService.findByUser(user.getId());
        if(orderService.list() == null) {
            orders = new ArrayList<Order>();
            model.addAttribute("orders", orders);
        } else {
            model.addAttribute("orders", orders);
        }
        return "orders/index";
    }

    @GetMapping("/pedidos/detalles/{id}")
    public String index(@PathVariable int id, Model model) {
        model.addAttribute("order", orderService.details(id));
        return "orders/details";
    }

    @GetMapping("/pedidos/crear")
    public String createOrder() {
        String result;
        User user = userService.findByUsername(getAuthenticatedUsername());
        Cart cart = cartService.details(user.getId());
        if(cart.getProducts().isEmpty()) {
            result = "cart/index";
        } else {
            orderService.createOrder(cart, user);
            result = "redirect:/pedidos";
        }
        return result;
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
