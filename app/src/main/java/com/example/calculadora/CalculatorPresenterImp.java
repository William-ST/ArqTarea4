package com.example.calculadora;

import android.text.TextUtils;

/**
 * Created by everis on 1/03/19.
 */
public class CalculatorPresenterImp implements CalculatorPresenter {

    private CalculatorView view;
    private Calculator calculator;

    public CalculatorPresenterImp(CalculatorView view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
    }

    @Override
    public void addSymbol(String expression, String symbol) {
        try {
            view.showOperations(calculator.addSymbol(expression, symbol));
        } catch (OperationException | ExpressionException exception) {
            view.showError();
        }
    }

    @Override
    public void removeSymbol(String expression) {
        if (expression == null) {
            view.showError();
            return;
        }
        view.showOperations(calculator.removeSymbol(expression));
    }

    @Override
    public void clearScreen() {
        view.showOperations("");
    }

    @Override
    public void calculate(String expression) {
        if (expression == null) {
            view.showError();
            return;
        }
        try {
            view.showResult(calculator.calculate(expression));
        } catch (RuntimeException e) {
            view.showError();
        }
    }
}
