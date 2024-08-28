package br.com.mesquitadev.estoque.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;

public class Utils {
  public static void checkThrow(Boolean expression, HttpStatus status, String message) {
    if(expression) {
      throw new ResponseStatusException(status, message);
    }
  }
}


