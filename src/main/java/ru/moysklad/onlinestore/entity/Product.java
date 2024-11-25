package ru.moysklad.onlinestore.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private String description;
    @Builder.Default
    private double price = 0.0;
    @Builder.Default
    private boolean inStock = false;
}
