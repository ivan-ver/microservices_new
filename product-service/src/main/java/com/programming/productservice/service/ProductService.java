package com.programming.productservice.service;

import com.programming.productservice.dto.ProductRequestDto;
import com.programming.productservice.dto.ProductResponseDto;
import com.programming.productservice.model.Product;
import com.programming.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequestDto productRequestDto) {
        Product product = Product.builder()
                .name(productRequestDto.getName())
                .description(productRequestDto.getDescription())
                .price(productRequestDto.getPrice())
                .build();
        try {
            productRepository.save(product);
            log.info("Product {} is saved", product);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<ProductResponseDto> getAll() {
        List<Product> all = (List<Product>) productRepository.findAll();
        return all.stream().map(ProductResponseDto::new).toList();
    }
}
