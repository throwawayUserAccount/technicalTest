package uk.co.rbs.technicalTest.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uk.co.rbs.technicalTest.service.PrimesService;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
public class PrimesController {

    private static final int TEN_MILLION = 10000000; // response gets too slow beyond 10m.
    public static final String INVALID_INPUT = "{\"error\":\"Invalid Input\"," +
            "\"message\":\"Try again with a positive integer.\"}";

    @GetMapping("/primes/{upto:.+}")
    public ResponseEntity<String> getPrimes(@PathVariable("upto") String upto) {
        Integer input = -1;
        try {
            if (!isInputAnIntegerValue(upto)){
                return badRequest(INVALID_INPUT);
            }

            input = Integer.parseInt(upto);

            if (input < 0) {
                return badRequest(INVALID_INPUT);
            }

            if (isTooLarge(input)) {
                return badRequest("{\"error\":\"Invalid Input\"," +
                        "\"message\":\"Number too large. Try another integer less than 10,000,000\"}");
            }
        } catch (NumberFormatException ex) {

            return badRequest(INVALID_INPUT);
        }

        return PrimesService.primesUpto(input);
    }

    private boolean isInputAnIntegerValue(String upto) {
        return (upto!=null && !upto.contains("."));
    }

    private ResponseEntity<String> badRequest(String message) {
        return new ResponseEntity<>(message, BAD_REQUEST);
    }

    private boolean isTooLarge(Integer upto) {
        return (upto >= TEN_MILLION);
    }
}
