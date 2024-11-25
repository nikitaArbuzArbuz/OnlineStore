package ru.moysklad.onlinestore.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "product_supplies", schema = "store")
public class ProductSupply {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_supplies_seq")
    @SequenceGenerator(name = "product_supplies_seq", sequenceName = "product_supplies_seq", schema = "store")
    @Column(name = "id")
    private Long id;

    @Column(name = "document_name")
    private String documentName;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity_supplied")
    private Integer quantitySupplied;
}
