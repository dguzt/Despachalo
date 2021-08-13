package org.guzman.despachalo.distribution;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetTruckSystemTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Sql("classpath:sql/distribution/get-truck-system-test.sql")
    void getTruckSuccess() {
        var truckId = 1L;

        var res = whenGetTruck(truckId);

        then(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(res.getBody())
                .contains("id")
                .contains("code")
                .contains(String.valueOf(truckId));
    }

    private HttpHeaders headers() {
        var headers = new HttpHeaders();
        headers.add("Content-type", "application/json");
        return headers;
    }

    private ResponseEntity<String> whenGetTruck(Long truckId) {
        var request = new HttpEntity<>(null, headers());
        return restTemplate.exchange("/trucks/{truckId}",
                HttpMethod.GET,
                request,
                String.class,
                truckId);
    }
}
