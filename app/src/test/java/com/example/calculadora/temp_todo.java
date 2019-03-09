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
public class temp_todo {

    private MathCalculator mathCalculator;
    private MathExpression mathExpression;
    private MathOperation mathOperation;

    @Before
    public void setUp() throws Exception {
        mathExpression = new MathExpression();
        mathOperation = new MathOperation();
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

    @Parameters(method = "getDataReturnExpectedValueRemoveSymbol")
    @Test
    public void removeSymbolShouldReturnExpectedValue(String from, String result) {
        Truth.assertThat(mathCalculator.removeSymbol(from)).isEqualTo(result);
    }

    private Object[] getDataReturnExpectedValueRemoveSymbol() {
        return $(
                $("4  +  3  -", "4 + 3"),
                $("8  -  2  *", "8 - 2"),
                $("4  +  3  fact (", "4 + 3 fact "),
                $("4  *", "4")
        );
    }

    private Object[] getDataCalculateReturnExpectedValue() {
        return $(
                $("4  +  3  -", "7"),
                $("8  -  2  *", "6"),
                $("4  +  3  fact (", "7"),
                $("4  *", "4")
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
