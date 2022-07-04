package com.javatechie.spring.aop.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProductDTO {

    private int id;
    private String name;
    private double price;

    @Override
    public String toString() {
        return String.format("UpdateProductDTO[id=%d, name=%s, price=%.2f]", id, name, price);
    }
}
