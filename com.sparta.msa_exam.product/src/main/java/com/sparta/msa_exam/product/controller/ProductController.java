package com.sparta.msa_exam.product.controller;

import com.sparta.msa_exam.product.application.dtos.ProductRequestDto;
import com.sparta.msa_exam.product.application.dtos.ProductResponseDto;
import com.sparta.msa_exam.product.application.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j(topic = "ProductController")
@RestController
@RequiredArgsConstructor
public class ProductController {
  private final ProductService productService;

  @Value("${server.port}") // 애플리케이션이 실행 중인 포트를 주입받습니다.
  private String serverPort;

  @PostMapping("/products")
  public void createProducts(
          @RequestBody
          ProductRequestDto requestDto
  ){
    productService.createProducts(requestDto);
  }

  @GetMapping("/products")
  private List<ProductResponseDto> findAllProducts(){
      List<ProductResponseDto> responseDto = productService.findAll();
      // 라운드로빈 형식으로 로드밸런싱 구현
      log.info("상품 목록을 조회시 포트 번호 : " + serverPort);
      return  responseDto;
  }

  @GetMapping("/products/{id}")
  public Long findProduct(
          @PathVariable("id")
          Long id
  ) {
    return productService.findById(id);
  }

}
