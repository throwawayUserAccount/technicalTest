package uk.co.rbs.technicalTest;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import uk.co.rbs.technicalTest.controllers.PrimesController;

@RunWith(SpringRunner.class)
@WebMvcTest(PrimesController.class)
public class PrimesResourceTest {

    @Autowired
    private MockMvc mockMVC;

    @Test
    public void shouldGetGreeting() throws Exception {
        mockMVC.perform(MockMvcRequestBuilders.get("/primes/12")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.equalTo("{ \"upto\" : 12, \"primes\" : [ 2, 3, 5, 7, 11 ]}")));

    }
}
