package uk.co.rbs.technicalTest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uk.co.rbs.technicalTest.service.PrimesService;

@RestController
public class PrimesController {

    @GetMapping("/primes/{upto}")
    public String getPrimes(@PathVariable("upto") int upto) {
        return PrimesService.primesUpto(upto);
    }
}
