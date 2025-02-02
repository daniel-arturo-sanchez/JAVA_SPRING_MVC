package com.dsw.Project.repositories;

import com.dsw.Project.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    public Cart findCartById(int id);

    public Cart findCartByUserId(int userId);
}
