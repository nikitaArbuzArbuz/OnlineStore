package ru.moysklad.onlinestore.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductDto {
    @NotBlank(message = "Please provide a name")
    @Size(max = 255, message = "The name must not exceed 255 characters")
    private String name;
    @Size(max = 4096, message = "The description must not exceed 4096 characters")
    private String description;
    @Min(value = 0, message = "Price cannot be less than 0")
    private double price;
    private boolean inStock;
}
