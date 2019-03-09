package com.example.calculadora;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by everis on 1/03/19.
 */
public class CalculatorPresenterTest {

    private CalculatorPresenterImp presenter;

    @Mock
    private CalculatorView mockedView;

    @Mock
    private Calculator mockedCalculator;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new CalculatorPresenterImp(mockedView,mockedCalculator);
    }

    @Test
    public void addSymbolShouldCallShowOperationsWhenInputIsValid() {
        when(mockedCalculator.addSymbol(anyString(), anyString())).thenReturn("+");
        presenter.addSymbol(anyString(), anyString());
        verify(mockedView, times(1)).showOperations(anyString());
        verify(mockedView, times(0)).showError();
    }

    @Test
    public void addSymbolShouldCallShowErrorWhenCalculatorThrowsAnOperationException() {
        when(mockedCalculator.addSymbol(anyString(), anyString())).thenThrow(OperationException.class);
        presenter.addSymbol(anyString(), anyString());
        verify(mockedView, times(0)).showOperations(anyString());
        verify(mockedView, times(1)).showError();
    }

    @Test
    public void addSymbolShouldCallShowErrorWhenCalculatorThrowsAnExpressionException() {
        when(mockedCalculator.addSymbol(anyString(), anyString())).thenThrow(ExpressionException.class);
        presenter.addSymbol(anyString(), anyString());
        verify(mockedView, times(0)).showOperations(anyString());
        verify(mockedView, times(1)).showError();
    }

    @Test
    public void removeSymbolShouldCallShowOperationsWhenInputIsValid() {
        when(mockedCalculator.removeSymbol(anyString())).thenReturn("");
        presenter.removeSymbol(anyString());
        verify(mockedView, times(1)).showOperations("");
        verify(mockedView, times(0)).showError();
    }

    @Test
    public void removeSymbolShouldCallErrorWhenCalculatorThrowsAnNullParamException() {
        when(mockedCalculator.removeSymbol(null)).thenReturn("");
        presenter.removeSymbol(null);
        verify(mockedView, times(0)).showOperations(anyString());
        verify(mockedView, times(1)).showError();
    }

    @Test
    public void cleanScreenShouldCallShowOperationsWhenInputIsValid() {
        presenter.clearScreen();
        verify(mockedView, times(1)).showOperations("");
    }

    @Test
    public void calculateShouldCallShowResultWhenInputIsValid() {
        when(mockedCalculator.calculate(anyString())).thenReturn("");
        presenter.calculate(anyString());
        verify(mockedView, times(1)).showResult(anyString());
        verify(mockedView, times(0)).showError();
        verify(mockedView, times(0)).showOperations(anyString());
    }

    @Test
    public void calculateShouldCallShowErrorWhenInputIsInvalid() {
        presenter.calculate(null);
        verify(mockedView, times(0)).showResult(anyString());
        verify(mockedView, times(1)).showError();
        verify(mockedView, times(0)).showOperations(anyString());
    }

    @Test
    public void calculateShouldCallShowErrorWhenRuntimeException() {
        when(mockedCalculator.calculate(anyString())).thenReturn("");
        presenter.calculate(null);
        verify(mockedView, times(0)).showResult(anyString());
        verify(mockedView, times(1)).showError();
        verify(mockedView, times(0)).showOperations(anyString());
    }


}
