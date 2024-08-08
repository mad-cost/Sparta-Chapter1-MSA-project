package com.sparta.msa_exam.order.controller;

import com.sparta.msa_exam.order.dto.OrderRequestDto;
import com.sparta.msa_exam.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController {
  private final OrderService orderService;

  @PostMapping("/order")
  public void createOrder(
          @RequestBody
          OrderRequestDto requestDto
  ){
    orderService.createOrder(requestDto);
  }

}
