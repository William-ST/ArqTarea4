package com.example.calculadora;

/**
 * Created by everis on 1/03/19.
 */
public interface CalculatorView {
    void setPresenter(CalculatorPresenter presenter); void showOperations(String operations);
    void showResult(String result);
    void showError();
}
