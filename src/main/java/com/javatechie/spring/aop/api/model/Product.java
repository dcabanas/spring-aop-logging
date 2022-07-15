package com.javatechie.spring.aop.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nif;
    private String name;
    private double price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;

        Product product = (Product) o;
        return this.id == product.getId();
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return String.format("Product[id=%d, name=%s, price=%.2f]", id, name, price);
    }

    public static Product convert(SaveProductDTO productDTO) {

        return Product.builder()
            .nif(productDTO.getNif())
            .name(productDTO.getName())
            .price(productDTO.getPrice())
            .build();
    }
}
