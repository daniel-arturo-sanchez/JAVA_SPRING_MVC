package com.dsw.Project.repositories;

import com.dsw.Project.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    public Order findById(int id);
}
