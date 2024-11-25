package ru.moysklad.onlinestore.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "product_sales", schema = "store")
public class ProductSale {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sales_seq")
    @SequenceGenerator(name = "product_sales_seq", sequenceName = "product_supplies_seq", schema = "store")
    @Column(name = "id")
    private Long id;

    @Column(name = "document_name")
    private String documentName;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity_sold")
    private Integer quantitySold;

    @Column(name = "purchase_cost", precision = 19, scale = 2)
    private BigDecimal purchaseCost;
}
