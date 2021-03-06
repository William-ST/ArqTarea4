package com.example.calculadora;

import android.util.Log;

import javax.xml.validation.Validator;

/**
 * Created by William_ST on 26/02/19.
 */

public class MathOperation {

    public double addition(Double operand1, Double operand2) throws OperationException {
        throwsIfValuesAreInvalid(operand1, operand2);
        return operand1 + operand2;
    }

    public double substraction(double operand1, double operand2) throws OperationException {
        throwsIfValuesAreInvalid(operand1, operand2);
        return operand1 - operand2;
    }

    public double multiplication(double operand1, double operand2) throws OperationException {
        throwsIfValuesAreInvalid(operand1, operand2);
        return operand1 * operand2;
    }

    public double division(double operand1, double operand2) throws OperationException {
        throwsIfValuesAreInvalidDivision(operand1, operand2);
        return operand1 / operand2;
    }

    private void throwsIfExponentIsMaximumAllowedDouble(double exponent) {
        if (exponent >= Double.MAX_EXPONENT) {
            throw new OperationException();
        }
    }

    private void throwsIfValuesAreInvalid(Double... values) throws OperationException {
        if (values == null) throw new OperationException();
        for (Double value : values) {
            if (value == null || value == Double.MAX_VALUE || Double.isInfinite(value) || Double.isNaN(value))
                throw new OperationException();
        }
    }

    private void throwsIfValuesAreInvalidDivision(double dividend, double divider) throws OperationException {
        if (divider == 0)
            throw new OperationException();
        throwsIfValuesAreInvalid(dividend, divider);
    }

    public double exponentiation(double base, double exponent) throws OperationException {
        throwsIfExponentIsMaximumAllowedDouble(exponent);
        double result = 1;
        boolean expoNegative = exponent < 0;
        if (expoNegative)
            exponent = -exponent;
        while (exponent != 0) {
            result = multiplication(result, base);
            exponent--;
            throwsIfValuesAreInvalid(result);
        }
        return expoNegative ? (1 / result) : result;
    }

    public void validLimitProcess(double value) throws OperationException {
        if (value >= Double.MAX_VALUE) throw new OperationException();
    }

    public double squareRoot(double radicand) throws OperationException {
        validLimitProcess(radicand);
        throwsIfValuesAreInvalid(radicand);
        double aux;
        double squareRoot = radicand / 2;
        do {
            aux = squareRoot;
            squareRoot = (aux + (radicand / aux)) / 2;
            validLimitProcess(squareRoot);
            throwsIfValuesAreInvalid(squareRoot);
        } while (aux != squareRoot);
        return squareRoot;
    }

    public double factorial(double operand) throws OperationException{
        validLimitProcess(operand);
        throwsIfValuesAreInvalid(operand);
        double result = 1;
        while (operand > 0) {
            result *= operand;
            validLimitProcess(result);
            throwsIfValuesAreInvalid(result);
            operand--;
        }
        return result;
    }
}