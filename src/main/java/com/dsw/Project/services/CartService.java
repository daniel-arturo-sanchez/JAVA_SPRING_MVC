package com.dsw.Project.services;

import com.dsw.Project.models.Cart;
import com.dsw.Project.models.Product;
import com.dsw.Project.models.User;
import com.dsw.Project.repositories.CartRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public void createCart(Cart cart, User user) {
        cart.setUser(user);
        cartRepository.saveAndFlush(cart);
        cart.setProducts(new ArrayList<Product>());
        cart.setTotalPrice((float) 0);
        cartRepository.saveAndFlush(cart);
    }

    @Override
    public void emptyCart(Cart cart) {
         cart.setProducts(new ArrayList<Product>());
         cart.setTotalPrice((float) 0);
         cartRepository.saveAndFlush(cart);
    }

    @Override
    public void addProductToCart(Cart cart, Product product) {
        List<Product> products = cart.getProducts();
        products.add(product);
        cart.setTotalPrice(cart.getTotalPrice() + product.getProductPrice());
        cartRepository.saveAndFlush(cart);
    }

    @Override
    public void removeProductFromCart(Cart cart, Product product) {
        if(cart.getProducts().isEmpty() || cart.getTotalPrice() != 0) {
            List<Product> products = cart.getProducts();
            Product removeItem = products.stream().filter(p -> Objects.equals(p.getId(), product.getId())).findFirst().orElse(null);
            if(removeItem != null) {
                products.remove(removeItem);
                cart.setProducts(products);
                cart.setTotalPrice(cart.getTotalPrice() - product.getProductPrice());
                cartRepository.saveAndFlush(cart);
            }
        }

    }

    @Override
    public Cart findCartByUser(User user) {
        return cartRepository.findCartByUserId(user.getId());
    }
}
