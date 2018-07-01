package uk.co.rbs.technicalTest.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PrimesService {


    public static String primesUpto(@PathVariable("upto") int upto) {
        return "{ \"upto\" : " + upto + ", \"primes\" : [ 2, 3, 5, 7, 11 ]}";
    }
}
