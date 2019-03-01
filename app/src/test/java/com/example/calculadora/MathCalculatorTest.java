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
        mathOperation = new MathOperation();
        mathCalculator = new MathCalculator(mathExpression, mathOperation);
    }

    @Parameters(method = "getDataContainsParenthesis")
    @Test
    public void containsParenthesisReturnExpectedValue(String expression) {
        Truth.assertThat(mathCalculator.containsParenthesis(expression)).isTrue();
    }

    private Object[] getDataContainsParenthesis() {
        return $(
                $("(  2  +  2  )"),
                $("( 5 * 2 )"),
                $("(10 / 2)"),
                $("(2^4)"),
                $("(4-2)")
        );
    }

    @Parameters(method = "getDataCleanParenthesisExpression")
    @Test
    public void getParenthesisExpressionReturnExpectedValue(String expression, String result) {
        Truth.assertThat(mathCalculator.getParenthesisExpression(expression)).isEqualTo(result);
    }

    private Object[] getDataCleanParenthesisExpression() {
        return $(
                $("(  2  +  2  )", "2  +  2"),
                $("( 5 * 2 )", "5 * 2"),
                $("(10 / 2)", "10 / 2"),
                $("(2^4)", "2^4"),
                $("(4-2)", "4-2")
        );
    }

    @Parameters(method = "getDataReplaceParenthesis")
    @Test
    public void replaceParenthesisReturnExpectedValue(String from, String with, String result) {
        Truth.assertThat(mathCalculator.replaceParenthesis(from, with)).isEqualTo(result);
    }

    private Object[] getDataReplaceParenthesis() {
        return $(
                $("( 7 ) + ", "2", "2x + "),
                $("( 4 + 5 ) ", "10", "10x "),
                $("( 7 ) + 2", "20", "20x + 2"),
                $("2 + ( 10 ) + 2", "20", "2 + x20x + 2"),
                $("4 ( 7 )", "5", "4 x5")
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
