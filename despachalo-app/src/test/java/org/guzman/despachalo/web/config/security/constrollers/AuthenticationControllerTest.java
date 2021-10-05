package org.guzman.despachalo.web.config.security.constrollers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;

import java.util.Map;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthenticationControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Sql("classpath:sql/security/insert-admin-user.sql")
    void whenTryToLoginWithCredentials_andUserWithThoseCredentialsExists_shouldLoginAndReturnJWT() {
        var email = "admin@despachalo.pe";
        var password = "admin";

        var res = whenAuthenticateWithCredentials(email, password);

        then(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(res.getBody()).contains("jwt");
    }

    @Test
    void whenTryToLoginWithCredentials_andUserNoExists_shouldReturnBadRequest() {
        var email = "admin@despachalo.pe";
        var password = "admin";

        var res = whenAuthenticateWithCredentials(email, password);

        then(res.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<String> whenAuthenticateWithCredentials(String email, String rawPassword) {
        var body = Map.of("email", email, "password", rawPassword);
        var request = new HttpEntity<>(body, headers());

        return restTemplate.exchange("/auth/login",
                HttpMethod.POST,
                request,
                String.class);
    }

    private HttpHeaders headers() {
        var headers = new HttpHeaders();
        headers.add("Content-type", "application/json");
        return headers;
    }
}
