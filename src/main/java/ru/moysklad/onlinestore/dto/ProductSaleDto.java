package ru.moysklad.onlinestore.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class ProductSaleDto {
    private Long id;
    @Size(max = 255, message = "The name must not exceed 255 characters")
    private String documentName;
    @NotBlank(message = "Please provide a productId")
    private Long productId;
    @Min(value = 0, message = "Quantity sold cannot be less than 0")
    private Integer quantitySold;
    @Min(value = 0, message = "Purchase cost sold cannot be less than 0")
    private BigDecimal purchaseCost;
}
