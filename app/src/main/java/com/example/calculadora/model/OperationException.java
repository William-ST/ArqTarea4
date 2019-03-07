package com.example.calculadora.model;


public class OperationException extends RuntimeException {

   public OperationException() {}
   public OperationException(String message) {
      super(message);
   }
}
