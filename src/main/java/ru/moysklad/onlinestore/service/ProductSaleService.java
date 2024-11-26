package ru.moysklad.onlinestore.service;

import ru.moysklad.onlinestore.dto.ProductSaleDto;

public interface ProductSaleService {
    ProductSaleDto createProductSale(ProductSaleDto productSaleDto);

    ProductSaleDto getProductSaleById(Long id);

    ProductSaleDto updateProductSale(Long id, ProductSaleDto productSaleDto);

    void deleteProductSale(Long id);
}
