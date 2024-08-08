package com.sparta.msa_exam.order.controller;

import com.sparta.msa_exam.order.dto.CreateOrderRequestDto;
import com.sparta.msa_exam.order.dto.OrderResponseDto;
import com.sparta.msa_exam.order.dto.PutOrderRequestDto;
import com.sparta.msa_exam.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController {
  private final OrderService orderService;

  @PostMapping("/order")
  public void createOrder(
          @RequestBody
          CreateOrderRequestDto requestDto
  ){
    orderService.createOrder(requestDto);
  }

  @PutMapping("/order/{orderId}")
  public void putOrder(
          @PathVariable("orderId")
          Long orderId,
          @RequestBody
          PutOrderRequestDto requestDto
  ){
    orderService.putOrder(orderId, requestDto);
  }

  @GetMapping("/order/{orderId}")
  public OrderResponseDto getOrder(
          @PathVariable("orderId")
          Long orderId
  ){
    OrderResponseDto responseDto = orderService.getOrder(orderId);
    return responseDto;
  }

}
