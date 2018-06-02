package com.devcomanda.simpletests.example;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class SimpleTest {

    @Test
    public void shouldReturnCorrectResultAfterMultiplyAAndB() {

        int expectedA = 5;
        int expectedB = 5;


        int actualResult = new TestSample().multiply(expectedA, expectedB);

        int expectedResult = 25;
        assertThat(actualResult, equalTo(expectedResult));
    }

    public static class TestSample {

        public int multiply(int a, int b) {
            return a * b;
        }
    }
}
