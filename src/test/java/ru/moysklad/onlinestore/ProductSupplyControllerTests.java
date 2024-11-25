package ru.moysklad.onlinestore;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.moysklad.onlinestore.controller.ProductSupplyController;
import ru.moysklad.onlinestore.dto.ProductSupplyDto;
import ru.moysklad.onlinestore.service.ProductSupplyService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductSupplyController.class)
public class ProductSupplyControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductSupplyService productSupplyService;

    private ProductSupplyDto supplyDto;

    @BeforeEach
    void setUp() {
        supplyDto = new ProductSupplyDto();
        supplyDto.setId(1L);
        supplyDto.setDocumentName("Supply Document");
        supplyDto.setProductId(1L);
        supplyDto.setQuantitySupplied(10);
    }

    @Test
    void testCreateSupply() throws Exception {
        when(productSupplyService.createProductSupply(any(ProductSupplyDto.class))).thenReturn(supplyDto);

        mockMvc.perform(post("/api/supplies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(supplyDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.documentName").value(supplyDto.getDocumentName()))
                .andExpect(jsonPath("$.productId").value(supplyDto.getProductId()))
                .andExpect(jsonPath("$.quantitySupplied").value(supplyDto.getQuantitySupplied()));
    }

    @Test
    void testGetSupplyById() throws Exception {
        when(productSupplyService.getProductSupplyById(1L)).thenReturn(supplyDto);

        mockMvc.perform(get("/api/supplies/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.documentName").value(supplyDto.getDocumentName()))
                .andExpect(jsonPath("$.productId").value(supplyDto.getProductId()))
                .andExpect(jsonPath("$.quantitySupplied").value(supplyDto.getQuantitySupplied()));
    }

    @Test
    void testDeleteSupply() throws Exception {
        doNothing().when(productSupplyService).deleteProductSupply(1L);

        mockMvc.perform(delete("/api/supplies/{id}", 1L))
                .andExpect(status().isOk());

        Mockito.verify(productSupplyService, Mockito.times(1)).deleteProductSupply(1L);
    }

    @Test
    void testUpdateSupply() throws Exception {
        when(productSupplyService.updateProductSupply(eq(1L), any(ProductSupplyDto.class))).thenReturn(supplyDto);

        mockMvc.perform(patch("/api/supplies/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(supplyDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.documentName").value(supplyDto.getDocumentName()))
                .andExpect(jsonPath("$.productId").value(supplyDto.getProductId()))
                .andExpect(jsonPath("$.quantitySupplied").value(supplyDto.getQuantitySupplied()));
    }
}
