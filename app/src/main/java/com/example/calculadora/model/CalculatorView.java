package com.example.calculadora.model;


interface CalculatorView {

    void setPresenter(CalculatorPresenter presenter);

    void showOperations(String operations);

    void showResult(String result);

    void showError();
}