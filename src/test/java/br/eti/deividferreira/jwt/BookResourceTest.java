package br.eti.deividferreira.jwt;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.eti.deividferreira.jwt.utils.TokenUtils;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class BookResourceTest {

    private String token;

    @BeforeEach
    public void gerarToken() throws Exception {
        token = TokenUtils.generateTokenString("username", new HashSet<>(Arrays.asList("Users", "Test")));
    }

    @Test
    public void testGetBooksEndpoint() {
        given()
            .auth().oauth2(this.token)
            .when().get("/books")
            .then()
            .statusCode(200)
                .body(is("Sitio do Pica-Pau Amarelo"));
    }
}