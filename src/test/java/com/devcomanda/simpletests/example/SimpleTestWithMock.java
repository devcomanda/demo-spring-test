package com.devcomanda.simpletests.example;

import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SimpleTestWithMock {

    @Test
    public void shouldReturnCorrectResultAfterMultiplyAAndB() {
        Summator mockSummator = Mockito.mock(Summator.class);

        when(mockSummator.sum(eq(5), eq(5))).thenReturn(25);

        TestSample testSample = new TestSample(mockSummator);

        int expectedA = 5;
        int expectedB = 5;

        int actualResult = testSample.sum(expectedA, expectedB);
        int expectedResult = 25;

        assertThat(actualResult, equalTo(expectedResult));
        // or
        assertEquals(actualResult, expectedResult);

        // see more this https://github.com/junit-team/junit4/wiki/Matchers-and-assertthat


        verify(mockSummator).sum(eq(5), eq(5));
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowException() {

        Summator mockSummator = Mockito.mock(Summator.class);

        when(mockSummator.sum(eq(5), eq(5))).thenThrow(IllegalArgumentException.class);

        TestSample testSample = new TestSample(mockSummator);

        int expectedA = 5;
        int expectedB = 5;

        testSample.sum(expectedA, expectedB);

        // this line is never called
        verify(mockSummator).sum(eq(150), eq(250));
    }


    public static class TestSample {

        private final Summator summator;

        public TestSample(Summator summator) {
            this.summator = summator;
        }

        public int sum(int a, int b) {
            return this.summator.sum(a, b);
        }
    }

    public interface Summator {
        int sum(int a, int b);
    }
}
