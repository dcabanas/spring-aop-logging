package com.javatechie.spring.aop.api.service;

import com.javatechie.spring.aop.api.model.Product;
import com.javatechie.spring.aop.api.model.SaveProductDTO;
import com.javatechie.spring.aop.api.model.UpdateProductDTO;
import com.javatechie.spring.aop.api.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void initDB() {
        repository.saveAll(Stream.of(new Product(101, "Book", 499), new Product(102, "laptop", 78000))
            .collect(Collectors.toList()));
    }
    public Product addProduct(SaveProductDTO product) {
        return repository.saveAndFlush(Product.convert(product));
    }

    public List<Product> findAllProducts() {
        return repository.findAll();
    }

    public Product updateProduct(UpdateProductDTO productDTO) {

        Product p = repository.findById(productDTO.getId()).orElse(null);

        if (p == null) return null;

        p.setPrice(productDTO.getPrice());
        repository.saveAndFlush(p);

        return p;
    }

    public boolean deleteProduct(int id) {

        Product p = repository.findById(id).orElse(null);

        if (p == null) return false;

        repository.delete(p);
        return true;
    }
}
