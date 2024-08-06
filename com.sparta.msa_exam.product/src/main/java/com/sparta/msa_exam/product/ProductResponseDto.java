package com.sparta.msa_exam.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
  private Long product_id;
  private String name;
  private Integer supply_price;

  public ProductResponseDto(Product product) {
    this.product_id = product.getProduct_id();
    this.name = product.getName();
    this.supply_price = product.getSupply_price();
  }
}
