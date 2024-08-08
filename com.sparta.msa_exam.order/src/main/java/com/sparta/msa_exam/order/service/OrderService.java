package com.sparta.msa_exam.order.service;

import com.sparta.msa_exam.order.ProductClient;
import com.sparta.msa_exam.order.dto.OrderRequestDto;
import com.sparta.msa_exam.order.entity.Order;
import com.sparta.msa_exam.order.entity.OrderProduct;
import com.sparta.msa_exam.order.repository.OrderProductRepository;
import com.sparta.msa_exam.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
  private final OrderRepository orderRepository;
  private final OrderProductRepository orderProductRepository;

  private final ProductClient productClient;

  public Long getProductInfo(Long productId) {
    return productClient.getProduct(productId);
  }

  @Transactional
  public void createOrder(OrderRequestDto requestDto) {
    List<Long> productIds = requestDto.getProductIds();

    Order order = new Order();
    order.setName(requestDto.getName());
    orderRepository.save(order);

    // requestDto로 받은 제품의 id가 존재하는지 검증하고, 데이터 넣어주기
    for (Long productId : productIds) {
      Long checkedId = getProductInfo(productId);
      OrderProduct orderProduct = new OrderProduct(order, checkedId);
      orderProductRepository.save(orderProduct);
    }

  }
}
