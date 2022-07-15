package com.javatechie.spring.aop.api.controller;

import com.javatechie.spring.aop.api.model.Product;
import com.javatechie.spring.aop.api.model.SaveProductDTO;
import com.javatechie.spring.aop.api.model.UpdateProductDTO;
import com.javatechie.spring.aop.api.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }


    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@RequestBody SaveProductDTO product) {

        return ResponseEntity.ok(service.addProduct(product));
    }

    @GetMapping("/findProducts")
    public ResponseEntity<List<Product>> getProducts() {

        return ResponseEntity.ok(service.findAllProducts());
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateProduct(@RequestBody UpdateProductDTO productDTO) {

        Product updated = service.updateProduct(productDTO);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(String.format("Produto %d atualizado com sucesso", updated.getId()));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable int id) {

        if (!service.deleteProduct(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(true);
    }

    @PostMapping("/findProduct/{id}")
    public ResponseEntity<Product> findProduct(@PathVariable int id) {

        Product found = service.findProduct(id);

        if (found == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(found);
    }

}
