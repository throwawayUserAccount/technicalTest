package uk.co.rbs.technicalTest.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uk.co.rbs.technicalTest.service.PrimesService;

import javax.servlet.http.HttpServletRequest;
import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class PrimesController {

    private static final int TEN_MILLION = 10000000; // response gets too slow beyond 10m.
    public static final String INVALID_INPUT = "{\"error\":\"Invalid Input\"," +
            "\"message\":\"Try again with a positive integer.\"}";

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/primes/{upto:.+}")
    public ResponseEntity<String> getPrimes(@PathVariable("upto") String upto) {

        if (!isInputAnIntegerValue(upto)){
            return badRequest(INVALID_INPUT);
        }

        Integer input = -1;
        try {
            input = Integer.parseInt(upto);
        } catch (NumberFormatException ex) {
            return badRequest(INVALID_INPUT);
        }

        if (input < 0) {
            return badRequest(INVALID_INPUT);
        }

        if (isTooLarge(input)) {
            return badRequest("{\"error\":\"Invalid Input\"," +
                    "\"message\":\"Number too large. Try another integer less than 10,000,000\"}");
        }

        Map<String, String> result = PrimesService.primesUpto(input);

        if (request.getHeaders("Accept").nextElement().equals("application/xml")){
            return new ResponseEntity<>(getMapAsXmlString(result), OK);
        }

        return new ResponseEntity<>(new JSONObject(result).toString(), OK);
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

    public String getMapAsXmlString(Map<String, String> hashMap){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XMLEncoder xmlEncoder = new XMLEncoder(bos);
        xmlEncoder.writeObject(hashMap);
        xmlEncoder.close();
        return bos.toString();
    }
}
