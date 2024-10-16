// nome do Pacote
package apiTest;

//Bibliotecas

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
//import org.junit.jupiter.api.FixMethodOrder;
//import org.junit.jupiter.api.MethodSorters;

//Classe
public class TestePet {

    //Atributos
    String ct = "application/json";
    String uriPet = "https://petstore.swagger.io/v2/pet/";

    //Funções e Metódos
    //Funções de Apoio
    public static String lerArquivoJson(String arquivoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(arquivoJson)));
    }

    //Funções de Teste
    @Test
    // Post
    public void testarIncluirPet() throws IOException {
    // Carregar os dados do nosso Json
        String jsonBody = lerArquivoJson("src/test/resources/json/pet1.json");

    // Realizar o Teste
        given()                                                    // Dado que
                .contentType(ct)                                   // o Tipo de Conteúdo
                .log().all()                                       // Mostre tudo
                .body(jsonBody)                                    // Corpo da requisição
        .when()                                                    // Quando
                .post("https://petstore.swagger.io/v2/pet/")    // Endpoint // Onde
        .then()                                                    // Então
                .log().all()                                       // mostre tudo na volta
                .statusCode(200)                                //  Comunicação ida e volta - OK
                .body("id", is(987654321))                    // tag code é 200
                .body("category.id", is(12))              // tag type é 137743327751
                .body("name", is("Dachshund"))                    // Message é o userId
                .body("status", is("available"))                    // Message é o userId
        ;
    }

    @Test
    //Get
    public void testarConsultarPet(){

        String petID = "987654321";

        //Resultados Esperados

        given()
                .contentType(ct)                                               // o Tipo de Conteúdo
                .log().all()                                                   // Mostre tud
        .when()                                                                // Quando
                .get(uriPet + petID)                                   // Endpoint // Onde
        .then()                                                                // Então
                .log().all()                                       // mostre tudo na volta
                .statusCode(200)                                //  Comunicação ida e volta - OK
                .body("id", is(987654321))                    // tag code é 200
                .body("category.id", is(12))              // tag type é 137743327751
                .body("name", is("Dachshund"))                    // Message é o userId
                .body("status", is("available"))                    // Message é o userId
        ;
    }

    @Test
    //Put
    public void testarAtualizarPet() throws IOException {

        String jsonBody = lerArquivoJson("src/test/resources/json/pet2.json");

        given()
                .contentType(ct)               // Tipo de Conteúdo
                .body(jsonBody)                                  // Corpo da requisição
                .log().all()                                     // Mostre tudo na requisição
        .when()
                .put(uriPet)                      // Endpoint para atualizar o usuário
        .then()
                .log().all()                                     // Mostre tudo na resposta
                .statusCode(200)                               // Verifique se o status é 200 OK
                .body("id", is(987654321))                    // tag code é 200
                .body("category.id", is(12))              // tag type é 137743327751
                .body("name", is("Dachshund"))                    // Message é o userId
                .body("status", is("available"))                    // Message é o userId
        ;
    }

    @Test
    //Delete
    public void testarExcluirPet() throws IOException {

        String petID = "987654321";

        given()
                .contentType(ct)               // Tipo de Conteúdo
                .log().all()                                     // Mostre tudo na requisição
        .when()
                .delete(uriPet + petID)                      // Endpoint para atualizar o usuário
        .then()
                .log().all()                                     // Mostre tudo na resposta
                .statusCode(200)                               // Verifique se o status é 200 OK
                .body("code", is(200))                    // tag code é 200
                .body("type", is("unknown"))              // tag type é 137743327751
                .body("message", is(petID))                    // Message é o petId
        ;
    }


}

