package com.project.fitstore.domain.product;

import com.project.fitstore.dtos.product.CreateProductDto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String brand;
    @Column(unique = true)
    private String sku;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private SubCategory subCategory;
    private BigDecimal price;

    public Product(CreateProductDto createProductDto) {
        this.name = createProductDto.name();
        this.sku = createProductDto.sku();
        this.brand = createProductDto.brand();
        this.category = createProductDto.category();
        this.subCategory = createProductDto.subCategory();
        this.price = createProductDto.price();
    }
}
