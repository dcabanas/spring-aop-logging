package com.javatechie.spring.aop.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveProductDTO {

    @ToString.Exclude
    private String nif;
    private String name;
    private double price;

}
