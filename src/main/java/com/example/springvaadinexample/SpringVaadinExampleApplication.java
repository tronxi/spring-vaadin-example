package com.example.springvaadinexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
public class SpringVaadinExampleApplication {
  public static void main(String[] args) {
    SpringApplication.run(SpringVaadinExampleApplication.class, args);
  }

}
