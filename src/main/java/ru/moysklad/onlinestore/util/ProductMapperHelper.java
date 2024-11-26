package ru.moysklad.onlinestore.util;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import ru.moysklad.onlinestore.entity.Product;
import ru.moysklad.onlinestore.repository.ProductRepository;
import ru.moysklad.onlinestore.util.exceptions.ProductNotFoundException;


@Component
@RequiredArgsConstructor
public class ProductMapperHelper {
    private final ProductRepository productRepository;

    @Named("mapProductFromId")
    public Product mapProductFromId(Long userId) {
        return productRepository.findById(userId)
                .orElseThrow(() -> new ProductNotFoundException("User not found",
                        System.currentTimeMillis()));
    }
}