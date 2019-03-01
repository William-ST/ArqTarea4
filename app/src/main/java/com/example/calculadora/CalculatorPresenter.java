package com.example.calculadora;

/**
 * Created by everis on 1/03/19.
 */
public interface CalculatorPresenter {
    void addSymbol(String expression, String character); void removeSymbol(String expression);
    void clearScreen();
    void calculate(String expression);
}
