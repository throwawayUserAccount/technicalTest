package uk.co.rbs.technicalTest.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PrimesService {

    private static final boolean NOT_PRIME = false;
    private static final boolean IS_PRIME = true;

    public static Map<String, String> primesUpto(Integer upto) {
        Map<String, String> primesMap = new HashMap<>();
        primesMap.put("Initial", upto.toString());
        primesMap.put("Primes",  getPrimes(upto).toString());

        return primesMap;
    }

    public static Collection getPrimes(Integer upto) {
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= upto; i++) {
            if (isPrime(i))
                primes.add(i);
        }
        return primes;
    }

    public static boolean isPrime(int num) {
        if (num > 2 && num % 2 == 0) {
            return NOT_PRIME;
        }
        int top = (int) Math.sqrt(num) + 1;
        for (int i = 3; i < top; i += 2) {
            if (num % i == 0) {
                return NOT_PRIME;
            }
        }
        return IS_PRIME;
    }
}
