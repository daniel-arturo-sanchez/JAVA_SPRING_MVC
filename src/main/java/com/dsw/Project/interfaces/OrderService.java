package com.dsw.Project.interfaces;

import com.dsw.Project.models.Order;

public interface OrderService {
    public void createOrder(Order order);
    public Order details (int id);
}
