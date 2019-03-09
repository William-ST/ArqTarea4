package com.example.calculadora;

import android.support.annotation.NonNull;
import android.text.TextUtils;

/**
 * Created by William_ST on 27/02/19.
 */

public class MathExpression implements Expression {

    static final String ADDITION = "+";
    static final String SUBTRACTION = "-";
    static final String MULTIPLICATION = "x";
    static final String DIVISION = "/";
    static final String EXPONENTIATION = "^";
    static final String SQUARE_ROOT = "r";
    static final String SQUARE_ROOT_SCREEN = "sqrt";
    static final String FACTORIAL = "f";
    static final String FACTORIAL_SCREEN = "fact";

    @Override
    public String read(@NonNull String expression) throws ExpressionException {
        return expression.replace(SQUARE_ROOT_SCREEN, SQUARE_ROOT)
                .replace(FACTORIAL_SCREEN, FACTORIAL)
                .replaceAll("\\s", "");
    }

    @Override
    public String write(@NonNull String expression) {
        return expression.replaceAll("(?<=[-fr+x/^)])|(?=[-fr+x/^(])", "$0 ")
                .replace(SQUARE_ROOT, SQUARE_ROOT_SCREEN).replace(FACTORIAL, FACTORIAL_SCREEN);
    }

    @Override
    public String addSymbol(@NonNull String expression, @NonNull String symbol) throws ExpressionException {
        throwsIfSymbolIsInvalid(symbol);
        return write(read(expression).concat(symbol));
    }

    @Override
    public String removeSymbol(String expression) throws ExpressionException {
        if (expression == null) throw new ExpressionException();
        expression = read(expression);
        int START_INDEX = 0;
        int END_INDEX = expression.length() - 1;
        return expression.isEmpty() ? expression : write(expression.substring(START_INDEX, END_INDEX));
    }

    @Override
    public String replaceSymbol(String expression, String symbol) throws ExpressionException {
        if (expression == null || symbol == null) throw new ExpressionException();
        if (expression.length() > 1) {
            expression = removeSymbol(expression);
            return write(expression.concat(symbol));
        }
        return write(expression.concat(symbol));
    }

    @Override
    public String[] tokenize(String expression) {
        if (expression == null) throw new ExpressionException();
        return expression.split("(?<=[fr+x/^])|(?=[-fr+x/^])");
    }

    private void throwsIfSymbolIsInvalid(String expression) throws ExpressionException {
        if (expression == null) {
            throw new ExpressionException(String.format("expression %s is null/empty", expression));
        }
        for (char symbol : expression.toCharArray()) {
            if (isSymbolInvalid(String.valueOf(symbol))) {
                throw new ExpressionException(
                        String.format("symbol %s is invalid", symbol));
            }
        }
    }

    private boolean isSymbolInvalid(String symbol) {
        return !symbol.matches("([0-9]|[-+x/]|[.]|[()^fr])");
    }
}