package com.abhishek.API;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Product {

          @Id
          private String id;
          private String name;
          private double price;

          @ElementCollection
          @CollectionTable(name = "product_colors", joinColumns = @JoinColumn(name = "product_id"))
          @Column(name = "color")
          private List<String> color;

          @ElementCollection
          @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
          @Column(name = "image_url")
          private List<String> image;

          @Column(name = "description", length = 1000)
          private String description;
          private String company;
          private String category;
          private boolean featured;
}
