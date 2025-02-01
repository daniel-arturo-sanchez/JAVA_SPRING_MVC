package com.dsw.Project.interfaces;

import com.dsw.Project.models.Order;

import java.util.List;

public interface OrderService {
    public void createOrder(Order order);
    public Order details (int id);
    public List<Order> list();
}
