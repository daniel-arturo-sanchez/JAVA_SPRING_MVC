package com.dsw.Project.services;

import com.dsw.Project.models.Product;
import com.dsw.Project.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class ProductService implements com.dsw.Project.interfaces.ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product getProduct(int id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> list() {
        return productRepository.findAll();
    }
}
