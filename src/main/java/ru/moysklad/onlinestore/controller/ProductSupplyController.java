package ru.moysklad.onlinestore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.moysklad.onlinestore.dto.ProductSupplyDto;
import ru.moysklad.onlinestore.service.ProductSupplyService;

@RestController
@RequestMapping("/supplies")
@RequiredArgsConstructor
public class ProductSupplyController {

    private final ProductSupplyService productSupplyService;

    @PostMapping
    public ResponseEntity<ProductSupplyDto> createProductSupply(@RequestBody ProductSupplyDto productSupplyDto) {
        return new ResponseEntity<>(productSupplyService.createProductSupply(productSupplyDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ProductSupplyDto getProductSupplyById(@PathVariable Long id) {
        return productSupplyService.getProductSupplyById(id);
    }

    @PutMapping("/{id}")
    public ProductSupplyDto updateProductSupply(@PathVariable Long id, @RequestBody ProductSupplyDto productSupplyDto) {
        return productSupplyService.updateProductSupply(id, productSupplyDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProductSupply(@PathVariable Long id) {
        productSupplyService.deleteProductSupply(id);
    }

}
