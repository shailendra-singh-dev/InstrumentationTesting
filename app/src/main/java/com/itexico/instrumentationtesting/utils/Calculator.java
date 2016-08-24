package com.itexico.instrumentationtesting.utils;

/**
 * Created by iTexico Developer on 8/18/2016.
 */
public class Calculator {

    /**
     * Addition operation
     */
    public double addition(double firstOperand, double secondOperand) {
        return firstOperand + secondOperand;
    }

    /**
     * Substract operation
     */
    public double subtraction(double firstOperand, double secondOperand) {
        return firstOperand - secondOperand;
    }

    /**
     * Divide operation
     */
    public double division(double firstOperand, double secondOperand) {
        if (0 == secondOperand) {
            throw new IllegalArgumentException("secondOperand must be != 0, you cannot divide by zero");
        }
        return firstOperand / secondOperand;
    }

    /**
     * Multiply operation
     */
    public double multiply(double firstOperand, double secondOperand) {
        return firstOperand * secondOperand;
    }
}
