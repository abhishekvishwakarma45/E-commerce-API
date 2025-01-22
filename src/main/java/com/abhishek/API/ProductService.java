package com.abhishek.API;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

          @Autowired
          private ProductRepository productRepository;

          public ResponseEntity<?> addProduct(Product product) {
                    productRepository.save(product);
                    return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully");
          }

          public List<Product> getAllProducts() {
                    return productRepository.findAll();
          }

          public Product getProductById(String id) {
                    return productRepository.findById(id).orElse(null);
          }

          public void deleteProductById(String id) {
                    productRepository.deleteById(id);
          }
}
