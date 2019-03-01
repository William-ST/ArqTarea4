package com.example.calculadora;

import com.google.common.truth.Truth;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 * Created by William_ST on 26/02/19.
 */
@RunWith(JUnitParamsRunner.class)
public class MathOperationTest {

    private MathOperation mathOperation;

    @Before
    public void setUp() {
        mathOperation = new MathOperation();
    }

    @Parameters(method = "getValidAdditionInput")
    @Test
    public void additionShouldReturnExpectedValueWhenOperandsAreRealNumbers
            (double operand1, double operand2, double expectedValue) {
        // ARRANGE
        // ACT
        double result = mathOperation.addition(operand1, operand2);
        //ASSERT
        assertEquals(String.format("Resultado %1s ha de ser igual a %2s", result, expectedValue), expectedValue, result, 0.0);
    }

    private Object[] getValidAdditionInput() {
        return new Object[]{
                new Object[]{1, 1, 2},
                new Object[]{2, -2, 0},
                new Object[]{-3.5, -2.7, -6.2}};
    }

    @Parameters(method = "getInvalidInput")
    @Test(expected = OperationException.class)
    public void additionShouldThrowsWhenValuesAreInvalid(double operand1, double operand2) {
        // ARRANGE
        // ACT
        double result = mathOperation.addition(operand1, operand2);
    }

    private Object[] getInvalidInput() {
        return new Object[]{
                new Object[]{12, Double.MAX_VALUE},
                new Object[]{Double.POSITIVE_INFINITY, 1},
                new Object[]{-12.3, Double.NEGATIVE_INFINITY}, new Object[]{Double.NaN, 12},
                new Object[]{Math.pow(2, 1024), -1}
        };
    }

    @Parameters(method = "getValidSubstractionInput")
    @Test
    public void substractionShouldReturnExpectedValueWhenOperandsAreRealNumbers(double operand1, double operand2, double expectedValue) {
        // ARRANGE
        // ACT
        double result = mathOperation.substraction(operand1, operand2);
        //ASSERT
        assertEquals(String.format("Resultado %1s ha de ser igual a %2s", result, expectedValue), expectedValue, result, 0.0);
    }

    private Object[] getValidSubstractionInput() {
        return new Object[]{
                new Object[]{10, 4, 6},
                new Object[]{-10, 5, -15},
                new Object[]{-3.5, 2.5, -6}};
    }

    @Parameters(method = "getValidMultiplicationInput")
    @Test
    public void multiplicationShouldReturnExpectedValueWhenOperandsAreRealNumbers(double operand1, double operand2, double expectedValue) {
        // ARRANGE
        // ACT
        double result = mathOperation.multiplication(operand1, operand2);
        //ASSERT
        assertEquals(String.format("Resultado %1s ha de ser igual a %2s", result, expectedValue), expectedValue, result, 0.0);
    }

    private Object[] getValidMultiplicationInput() {
        return new Object[]{
                new Object[]{2, 9, 18},
                new Object[]{-1, 20, -20},
                new Object[]{-5, 2.5, -12.5}};
    }

    @Parameters(method = "getValidDivisionInput")
    @Test
    public void divisionShouldReturnExpectedValueWhenOperandsAreRealNumbers(double operand1, double operand2, double expectedValue) {
        // ARRANGE
        // ACT
        double result = mathOperation.division(operand1, operand2);
        //ASSERT
        assertEquals(String.format("Resultado %1s ha de ser igual a %2s", result, expectedValue), expectedValue, result, 0.0);
    }

    private Object[] getValidDivisionInput() {
        return new Object[]{
                new Object[]{10, 2, 5.0},
                new Object[]{45, 5, 9.0},
                new Object[]{-100, 25, -4.0}};
    }

    @Parameters(method = "getValidDivisionInvalidInput")
    @Test(expected = OperationException.class)
    public void divisionShouldReturnExpectedValueWhenOperandsAreRealInvalid(double operand1, double operand2, double expectedValue) {
        // ARRANGE
        // ACT
        double result = mathOperation.division(operand1, operand2);
        //ASSERT
        assertEquals(String.format("Resultado %1s ha de ser igual a %2s", result, expectedValue), expectedValue, result, 0.0);
    }

    private Object[] getValidDivisionInvalidInput() {
        return new Object[]{
                new Object[]{10, 0, 5.0},
                new Object[]{45, 0, 9.0},
                new Object[]{-100, 0, -4.0}};
    }

    @Parameters(method = "getValidExponentiationInput")
    @Test
    public void exponentiationShouldReturnExpectedValueWhenInputAreIntegers(double base, double exponent, double expectedValue) {

        double result = mathOperation.exponentiation(base, exponent);
        Truth.assertThat(result).isWithin(0.00000000001).of(expectedValue);
        //assertThat(result, equalTo(expectedValue));
    }

    private Object[] getValidExponentiationInput() {
        return $(
                $(0, 0, 1),
                $(2, 0, 1),
                $(2, 1, 2),
                $(2.3, 5, 64.36343),
                $(-3, 4, 81),
                $(-3, 3, -27),
                $(2, -2, 0.25), //Añade este elemento
                $(-3, -5, -0.00411522633) //Añade este elemento
        );
    }


    @Parameters(method = "getInvalidExponentiationInput")
    @Test(expected = OperationException.class, timeout = 100)
    public void exponentiationShouldThrowWhenInputIsInvalid(double base, double exponent) {
        mathOperation.exponentiation(base, exponent);
    }

    private Object[] getInvalidExponentiationInput() {
        return $(
                $(Double.NEGATIVE_INFINITY, 2),
                $(-3, Double.POSITIVE_INFINITY),
                $(Double.NaN, -1),
                $(2, Double.MAX_VALUE),
                $(2, 1024),
                $(5, 3.3),     //Añade este elemento
                $(-3, -1.2),  //Añade este elemento;
                $( 0, 100000000),
                $( 1, 100000000));
    }
}