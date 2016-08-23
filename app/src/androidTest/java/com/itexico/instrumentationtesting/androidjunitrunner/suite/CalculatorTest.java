package com.itexico.instrumentationtesting.androidjunitrunner.suite;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import com.itexico.instrumentationtesting.androidjunitrunner.Calculator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by iTexico Developer on 8/18/2016.
 */

@RunWith(AndroidJUnit4.class)
@SmallTest
public class CalculatorTest {
    private Calculator mCalculator;

    @Before
    public void setUp() {
        mCalculator = new Calculator();
    }

    @Test
    public void addTwoNumbers() {
        double resultAdd = mCalculator.addition(10d, 10d);
        assertThat(resultAdd, is(equalTo(20d)));
    }

    @Test
    public void subtractTwoNumbers() {
        double resultSub = mCalculator.subtraction(100d, 10d);
        assertThat(resultSub, is(equalTo(90d)));
    }

    @Test
    public void subtractWorksWithNegativeResult() {
        double resultSub = mCalculator.subtraction(10d, 100d);
        assertThat(resultSub, is(equalTo(-90d)));
    }

    @Test
    public void devideTwoNumbers() {
        double resultDiv = mCalculator.division(20d, 2d);
        assertThat(resultDiv, is(equalTo(10d)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void devideDivideByZeroThrows() {
        mCalculator.division(32d, 0d);
    }

    @Test
    public void multiplyTwoNumbers() {
        double resultMul = mCalculator.multiply(11d, 2d);
        assertThat(resultMul, is(equalTo(22d)));
    }

}
