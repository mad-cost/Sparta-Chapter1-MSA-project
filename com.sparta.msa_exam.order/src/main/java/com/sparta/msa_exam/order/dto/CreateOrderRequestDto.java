package com.sparta.msa_exam.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CreateOrderRequestDto {
  private String name;
  private List<Long> productIds = new ArrayList<>();
}
