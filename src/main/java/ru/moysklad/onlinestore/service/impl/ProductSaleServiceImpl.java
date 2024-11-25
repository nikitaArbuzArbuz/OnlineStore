package ru.moysklad.onlinestore.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.moysklad.onlinestore.dto.ProductSaleDto;
import ru.moysklad.onlinestore.entity.Product;
import ru.moysklad.onlinestore.entity.ProductSale;
import ru.moysklad.onlinestore.mapper.ProductSaleMapper;
import ru.moysklad.onlinestore.repository.ProductRepository;
import ru.moysklad.onlinestore.repository.ProductSaleRepository;
import ru.moysklad.onlinestore.service.ProductSaleService;
import ru.moysklad.onlinestore.util.exceptions.ProductNotFoundException;

@Service
@RequiredArgsConstructor
public class ProductSaleServiceImpl implements ProductSaleService {
    private final ProductSaleRepository productSaleRepository;
    private final ProductRepository productRepository;
    private final ProductSaleMapper productSaleMapper;

    @Override
    @Transactional
    public ProductSaleDto createProductSale(ProductSaleDto productSaleDto) {
        Product product = productRepository.findById(productSaleDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found", System.currentTimeMillis()));

        if (product.getQuantityInStock() < productSaleDto.getQuantitySold()) {
            throw new IllegalArgumentException("Insufficient stock for sale");
        }

        ProductSale productSale = productSaleMapper.map(productSaleDto);

        productSaleRepository.save(productSale);

        product.setQuantityInStock(product.getQuantityInStock() - productSale.getQuantitySold());
        productRepository.save(product);

        return productSaleMapper.map(productSale);
    }

    @Override
    public ProductSaleDto getProductSaleById(Long id) {
        ProductSale productSale = productSaleRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("ProductSale not found", System.currentTimeMillis()));
        return productSaleMapper.map(productSale);
    }

    @Override
    public ProductSaleDto updateProductSale(Long id, ProductSaleDto productSaleDto) {
        ProductSale productSale = productSaleRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("ProductSale not found", System.currentTimeMillis()));
        productSaleMapper.updateProductSaleFromDto(productSaleDto, productSale);
        return productSaleMapper.map(productSaleRepository.saveAndFlush(productSale));
    }


    @Override
    public void deleteProductSale(Long id) {
        ProductSale productSale = productSaleRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("ProductSale not found", System.currentTimeMillis()));
        productSaleRepository.delete(productSale);
    }

}
