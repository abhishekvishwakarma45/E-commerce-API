package com.abhishek.API;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
// @RequestMapping("/product")
public class ProductController {

          @Autowired
          private ProductService productService;

          @PostMapping("/add")
          public ResponseEntity<?> addProduct(@RequestBody Product product, Principal principal) {

                    return productService.addProduct(product);

          }

          @GetMapping("/home")
          public String home() {
                    return "welcome page";
          }

}
