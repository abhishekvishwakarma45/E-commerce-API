package com.abhishek.API;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class Config {

          @Bean
          public WebMvcConfigurer configurer() {
                    return new WebMvcConfigurer() {
                              @Override
                              public void addCorsMappings(CorsRegistry registry) {
                                        registry.addMapping("/**")
                                                            .allowedOrigins("*")
                                                            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                                                            .allowedHeaders("*")
                                                            .allowCredentials(false);

                              }
                    };
          }

          @Bean
          Cloudinary getCloudinary() {
                    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                                        "cloud_name", "dqktbs8zx",
                                        "api_key", "682372517532291",
                                        "api_secret", "JaWEZt2CPRPCEl538TTYVGpSFHI",
                                        "secure", true));
                    return cloudinary;
          }

}
