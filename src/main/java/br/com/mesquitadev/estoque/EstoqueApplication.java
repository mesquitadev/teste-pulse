package br.com.mesquitadev.estoque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"br.com.mesquitadev.estoque.*", "org.springdoc"})
public class EstoqueApplication {
  public static void main(String[] args) {
    SpringApplication.run(EstoqueApplication.class, args);
  }
}