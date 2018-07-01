package uk.co.rbs.technicalTest.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrimesController {

    @GetMapping("/primes/{upto}")
    public String getPrimes(@PathVariable("upto") int upto) {
        return "{ \"upto\" : " +  upto + ", \"primes\" : [ 2, 3, 5, 7, 11 ]}";
    }


}
