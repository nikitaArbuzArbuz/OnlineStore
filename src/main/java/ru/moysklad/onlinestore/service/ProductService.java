package ru.moysklad.onlinestore.service;


import ru.moysklad.onlinestore.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();

    ProductDto getProductById(Long id);

    ProductDto createProduct(ProductDto product);

    ProductDto updateProduct(Long id, ProductDto product);

    void deleteProduct(Long id);

    List<ProductDto> getFilteredProducts(String name, Double minPrice, Double maxPrice, Boolean inStock, String sortBy, String direction, Integer limit);
}
