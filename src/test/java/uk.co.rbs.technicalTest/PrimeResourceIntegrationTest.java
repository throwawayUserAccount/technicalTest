package uk.co.rbs.technicalTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrimeResourceIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldGetGreeting() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("/primes/12", String.class);
        assertThat(response.getBody(), equalTo("{ \"upto\" : 12, \"primes\" : [ 2, 3, 5, 7, 11 ]}"));
    }
}
