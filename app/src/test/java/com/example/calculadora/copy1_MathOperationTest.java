package com.example.calculadora;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by William_ST on 26/02/19.
 */
public class copy1_MathOperationTest {

    @Test
    public void additionShouldReturnTenWhenOperandsAreThreeAndSeven() {
        //Arrange
        MathOperation operation = new MathOperation();
        double expectedValue = 10;
        double result = 0;
        //Act
        result = operation.addition(3, 7);
        //Assert
        assertEquals(String.format("Resultado %1s ha de ser igual a %2s", result, expectedValue), expectedValue, result, 0.0);
    }
}