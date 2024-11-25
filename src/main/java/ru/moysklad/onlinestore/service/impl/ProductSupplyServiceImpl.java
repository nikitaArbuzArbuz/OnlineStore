package ru.moysklad.onlinestore.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.moysklad.onlinestore.dto.ProductSupplyDto;
import ru.moysklad.onlinestore.entity.Product;
import ru.moysklad.onlinestore.entity.ProductSupply;
import ru.moysklad.onlinestore.mapper.ProductSupplyMapper;
import ru.moysklad.onlinestore.repository.ProductRepository;
import ru.moysklad.onlinestore.repository.ProductSupplyRepository;
import ru.moysklad.onlinestore.service.ProductSupplyService;
import ru.moysklad.onlinestore.util.exceptions.ProductNotFoundException;

@Service
@RequiredArgsConstructor
public class ProductSupplyServiceImpl implements ProductSupplyService {
    private final ProductSupplyRepository productSupplyRepository;
    private final ProductRepository productRepository;
    private final ProductSupplyMapper productSupplyMapper;

    @Override
    @Transactional
    public ProductSupplyDto createProductSupply(ProductSupplyDto productSupplyDto) {
        Product product = productRepository.findById(productSupplyDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found", System.currentTimeMillis()));

        ProductSupply productSupply = productSupplyMapper.map(productSupplyDto);

        productSupplyRepository.save(productSupply);

        product.setQuantityInStock(product.getQuantityInStock() + productSupply.getQuantitySupplied());
        productRepository.save(product);

        return productSupplyMapper.map(productSupply);
    }

    @Override
    public void deleteProductSupply(Long id) {
        ProductSupply productSupply = productSupplyRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("ProductSupply not found", System.currentTimeMillis()));
        productSupplyRepository.delete(productSupply);
    }


    @Override
    public ProductSupplyDto updateProductSupply(Long id, ProductSupplyDto productSupplyDto) {
        ProductSupply productSupply = productSupplyRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("ProductSupply not found", System.currentTimeMillis()));
        productSupplyMapper.updateProductSupplyFromDto(productSupplyDto, productSupply);
        return productSupplyMapper.map(productSupplyRepository.saveAndFlush(productSupply));
    }


    @Override
    public ProductSupplyDto getProductSupplyById(Long id) {
        ProductSupply productSupply = productSupplyRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("ProductSupply not found", System.currentTimeMillis()));
        return productSupplyMapper.map(productSupply);
    }

}
