package com.abhishek.API;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class APIController {

          @GetMapping("/home")
          public String Home() {
                    return "WELCOME TO HOME PAGE";
          }

}
