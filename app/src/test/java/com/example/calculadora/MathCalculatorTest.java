package com.example.calculadora;

import com.google.common.truth.Truth;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static junitparams.JUnitParamsRunner.$;

/**
 * Created by William_ST on 28/02/19.
 */
@RunWith(JUnitParamsRunner.class)
public class MathCalculatorTest {

    private MathCalculator mathCalculator;
    private MathExpression mathExpression;
    private MathOperation mathOperation;

    @Before
    public void setUp() throws Exception {
        mathExpression = new MathExpression();
        mathCalculator = new MathCalculator(mathExpression, mathOperation);
    }

    @Parameters(method = "getDataReturnExpectedValueAddOperationIsUnaryOrBinary")
    @Test
    public void addSymbolShouldReturnExpectedValueWhenLastOperatorIsUnaryOrBinary(String to,
        String symbol, String expectedValue) {
        Truth.assertThat(mathCalculator.addSymbol(to, symbol)).isEqualTo(expectedValue);
    }

    private Object[] getDataReturnExpectedValueAddOperationIsUnaryOrBinary() {
        return $(
                $("4  +  3  -", "+", "4  +  3 + "),
                $("4  +  3  -", "^", "4  +  3 ^ "),
                $("4  +  3  -", "f", "4  +  3 fact ("),
                $("4  *", "/", "4* / ")
        );
    }

    @Parameters(method = "getDataReturnExpectedValueAddOperationIsUnaryOrBinary")
    @Test
    public void removeSymbolShouldReturnExpectedValue(String from) {
        Truth.assertThat(mathCalculator.removeSymbol(from)).(expectedValue);
    }

    private Object[] getDataReturnExpectedValueAddOperationIsUnaryOrBinary() {
        return $(
                $("4  +  3  -", "+", "4  +  3 + "),
                $("4  +  3  -", "^", "4  +  3 ^ "),
                $("4  +  3  -", "f", "4  +  3 fact ("),
                $("4  *", "/", "4* / ")
        );
    }




    @Parameters(method = "getDataIsAnOperatorReturnTrue")
    @Test
    public void isAnOperatorShouldReturnTrue(String symbol) {
        Truth.assertThat(mathCalculator.isAnOperator(symbol)).isTrue();
    }

    private Object[] getDataIsAnOperatorReturnTrue() {
        return $(
                $(MathExpression.SQUARE_ROOT),
                $(MathExpression.FACTORIAL),
                $(MathExpression.ADDITION),
                $(MathExpression.SUBTRACTION),
                $(MathExpression.MULTIPLICATION),
                $(MathExpression.DIVISION),
                $(MathExpression.EXPONENTIATION)
        );
    }

    @Parameters(method = "getDataIsAnOperatorReturnFalse")
    @Test
    public void isAnOperatorShouldReturnFalse(String symbol) {
        Truth.assertThat(mathCalculator.isAnOperator(symbol)).isFalse();
    }

    private Object[] getDataIsAnOperatorReturnFalse() {
        return $(
                $(MathExpression.FACTORIAL_SCREEN),
                $(MathExpression.SQUARE_ROOT_SCREEN),
                $("."),
                $("#"),
                $("_")
        );
    }

}
