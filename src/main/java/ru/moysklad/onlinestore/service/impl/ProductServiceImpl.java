package ru.moysklad.onlinestore.service.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.moysklad.onlinestore.dto.ProductDto;
import ru.moysklad.onlinestore.entity.Product;
import ru.moysklad.onlinestore.mapper.ProductMapper;
import ru.moysklad.onlinestore.repository.ProductRepository;
import ru.moysklad.onlinestore.service.ProductService;
import ru.moysklad.onlinestore.util.exceptions.ProductNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Getter
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Override
    public List<ProductDto> getAllProducts() {
        return productMapper.map(productRepository.findAll());
    }

    @Override
    public ProductDto getProductById(Long productId) {
        return productMapper.map(productRepository.findById(productId).orElseThrow(() ->
                new ProductNotFoundException("Product not found", System.currentTimeMillis())));
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        return productMapper.map(productRepository.saveAndFlush(productMapper.map(productDto)));
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto productDto) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new ProductNotFoundException("Product not found", System.currentTimeMillis()));
        productMapper.updateProductFromDto(productDto, product);
        return productMapper.map(productRepository.saveAndFlush(product));
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.delete(productRepository.findById(productId).orElseThrow(() ->
                new ProductNotFoundException("Product not found", System.currentTimeMillis())));
    }

    @Override
    public List<ProductDto> getFilteredProducts(String name, Double minPrice, Double maxPrice,
                                                Boolean inStock, String sortBy, String direction, Integer limit) {

        List<Product> filteredProducts = productRepository.findAll().stream()
                .filter(product -> name == null || product.getName().contains(name))
                .filter(product -> minPrice == null || product.getPrice() >= minPrice)
                .filter(product -> maxPrice == null || product.getPrice() <= maxPrice)
                .filter(product -> inStock == null || product.isInStock() == inStock)
                .sorted((p1, p2) -> switch (sortBy) {
                    case "name" ->
                            direction.equals("asc") ? p1.getName().compareTo(p2.getName()) : p2.getName().compareTo(p1.getName());
                    case "price" ->
                            direction.equals("asc") ? Double.compare(p1.getPrice(), p2.getPrice()) : Double.compare(p2.getPrice(), p1.getPrice());
                    default -> 0;
                })
                .limit(limit)
                .collect(Collectors.toList());

        return productMapper.map(filteredProducts);
    }
}
