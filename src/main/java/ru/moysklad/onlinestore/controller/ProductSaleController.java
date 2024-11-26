package ru.moysklad.onlinestore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.moysklad.onlinestore.dto.ProductSaleDto;
import ru.moysklad.onlinestore.service.ProductSaleService;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class ProductSaleController {

    private final ProductSaleService productSaleService;

    @PostMapping
    public ResponseEntity<ProductSaleDto> createProductSale(@RequestBody ProductSaleDto productSaleDto) {
        return new ResponseEntity<>(productSaleService.createProductSale(productSaleDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ProductSaleDto getProductSaleById(@PathVariable Long id) {
        return productSaleService.getProductSaleById(id);
    }

    @PatchMapping("/{id}")
    public ProductSaleDto updateProductSale(@PathVariable Long id, @RequestBody ProductSaleDto productSaleDto) {
        return productSaleService.updateProductSale(id, productSaleDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProductSale(@PathVariable Long id) {
        productSaleService.deleteProductSale(id);
    }
}