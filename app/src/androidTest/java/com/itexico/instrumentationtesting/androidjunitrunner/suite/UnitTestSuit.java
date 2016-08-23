package com.itexico.instrumentationtesting.androidjunitrunner.suite;

import com.itexico.instrumentationtesting.androidjunitrunner.OperandsParcelableUnitTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by iTexico Developer on 8/18/2016.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({OperandsParcelableUnitTest.class, CalculatorTest.class,CalculatorParamerizedTest.class})
public class UnitTestSuit {
}
