package apiTest;

import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasLength;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TesteBooking {
    // Atributos
    String ct = "application/json";
    String uri = "https://restful-booker.herokuapp.com/";
    private static String token = "";
    // Funções de apoio
    public String lerArquivoJson(String arquivoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(arquivoJson)));
    }

    //Testes

    @Test
    @Order(1)
    public void testeCreateToken() throws IOException {
        String jsonBody = lerArquivoJson("src/test/resources/json/bookingAuth.json");

        Response resp = (Response) given()
                .log().all()
                .contentType(ct)
                .body(jsonBody)
        .when()
                .post(uri + "auth")
        .then()
                .log().all()
                .statusCode(200)
                .body("token", hasLength(15))
        .extract();

        token = resp.jsonPath().getString(("token"));
        System.out.println("Token: " + token);
    }
}
