package com.dsw.Project.interfaces;

import com.dsw.Project.models.Cart;
import com.dsw.Project.models.Product;
import com.dsw.Project.models.User;

import java.util.List;

public interface CartService {
    public Cart details(int id);
    public void createCart(Cart cart, User user);
    public void emptyCart(Cart cart);
    public void addProductToCart(Cart cart, Product product);
    public void removeProductFromCart(Cart cart, Product product);
    public Cart findCartByUser(User user);
}

