package ru.moysklad.onlinestore.util.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundException extends RuntimeException {
    private String message;
    private Long timestamp;

    public ProductNotFoundException(String message, Long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
