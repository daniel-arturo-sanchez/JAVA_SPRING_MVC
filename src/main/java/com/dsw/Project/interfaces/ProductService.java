package com.dsw.Project.interfaces;

import com.dsw.Project.models.Product;

public interface ProductService {
    public void createProduct(Product product);
    public Product getProduct(int id);
}
