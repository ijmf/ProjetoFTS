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
import static org.hamcrest.Matchers.is;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TesteBooking {
    // Atributos
    String ct = "application/json";
    String uri = "https://restful-booker.herokuapp.com/";
    private static String token = "";
    private static int bookingId = 298;

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
    @Test
    @Order(2)
    public void testeBookingIds() throws IOException {

        given()
                .log().all()
                .contentType(ct)
        .when()
                .get(uri + "booking")
        .then()
                .log().all()
                .statusCode(200)
                ;
        }

    @Test
    @Order(3)
    public void testeGetBooking() throws IOException {
        testeCreateToken();
        given()
                .log().all()
                .contentType(ct)
        .when()
                .get(uri + "booking/" + bookingId)
        .then()
                .log().all()
                .statusCode(200)
                .body("totalprice", is(112))
                //.body("bookingdates.checkin", is("2018-01-01"))
        ;
    }

    @Test
    @Order(5)
    public void testeUpdateBooking() throws IOException {
        testeCreateToken();

        String jsonBody = lerArquivoJson("src/test/resources/json/updateBooking.json");

        given()
                .log().all()
                .contentType(ct)
                .header("Cookie", "token=" + token)
                .body(jsonBody)
        .when()
                .put(uri + "booking/" + bookingId)
        .then()
                .log().all()
                .statusCode(200)
                .body("firstname", is("John"))
                .body("bookingdates.checkin", is("2024-09-30"))
                .body("bookingdates.checkout", is("2024-10-30"))
                .extract();



    }
}
