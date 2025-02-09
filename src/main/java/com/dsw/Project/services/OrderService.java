package com.dsw.Project.services;

import com.dsw.Project.models.Cart;
import com.dsw.Project.models.Order;
import com.dsw.Project.models.Product;
import com.dsw.Project.models.User;
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

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartService cs;



    @Override
    public void createOrder(Cart cart, User user) {
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        List<Product> productsCopy = new ArrayList<>();
        for (Product product : cart.getProducts()) {
            productsCopy.add(product); // Assuming Product has a copy constructor
        }
        order.setProducts(productsCopy);
        order.setTotalPrice(cart.getTotalPrice());
        orderRepository.saveAndFlush(order);
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

    @Override
    public List<Order> findByUser(int id) {
        return orderRepository.findByUserId(id);
    }
}
