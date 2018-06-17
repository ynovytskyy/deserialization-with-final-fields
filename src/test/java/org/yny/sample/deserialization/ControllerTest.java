package org.yny.sample.deserialization;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnDefaultMessage() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>("{\"str\": \"test\", \"i\": \"3\"}", headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/echo", HttpMethod.POST,
                request, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(response.getBody(), containsString("test-echo"));
        assertThat(response.getBody(), containsString("13"));
    }
}