package com.sparta.msa_exam.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
