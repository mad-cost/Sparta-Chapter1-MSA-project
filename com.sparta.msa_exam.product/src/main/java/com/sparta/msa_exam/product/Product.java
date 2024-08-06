package com.sparta.msa_exam.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long product_id;

  private String name;
  private Integer supply_price;

  public Product(ProductRequestDto requestDto) {
    this.name = requestDto.getName();
    this.supply_price = requestDto.getSupply_price();
  }
}
