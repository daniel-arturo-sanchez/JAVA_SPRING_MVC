package com.dsw.Project.controllers;

import com.dsw.Project.models.Order;
import com.dsw.Project.services.CartService;
import com.dsw.Project.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrdersController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;

    @GetMapping("/pedidos")
    public String index(Model model) {
        List<Order> orders = orderService.list();
        if(orderService.list() == null) {
            orders = new ArrayList<Order>();
            model.addAttribute("orders", orderService.provisionalOrder);
        } else {
            model.addAttribute("orders", orderService.provisionalOrder);
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
        int num = cartService.details(1).getProducts().size();
        if(num == 0) {
            result = "cart/index";
        } else {
            orderService.createOrder(orderService.provisionalOrder);
            result = "redirect:/productos";
        }
        return result;
    }
}
