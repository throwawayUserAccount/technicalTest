package uk.co.rbs.technicalTest.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class PrimesServiceTest {

    @Autowired
    PrimesService testObject;

    @Test
    public void shouldReturnEmptySetForValueOfOne() {
        Set result = testObject.getPrimes(1);

        assertEquals(Collections.emptySet(),result);
    }

    @Test
    public void shouldReturnSetIncludingNumberForValueOfTwo() {
        Set expectedResult = new HashSet();
        expectedResult.add(2);

        Set result = testObject.getPrimes(2);

        assertEquals(expectedResult, result);
    }

    @Test
    public void shouldReturnSetWithMultipleCorrectValuesForPositiveIntegerValues() {
        Set expectedResult = new HashSet();
        Arrays.stream(new int[]{2, 3, 5, 7, 11}).forEach(v -> expectedResult.add(v));

        Set result = testObject.getPrimes(12);

        assertEquals(expectedResult, result);
    }
}
