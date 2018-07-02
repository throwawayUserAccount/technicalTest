package uk.co.rbs.technicalTest;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrimeResourceIntegrationTest {

    public static final String ERROR_INVALID_INPUT = "\"error\": \"Invalid Input\"";
    public static final String MESSAGE_NUMBER_TOO_LARGE = "\"message\": \"Number too large. Try another integer less than 10,000,000\"";
    public static final String MESSAGE_TRY_AGAIN = "\"message\": \"Try again with a positive integer.\"";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnEmptyCollectionForZero() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("/primes/0", String.class);
        assertThat(response.getBody(), Matchers.equalTo("{ \"Initial\" : 0, \"Primes\" : []}"));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void shouldReturnEmptyCollectionForOne() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("/primes/1", String.class);
        assertThat(response.getBody(), Matchers.equalTo("{ \"Initial\" : 1, \"Primes\" : []}"));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void shouldReturnCollectionOfPrimesUptoAndIncludingValueForPositiveIntegersGreaterThanOne() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("/primes/2", String.class);
        assertThat(response.getBody(), Matchers.equalTo("{ \"Initial\" : 2, \"Primes\" : [2]}"));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void shouldGetPrimesUptoAValidNumber() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("/primes/12", String.class);
        assertThat(response.getBody(), Matchers.equalTo("{ \"Initial\" : 12, \"Primes\" : [2, 3, 5, 7, 11]}"));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void shouldGetPrimesUptoSomeLargeNumber() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("/primes/9999999", String.class);
        assertThat(response.getBody(), Matchers.containsString("{ \"Initial\" : 9999999, \"Primes\" : [2, 3, 5, 7, 11, 13"));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void shouldNotGetPrimesGreaterThanOrEqualToTenMillion() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("/primes/10000000", String.class);
        assertThat(response.getBody(), Matchers.equalTo("{ " + ERROR_INVALID_INPUT + ", " +
                MESSAGE_NUMBER_TOO_LARGE + " }"));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void shouldGetPrimesForANumberThatIsADecimalInteger() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("/primes/5.0", String.class);
        assertThat(response.getBody(), Matchers.equalTo("{ " + ERROR_INVALID_INPUT + ", " + MESSAGE_TRY_AGAIN + " }"));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void shouldNotGetPrimesForANumberThatIsNotAnInteger() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("/primes/5.1", String.class);
        assertThat(response.getBody(), Matchers.equalTo("{ " + ERROR_INVALID_INPUT + ", " +
                MESSAGE_TRY_AGAIN + " }"));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void shouldNotGetPrimesForANumberThatIsAString() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("/primes/abc", String.class);
        assertThat(response.getBody(), Matchers.equalTo("{ " + ERROR_INVALID_INPUT + ", " +
                MESSAGE_TRY_AGAIN + " }"));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void shouldReturnAnErrorForNegativeInputValues() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("/primes/-1", String.class);
        assertThat(response.getBody(), Matchers.equalTo("{ " + ERROR_INVALID_INPUT + ", " +
                MESSAGE_TRY_AGAIN + " }"));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
