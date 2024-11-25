package ru.moysklad.onlinestore;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.moysklad.onlinestore.dto.ProductSaleDto;
import ru.moysklad.onlinestore.service.ProductSaleService;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureWebClient
public class ProductSaleControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductSaleService productSaleService;

    @Autowired
    private ObjectMapper objectMapper;

    private ProductSaleDto productSaleDto;

    @BeforeEach
    void setUp() {
        productSaleDto = new ProductSaleDto()
                .setId(1L)
                .setDocumentName("Sale Doc 1")
                .setProductId(1L)
                .setQuantitySold(10);
    }

    @Test
    void createProductSale() throws Exception {
        Mockito.when(productSaleService.createProductSale(any(ProductSaleDto.class))).thenReturn(productSaleDto);

        mockMvc.perform(post("/api/sales")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productSaleDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.documentName", is("Sale Doc 1")))
                .andExpect(jsonPath("$.productId", is(1)))
                .andExpect(jsonPath("$.quantitySold", is(10)));
    }

    @Test
    void getProductSaleById() throws Exception {
        ProductSaleDto productSaleDto = new ProductSaleDto()
                .setProductId(1L)
                .setId(1L)
                .setDocumentName("Sale Doc 1")
                .setQuantitySold(10);
        Mockito.when(productSaleService.getProductSaleById(1L)).thenReturn(productSaleDto);

        mockMvc.perform(get("/api/sales/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.documentName", is("Sale Doc 1")))
                .andExpect(jsonPath("$.productId", is(1)))
                .andExpect(jsonPath("$.quantitySold", is(10)));
    }

    @Test
    void updateProductSale() throws Exception {
        ProductSaleDto updatedSaleDto = new ProductSaleDto()
                .setProductId(1L)
                .setId(1L)
                .setDocumentName("Updated Sale Doc")
                .setQuantitySold(20);
        Mockito.when(productSaleService.updateProductSale(eq(1L), any(ProductSaleDto.class))).thenReturn(updatedSaleDto);

        mockMvc.perform(patch("/api/sales/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedSaleDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.documentName", is("Updated Sale Doc")))
                .andExpect(jsonPath("$.productId", is(1)))
                .andExpect(jsonPath("$.quantitySold", is(20)));
    }

    @Test
    void deleteProductSale() throws Exception {
        doNothing().when(productSaleService).deleteProductSale(1L);

        mockMvc.perform(delete("/api/sales/{id}", 1L))
                .andExpect(status().isOk());

        Mockito.verify(productSaleService, Mockito.times(1)).deleteProductSale(1L);
    }
}
