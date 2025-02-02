package com.dsw.Project.services;

import com.dsw.Project.models.Cart;
import com.dsw.Project.models.Order;
import com.dsw.Project.models.Product;
import com.dsw.Project.repositories.CartRepository;
import com.dsw.Project.repositories.OrderRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Primary
@Service
public class OrderService implements com.dsw.Project.interfaces.OrderService {

    public Order provisionalOrder = new Order();

    @PostConstruct
    public void init() {
        provisionalOrder.setId(1);
        provisionalOrder.setProducts(new ArrayList<Product>());
        provisionalOrder.setTotalPrice((float) 0);
    }

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartService cs;

    @Override
    public void createOrder(Order order) {
//        Cart cart = cs.details(1);
        Cart cart = cs.provisionalCart;
        order.setOrderDate(LocalDateTime.now());
        order.setProducts(cart.getProducts());
        order.setTotalPrice(cart.getTotalPrice());
        orderRepository.save(order);
        cs.emptyCart(cart);
    }

    @Override
    public Order details(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> list() {
        return orderRepository.findAll();
    }
}
