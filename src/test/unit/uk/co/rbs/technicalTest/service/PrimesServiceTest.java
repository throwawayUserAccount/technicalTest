package uk.co.rbs.technicalTest.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class PrimesServiceTest {

    @Autowired
    PrimesService testObject;

    @Test
    public void shouldReturnEmptyCollectionForValueOfZero() {
        Collection result = testObject.getPrimes(0);

        assertEquals(Collections.emptyList(),result);
    }

    @Test
    public void shouldReturnEmptyCollectionForValueOfOne() {
        Collection result = testObject.getPrimes(1);

        assertEquals(Collections.emptyList(),result);
    }

    @Test
    public void shouldReturnCollectionIncludingNumberForValueOfTwo() {
        Collection expectedResult = new ArrayList();
        expectedResult.add(2);

        Collection result = testObject.getPrimes(2);

        assertEquals(expectedResult, result);
    }

    @Test
    public void shouldReturnCollectionWithMultipleCorrectValuesForPositiveIntegerValues() {
        Collection expectedResult = new ArrayList();
        Arrays.stream(new int[]{2, 3, 5, 7, 11}).forEach(v -> expectedResult.add(v));

        Collection result = testObject.getPrimes(12);

        assertEquals(expectedResult, result);
    }
}
