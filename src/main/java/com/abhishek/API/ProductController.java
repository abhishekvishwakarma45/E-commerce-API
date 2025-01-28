package com.abhishek.API;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@RestController
@RequestMapping("/product")
public class ProductController {

          @Autowired
          ProductRepository productRepository;

          @Autowired
          private ProductService productService;

          @Autowired
          private Cloudinary cloudinary;

          @PostMapping("/add")
          public ResponseEntity<?> addProduct(
                              @RequestParam("id") String id,
                              @RequestParam("name") String name,
                              @RequestParam("price") double price,
                              @RequestParam("colors") List<String> colors,
                              @RequestParam("description") String description,
                              @RequestParam("company") String company,
                              @RequestParam("category") String category,
                              @RequestParam("featured") boolean featured,
                              @RequestParam("images") MultipartFile[] images) {
                    try {

                              List<String> imageUrls = new ArrayList<>();
                              for (MultipartFile image : images) {

                                        Map uploadResult = cloudinary.uploader().upload(image.getBytes(),
                                                            ObjectUtils.emptyMap());
                                        imageUrls.add(uploadResult.get("url").toString());
                              }

                              Product product = new Product();
                              product.setId(id);
                              product.setName(name);
                              product.setPrice(price);
                              product.setColor(colors);
                              product.setImage(imageUrls);
                              product.setDescription(description);
                              product.setCompany(company);
                              product.setCategory(category);
                              product.setFeatured(featured);

                              return productService.addProduct(product);
                    } catch (IOException e) {
                              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                                  .body(Map.of("status", "error", "message",
                                                                      "File upload failed: " + e.getMessage()));
                    }
          }

          @GetMapping("/get")
          public ResponseEntity<List<Product>> getProducts() {
                    return ResponseEntity.ok(productService.getAllProducts());
          }

          @GetMapping("/get/{id}")
          public ResponseEntity<Product> getProductById(@PathVariable String id) {
                    return ResponseEntity.of(Optional.ofNullable(productService.getProductById(id)));
          }

          @DeleteMapping("/remove/{id}")
          public ResponseEntity<String> deleteProductById(@PathVariable String id) {
                    productService.deleteProductById(id);
                    return ResponseEntity.ok("Product deleted successfully");
          }

          @PostMapping("/update/{id}")
          public ResponseEntity<String> updateProduct(@PathVariable String id,
                              @RequestParam("name") String name,
                              @RequestParam("price") double price,
                              @RequestParam("colors") List<String> colors,
                              @RequestParam("description") String description,
                              @RequestParam("company") String company,
                              @RequestParam("category") String category,
                              @RequestParam("featured") boolean featured,
                              @RequestParam("images") MultipartFile[] images) throws IOException {

                    List<String> imageUrls = new ArrayList<>();
                    for (MultipartFile image : images) {

                              Map uploadResult = cloudinary.uploader().upload(image.getBytes(),
                                                  ObjectUtils.emptyMap());
                              imageUrls.add(uploadResult.get("url").toString());
                    }

                    Product foundProduct = productRepository.findById(id).get();
                    if (foundProduct == null) {
                              return new ResponseEntity<>("product is not available", HttpStatus.NOT_FOUND);

                    }

                    foundProduct.setName(name);
                    foundProduct.setPrice(price);
                    foundProduct.setColor(colors);
                    foundProduct.setDescription(description);
                    foundProduct.setCompany(company);
                    foundProduct.setCategory(category);
                    foundProduct.setFeatured(featured);
                    foundProduct.setImage(imageUrls);

                    return productService.updateProduct(foundProduct);

          }
}
