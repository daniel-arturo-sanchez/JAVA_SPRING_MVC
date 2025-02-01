package com.dsw.Project.interfaces;

import com.dsw.Project.models.Product;

import java.util.List;

public interface ProductService {
    public void createProduct(Product product);
    public Product getProduct(int id);
    public List<Product> list();
}
