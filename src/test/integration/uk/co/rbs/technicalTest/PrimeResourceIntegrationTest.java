package uk.co.rbs.technicalTest;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrimeResourceIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldGetPrimesUptoAValidNumber() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("/primes/12", String.class);
        Assert.assertThat(response.getBody(), Matchers.equalTo("{ \"upto\" : 12, \"primes\" : [2, 3, 5, 7, 11]}"));
    }
}
