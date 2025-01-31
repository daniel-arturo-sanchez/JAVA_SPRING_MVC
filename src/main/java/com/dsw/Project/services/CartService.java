package com.dsw.Project.services;

import com.dsw.Project.models.Cart;
import com.dsw.Project.models.Product;
import com.dsw.Project.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service
public class CartService implements com.dsw.Project.interfaces.CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart details(int id) {
        return cartRepository.findCartById(id);
    }

    @Override
    public void createCart(Cart cart) {
        cart.setProducts(new ArrayList<Product>());
        cart.setTotalPrice((float) 0);
        cartRepository.save(cart);
    }

    @Override
    public void emptyCart(Cart cart) {
         cart.setProducts(new ArrayList<Product>());
         cartRepository.save(cart);
    }

    @Override
    public void addProductToCart(Cart cart, Product product) {
        List<Product> products = cart.getProducts();
        products.add(product);
        cart.setProducts(products);
        cartRepository.save(cart);
    }

    @Override
    public void removeProductFromCart(Cart cart, Product product) {
        List<Product> products = cart.getProducts();
        products.remove(product);
        cart.setProducts(products);
        cartRepository.save(cart);
    }
}
