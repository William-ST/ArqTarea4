package com.example.calculadora.model;


public interface CalculatorPresenter {

    void addSymbol(String expression, String character);

    void removeSymbol(String expression);

    void clearScreen();

    void calculate(String expression);
}
