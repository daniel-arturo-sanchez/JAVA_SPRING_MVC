package com.dsw.Project.interfaces;

import com.dsw.Project.models.Cart;
import com.dsw.Project.models.Order;
import com.dsw.Project.models.User;

import java.util.List;

public interface OrderService {
    public void createOrder(Cart cart, User user);
    public Order details (int id);
    public List<Order> list();
    public List<Order> findByUser(int id);
}
