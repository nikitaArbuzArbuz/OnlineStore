package ru.moysklad.onlinestore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.moysklad.onlinestore.dto.ProductDto;
import ru.moysklad.onlinestore.entity.Product;
import ru.moysklad.onlinestore.mapper.ProductMapper;
import ru.moysklad.onlinestore.service.impl.ProductServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        Product product1 = Product.builder().id(1L).name("Product 1").description("Description 1").price(10.0).inStock(true).build();
        Product product2 = Product.builder().id(2L).name("Product 2").description("Description 2").price(20.0).inStock(true).build();

        productService.getProductMap().put(1L, product1);
        productService.getProductMap().put(2L, product2);

        ProductDto productDto1 = new ProductDto();
        ProductDto productDto2 = new ProductDto();

        when(productMapper.map(product1)).thenReturn(productDto1);
        when(productMapper.map(product2)).thenReturn(productDto2);

        List<ProductDto> products = productService.getAllProducts();

        assertEquals(2, products.size());
        verify(productMapper, times(1)).map(product1);
        verify(productMapper, times(1)).map(product2);
    }

    @Test
    public void testGetProductById() {
        Product product = Product.builder().id(1L).name("Product 1").build();
        ProductDto productDto = new ProductDto();

        productService.getProductMap().put(1L, product);

        when(productMapper.map(product)).thenReturn(productDto);

        ProductDto result = productService.getProductById(1L);

        assertEquals(productDto, result);
        verify(productMapper, times(1)).map(product);
    }

    @Test
    public void testCreateProduct() {
        ProductDto productDto = new ProductDto();
        productDto.setName("New Product");

        Product product = Product.builder().id(1L).name("New Product").build();

        when(productMapper.map(productDto)).thenReturn(product);
        when(productMapper.map(product)).thenReturn(productDto);

        ProductDto result = productService.createProduct(productDto);

        assertEquals(productDto, result);
        verify(productMapper, times(1)).map(productDto);
        verify(productMapper, times(1)).map(product);
    }

    @Test
    public void testUpdateProduct() {
        ProductDto productDto = new ProductDto();
        productDto.setName("Updated Product");

        Product existingProduct = Product.builder().id(1L).name("Old Product").build();

        productService.getProductMap().put(1L, existingProduct);

        when(productMapper.map(productDto)).thenReturn(existingProduct);
        when(productMapper.map(existingProduct)).thenReturn(productDto);

        ProductDto result = productService.updateProduct(1L, productDto);

        assertEquals(productDto, result);
        verify(productMapper, times(1)).updateProductFromDto(productDto, existingProduct);
        verify(productMapper, times(1)).map(existingProduct);
    }

    @Test
    public void testDeleteProduct() {
        Product product = Product.builder().id(1L).name("Product to be deleted").build();

        productService.getProductMap().put(1L, product);

        productService.deleteProduct(1L);

        assertFalse(productService.getProductMap().containsKey(1L));
    }
}

