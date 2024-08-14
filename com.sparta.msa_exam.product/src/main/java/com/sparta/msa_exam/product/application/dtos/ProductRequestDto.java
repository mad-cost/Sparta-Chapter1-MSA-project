package com.sparta.msa_exam.product.application.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
  private String name;
  private Integer supply_price;
}
