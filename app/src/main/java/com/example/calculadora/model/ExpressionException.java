package com.example.calculadora.model;


public class ExpressionException extends RuntimeException {

   public ExpressionException() {}
   public ExpressionException(String message) {
      super(message);
   }
}
