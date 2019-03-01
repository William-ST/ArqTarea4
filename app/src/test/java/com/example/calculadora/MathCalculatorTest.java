package com.example.calculadora;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static com.google.common.truth.Truth.assertThat;
import static junitparams.JUnitParamsRunner.$;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by William_ST on 28/02/19.
 */
@RunWith(JUnitParamsRunner.class)
public class MathCalculatorTest {

    private MathCalculator mathCalculator;
    //private MathExpression mathExpression;
    //private MathOperation mathOperation;

    @Mock
    private Expression mockedExpression;

    @Mock
    private MathOperation mockedOperation;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        //mathExpression = new MathExpression();
        //mathOperation = new MathOperation();
        mathCalculator = new MathCalculator(mockedExpression, mockedOperation);
    }

    @Parameters(method = "getDataContainsParenthesis")
    @Test
    public void containsParenthesisReturnExpectedValue(String expression) {
        assertThat(mathCalculator.containsParenthesis(expression)).isTrue();
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
        assertThat(mathCalculator.getParenthesisExpression(expression)).isEqualTo(result);
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
        assertThat(mathCalculator.replaceParenthesis(from, with)).isEqualTo(result);
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
        assertThat(mathCalculator.isAnOperator(symbol)).isTrue();
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
        assertThat(mathCalculator.isAnOperator(symbol)).isFalse();
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


    @Test
    @Parameters(method = "addSymbolData")
    public void addSymbolShouldCallAddSymbolInExpression(String to, String symbol) {
        //Arrange
        MockExpression mockedExpression = new MockExpression();
        MathOperation dummyOperation = null;
        MathCalculator calculator = new MathCalculator(mockedExpression, dummyOperation);
        //Act
        calculator.addSymbol(to, symbol);
        //Assert
        assertThat(mockedExpression.symbolAdded).isTrue();
        assertThat(mockedExpression.symbolReplaced).isFalse();
    }

    private Object[] addSymbolData() {
        return $($("", "-"),
                $("2", "+"),
                $("5", "."),
                $("4.3", "f"));
    }

    @Test
    @Parameters(method = "replaceSymbolData")
    public void addSymbolShouldCallReplaceSymbolInExpression(String to, String symbol) {
        //Arrange
        MockExpression mockedExpression = new MockExpression();
        MathOperation dummyOperation = null;
        MathCalculator calculator = new MathCalculator(mockedExpression, dummyOperation);
        //Act
        calculator.addSymbol(to, symbol);
        //Assert
        assertThat(mockedExpression.symbolAdded).isFalse();
        assertThat(mockedExpression.symbolReplaced).isTrue();
    }

    private Object[] replaceSymbolData() {
        return $($("-", "+"),
                $("-5+", "x"),
                $("3x4/", "f"),
                $("3sqrt", "x"),
                $("3.", "/"));
    }

    @Test
    @Parameters(method = "removeSymbolData")
    public void removeSymbolShouldCallRemoveSymbolInExpression(String from) {
        //Arrange
        MockExpression mockedExpression = new MockExpression();
        MathOperation dummyOperation = null;
        MathCalculator calculator = new MathCalculator(mockedExpression, dummyOperation);
        //Act
        calculator.removeSymbol(from);
        //Assert
        assertThat(mockedExpression.symbolRemove).isTrue();
        assertThat(mockedExpression.symbolAdded).isFalse();
        assertThat(mockedExpression.symbolReplaced).isFalse();
    }

    private Object[] removeSymbolData() {
        return $($("-"),
                $("-5+"),
                $("3x4/"),
                $("3sqrt"),
                $("3.")
        );
    }

    @Test
    @Parameters(method = "addSymbolData")
    public void addSymbolShouldCallAddSymbol(String to, String symbol) {
        mathCalculator.addSymbol(to, symbol);
        verify(mockedExpression, times(1)).addSymbol(anyString(), anyString());
        verify(mockedExpression, times(0)).replaceSymbol(anyString(), anyString());
    }

    @Test
    @Parameters(method = "replaceSymbolData")
    public void addSymbolShouldCallReplaceSymbol(String to, String symbol) {
        mathCalculator.addSymbol(to, symbol);
        verify(mockedExpression, times(1)).replaceSymbol(anyString(), anyString());
        verify(mockedExpression, times(0)).addSymbol(anyString(), anyString());
    }

    @Test
    @Parameters(method = "resolveData")
    public void resolveShouldReturnExpectedExpression(String from, String[] tokens, String expected) {
        MathOperation operation = new MathOperation();
        MathCalculator calculator = new MathCalculator(mockedExpression, operation);
        when(mockedExpression.tokenize(from)).thenReturn(tokens);
        assertThat(calculator.resolve(from)).isEqualTo(expected);
    }

    private Object[] resolveData() {
        return $($("", new String[]{""}, ""),
                $("-", new String[]{"-"}, "0"),
                $("-5", new String[]{"-5"}, "-5"),
                $("3.4", new String[]{"3.4"}, "3.4"),
                $("3-2", new String[]{"3", "-2"}, "1"),
                $("3+2", new String[]{"3", "+", "2"}, "5"),
                $("f3", new String[]{"f", "3"}, "6"),
                $("2f3", new String[]{"2", "f", "3"}, "12"),
                $("9/r9", new String[]{"9", "/", "r", "9"}, "3"),
                $("3^f3", new String[]{"3", "^", "f", "3"}, "729"),
                $("3--4", new String[]{"3", "-", "-4"}, "7"));
    }

    @Test
    @Parameters(method = "resolveAdditionData")
    public void resolveShouldCallXTimesAdditionMethod(String from, String[] tokens, int times) {
        when(mockedExpression.tokenize(from)).thenReturn(tokens);
        mathCalculator.resolve(from);
        verify(mockedOperation, times(times)).addition(anyDouble(), anyDouble());
    }

    private Object[] resolveAdditionData() {
        return $(
                $("2+2", new String[]{"2", "+", "2"}, 2),
                $("", new String[]{""}, 0),
                $("2", new String[]{"2"}, 1),
                $("2+2x3-5", new String[]{"2", "+", "2", "x", "3", "-5"}, 3));
    }
}