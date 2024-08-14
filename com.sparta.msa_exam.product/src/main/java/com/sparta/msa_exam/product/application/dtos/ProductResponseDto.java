package com.sparta.msa_exam.product.application.dtos;

import com.sparta.msa_exam.product.domain.Product;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProductResponseDto implements Serializable{
  private Long product_id;
  private String name;
  private Integer supply_price;

  public ProductResponseDto(Product product) {
    this.product_id = product.getProduct_id();
    this.name = product.getName();
    this.supply_price = product.getSupply_price();
  }
}
