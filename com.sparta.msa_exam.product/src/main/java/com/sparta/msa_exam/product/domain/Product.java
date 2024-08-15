package com.sparta.msa_exam.product.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long product_id;

  private String name;
  private Integer supply_price;

  public static Product create(String name, Integer supply_price) {
    return Product.builder()
            .name(name)
            .supply_price(supply_price)
            .build();
  }

}
