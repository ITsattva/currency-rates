package com.currency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TagSoftTestTaskApplication {

  public static void main(String[] args) {
    SpringApplication.run(TagSoftTestTaskApplication.class, args);
  }
}
