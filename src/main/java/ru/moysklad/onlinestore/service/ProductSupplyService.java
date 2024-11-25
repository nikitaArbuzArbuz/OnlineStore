package ru.moysklad.onlinestore.service;

import ru.moysklad.onlinestore.dto.ProductSupplyDto;

public interface ProductSupplyService {
    public ProductSupplyDto createProductSupply(ProductSupplyDto productSupplyDto);

    void deleteProductSupply(Long id);

    ProductSupplyDto updateProductSupply(Long id, ProductSupplyDto productSupplyDto);

    ProductSupplyDto getProductSupplyById(Long id);
}
