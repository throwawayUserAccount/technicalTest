package uk.co.rbs.technicalTest.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrimesResource {

    @GetMapping("/primes")
    public void getPrimes() {

    }


}
