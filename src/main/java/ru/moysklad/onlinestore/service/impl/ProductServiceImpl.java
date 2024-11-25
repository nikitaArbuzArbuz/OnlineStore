package ru.moysklad.onlinestore.service.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.moysklad.onlinestore.dto.ProductDto;
import ru.moysklad.onlinestore.entity.Product;
import ru.moysklad.onlinestore.mapper.ProductMapper;
import ru.moysklad.onlinestore.service.ProductService;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Getter
    private final Map<Long, Product> productMap = new HashMap<>();
    private long currentId = 1;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDto> getAllProducts() {
        return new ArrayList<>(productMap.values()).stream().map(productMapper::map).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long id) {
        if (!productMap.containsKey(id)) {
            throw new NoSuchElementException("Продукт не найден");
        }
        return productMapper.map(productMap.get(id));
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = productMapper.map(productDto);
        product.setId(currentId++);
        productMap.put(product.getId(), product);
        return productMapper.map(product);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        if (!productMap.containsKey(id)) {
            throw new NoSuchElementException("Продукт не найден");
        }
        Product product = productMap.get(id);
        productMapper.updateProductFromDto(productDto, product);
        productMap.put(id, product);
        return productMapper.map(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productMap.remove(id);
    }
}
