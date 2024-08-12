package com.sparta.msa_exam.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;

  /*  @CacheEvict: createProducts 호출시 "productList_cache" 캐시를 삭제한다.
      productRepository.save 를 통하여 DB에 새로운 데이터 저장.
      이후 @Cacheable이 선언된 findAll 를 호출시, 방금 저장한 새로운 데이터가 포함되어 캐시에 등록된다.
   */
  @CacheEvict(cacheNames = "productList_cache", allEntries = true)
  public void createProducts(ProductRequestDto requestDto) {
    productRepository.save(new Product(requestDto));
  }

  @Cacheable(cacheNames = "productList_cache") // Cache-Aside로 캐시에 등록
  public List<ProductResponseDto> findAll() {
    List<Product> products = productRepository.findAll();
    List<ProductResponseDto> responseDtos = new ArrayList<>();

    for (Product product : products){
      responseDtos.add(new ProductResponseDto(product));
    }
    return responseDtos;
  }

  public Long findById(Long productId) {
    Product product = productRepository.findById(productId).orElse(null);
    if (product != null) {
      return product.getProduct_id();
    } else {
      return null;
    }
  }


}
