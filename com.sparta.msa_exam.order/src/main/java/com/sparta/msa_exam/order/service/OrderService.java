package com.sparta.msa_exam.order.service;

import com.sparta.msa_exam.order.ProductClient;
import com.sparta.msa_exam.order.dto.CreateOrderRequestDto;
import com.sparta.msa_exam.order.dto.OrderResponseDto;
import com.sparta.msa_exam.order.dto.PutOrderRequestDto;
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


@Slf4j(topic = "OrderService")
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
  public void createOrder(CreateOrderRequestDto requestDto) {
    List<Long> productIds = requestDto.getProductIds();

    Order order = new Order();
    order.setName(requestDto.getName());
    orderRepository.save(order);

    // 상품이 전부 존재할 경우 주문에 상품을 추가하고, 존재하지 않는다면 주문에 저장하지 않고 에러 발생.
    for (Long productId : productIds) {
      Long checkedId = getProductInfo(productId);
      if (checkedId != null){
        OrderProduct orderProduct = new OrderProduct(order, checkedId);
        orderProductRepository.save(orderProduct);
      }else {
        throw new IllegalArgumentException("OrderService 존재하지 않는 상품 번호 : " + productId);
      }

    }

  }

  public void putOrder(Long orderId, PutOrderRequestDto requestDto) {
    Order order = orderRepository.findById(orderId).orElseThrow(()->
            new IllegalArgumentException("OrderService orderId가 존재하지 않습니다"));

    Long product = requestDto.getProductId();
    Long checkedId = getProductInfo(product);

    // 존재할 경우 주문에 상품을 추가하고, 존재하지 않는다면 주문에 저장하지 않음.
    if (checkedId != null){
      OrderProduct orderProduct = new OrderProduct(order, checkedId);
      orderProductRepository.save(orderProduct);
    }else {
      // 존재 하지 않을경우 로그로 알려주기
      log.info("주문에 추가하고싶은 상품이 존재하지 않습니다.");
    }
  }

  public OrderResponseDto getOrder(Long orderId) {
    Order order = orderRepository.findById(orderId).orElseThrow(()->
            new IllegalArgumentException("OrderService 조회하는 orderId가 존재하지 않습니다"));

    List<OrderProduct> orderProducts = orderProductRepository.findByOrder(order);

    // 제품 아이디 int로 바꿔주기
    List<Integer> productIds = new ArrayList<>();
    for (OrderProduct orderProduct : orderProducts){
      productIds.add(orderProduct.getProductId().intValue());
    }

    // 반환 값 저장
    OrderResponseDto responseDto = new OrderResponseDto();
    responseDto.setOrderId(orderId);
    responseDto.setProductIds(productIds);

    return  responseDto;
  }

}
