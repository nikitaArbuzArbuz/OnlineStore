package ru.moysklad.onlinestore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import ru.moysklad.onlinestore.dto.ProductDto;
import ru.moysklad.onlinestore.entity.Product;
import ru.moysklad.onlinestore.mapper.ProductMapper;
import ru.moysklad.onlinestore.repository.ProductRepository;
import ru.moysklad.onlinestore.service.impl.ProductServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"spring.profiles.active=test"})
@EnableConfigurationProperties
public class ProductServiceTest {

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    private Product product;
    private ProductDto productDto;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L)
                .setName("Name1")
                .setDescription("Test");

        productDto = new ProductDto();
        productDto.setName("Name1")
                .setDescription("Test");
    }

    @Test
    void shouldCreateTask() {
        ProductDto expectedTaskDto = new ProductDto();
        expectedTaskDto.setName("Name1");

        when(productMapper.map(productDto)).thenReturn(product);
        when(productRepository.saveAndFlush(product)).thenReturn(product);
        when(productMapper.map(product)).thenReturn(expectedTaskDto);

        ProductDto result = productService.createProduct(productDto);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Name1");
        verify(productRepository, times(1)).saveAndFlush(any(Product.class));
    }

    @Test
    void shouldGetTaskById() {
        when(productRepository.findById(1L)).thenReturn(java.util.Optional.of(product));
        when(productMapper.map(any(Product.class))).thenReturn(productDto);

        ProductDto result = productService.getProductById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Name1");
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateProduct() {
        Long productId = 1L;
        ProductDto updatedProductDto = new ProductDto();
        updatedProductDto.setName("Updated Name")
                .setDescription("Updated Description");

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        doNothing().when(productMapper).updateProductFromDto(updatedProductDto, product);
        when(productRepository.saveAndFlush(product)).thenReturn(product);
        when(productMapper.map(product)).thenReturn(updatedProductDto);

        ProductDto result = productService.updateProduct(productId, updatedProductDto);

        assertEquals("Updated Name", result.getName());
        assertEquals("Updated Description", result.getDescription());
        verify(productRepository).findById(productId);
        verify(productRepository).saveAndFlush(product);
        verify(productMapper).updateProductFromDto(updatedProductDto, product);
        verify(productMapper).map(product);
    }

    @Test
    void deleteProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).delete(product);
    }
}

