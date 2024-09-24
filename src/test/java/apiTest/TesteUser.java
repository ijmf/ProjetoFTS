// nome do Pacote
package apiTest;

//Bibliotecas
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

//Classe
public class TesteUser {

    //Atributos
    String ct = "application/json";
    String uriUser = "https://petstore.swagger.io/v2/user/";

    //Funções e Metódos
    //Funções de Apoio
    public static String lerArquivoJson(String arquivoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(arquivoJson)));
    }

    //Funções de Teste
    @Test
    public void testarIncluirUser() throws IOException {
    // Carregar os dados do nosso Json
        String jsonBody = lerArquivoJson("src/test/resources/json/user1.json");
        String userID = "137743327751";
    // Realizar o Teste
        given()                                                    // Dado que
                .contentType(ct)                // o Tipo de Conteúdo
                .log().all()                                       // Mostre tudo
                .body(jsonBody)                                    // Corpo da requisição
        .when()                                                    // Quando
                .post("https://petstore.swagger.io/v2/user")    // Endpoint // Onde
        .then()                                                    // Então
                .log().all()                                       // mostre tudo na volta
                .statusCode(200)                                //  Comunicação ida e volta - OK
                .body("code", is(200))                    // tag code é 200
                .body("type", is("unknown"))              // tag type é 137743327751
                .body("message", is(userID))                    // Message é o userId
        ;
    }//Fim do Post

    @Test
    public void testarConsultarUser(){

        String username = "IvanFerreira";

        //Resultados Esperados
        long userID = 137743327751L;                                  //Código do Usuário
        String email = "ivanjuniomf@gmail.com";
        String password = "123";
        String phone = "6155779900";

        given()
                .contentType(ct)                                               // o Tipo de Conteúdo
                .log().all()                                                   // Mostre tud
        .when()                                                                // Quando
                .get(uriUser + username)                                   // Endpoint // Onde
        .then()                                                                // Então
                .log().all()                                       // mostre tudo na volta
                .statusCode(200)                                //  Comunicação ida e volta - OK
                .body("id", is(userID))                    // tag code é 200
                .body("email", is(email))              // tag type é 137743327751
                .body("password", is(password))                    // Message é o userId
                .body("phone", is(phone))                    // Message é o userId
        ;
    }

}

