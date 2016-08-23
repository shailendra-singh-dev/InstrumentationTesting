package com.itexico.instrumentationtesting.androidjunitrunner.suite;

import android.support.test.filters.SmallTest;

import com.itexico.instrumentationtesting.androidjunitrunner.Calculator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by iTexico Developer on 8/18/2016.
 */
@RunWith(Parameterized.class)
@SmallTest
public class CalculatorParamerizedTest {
    /**
     * @return {@link Iterable} that contains the values that should be passed to the constructor.
     * In this example we are going to use three parameters: operand one, operand two and the
     * expected result.
     */
    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, 0, 0},
                {0, -1, -1},
                {2, 2, 4},
                {8, 8, 16},
                {16, 16, 32},
                {32, 0, 32},
                {64, 64, 128}});
    }

    private final double mOperandOne;
    private final double mOperandTwo;
    private final double mExpectedResult;

    private Calculator mCalculator;

    /**
     * Constructor that takes in the values specified in
     * {@link CalculatorParamerizedTest#data()}. The values need to be saved to fields in order
     * to reuse them in your tests.
     */
    public CalculatorParamerizedTest(double operandOne, double operandTwo,
                                     double expectedResult) {
        mOperandOne = operandOne;
        mOperandTwo = operandTwo;
        mExpectedResult = expectedResult;
    }

    @Before
    public void setUp() {
        mCalculator = new Calculator();
    }

    @Test
    public void testAddTwoNumbers() {
        double resultAdd = mCalculator.addition(mOperandOne, mOperandTwo);
        assertThat(resultAdd, is(equalTo(mExpectedResult)));
    }

}
