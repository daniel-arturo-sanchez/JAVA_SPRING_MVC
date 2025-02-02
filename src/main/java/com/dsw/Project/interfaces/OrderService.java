package com.dsw.Project.interfaces;

import com.dsw.Project.models.Cart;
import com.dsw.Project.models.Order;
import java.util.List;

public interface OrderService {
    public void createOrder(Cart cart);
    public Order details (int id);
    public List<Order> list();
    public List<Order> findByUser(int id);
}
