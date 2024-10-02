// nome do Pacote
package apiTest;

//Bibliotecas
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
//import org.junit.jupiter.api.FixMethodOrder;
//import org.junit.jupiter.api.MethodSorters;

//Classe
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
    @Order(1)
    // Post
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
    }

    @Test
    @Order(2)
    //Get
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
                .log().all()                                                  // mostre tudo na volta
                .statusCode(200)                                          //  tag deve ser 200
                .body("id", is(userID))                                  // id é o userID
                .body("email", is(email))                                // tag type é email
                .body("password", is(password))                         // Message é o password
                .body("phone", is(phone))                               // Message é o phone
        ;
    }

    @Test
    @Order(3)
    //Put
    public void testarAtualizarUser() throws IOException {

        String jsonBody = lerArquivoJson("src/test/resources/json/user2.json");
        String userID = "137743327751";
        String username = "IvanFerreira";

        given()
                .contentType(ct)               // Tipo de Conteúdo
                .body(jsonBody)                                  // Corpo da requisição
                .log().all()                                     // Mostre tudo na requisição
        .when()
                .put(uriUser + username)                      // Endpoint para atualizar o usuário
        .then()
                .log().all()                                     // Mostre tudo na resposta
                .statusCode(200)                               // Verifique se o status é 200 OK
                .body("code", is(200))                    // tag code é 200
                .body("type", is("unknown"))              // tag type é 137743327751
                .body("message", is(userID))                    // Message é o userId
        ;
    }

    @Test
    @Order(4)
    //Delete
    public void testarExcluirUser() throws IOException {

        String username = "IvanFerreira";

        given()
                .contentType(ct)               // Tipo de Conteúdo
                .log().all()                                     // Mostre tudo na requisição
        .when()
                .delete(uriUser + username)                      // Endpoint para atualizar o usuário
        .then()
                .log().all()                                     // Mostre tudo na resposta
                .statusCode(200)                               // Verifique se o status é 200 OK
                .body("code", is(200))                    // tag code é 200
                .body("type", is("unknown"))              // tag type é 137743327751
                .body("message", is(username))                    // Message é o userId
        ;
    }

    @Test
    @Order(5)
    //GET - LOGIN
    public void testarLogin(){

        String username = "IvanFerreira";
        String password = "123";

        Response response = (Response) given()
                .contentType(ct)
                .log().all()
        .when()
                .get(uriUser + "login?username="+ username +"&password=" + password)
        .then()
                .statusCode(200)
                .log().all()
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", containsString("logged in user session:"))
                .body("message", hasLength(36))
        .extract();
                //Extração do token da resposta
                String token = response.jsonPath().getString("message").substring(23);
                System.out.println("Conteúdo do Token: "+ token);
    }

    @ParameterizedTest
    @Order(6)
    @CsvFileSource(resources = "/csv/massaUser.csv", numLinesToSkip = 1, delimiter = ',')
    public void testarIncluirUserCSV(
            String id,
            String username,
            String firstName,
            String lastName,
            String email,
            String password,
            String phone,
            String userStatus)
    {
        User user= new User();

        user.id = id;
        user.username = username;
        user.firstName = firstName;
        user.lastName = lastName;
        user.email = email;
        user.password = password;
        user.phone = phone;
        user.userStatus = userStatus;

        Gson gson = new Gson(); //Instancia a classe Gson.
        String jsonBody = gson.toJson(user);

        // Incluir dados no CSV
        /*
        StringBuilder jsonBody = new StringBuilder("{");
        jsonBody.append("\"id\": " + id + ",");
        jsonBody.append("\"username\": \"" + username + "\",");
        jsonBody.append("\"firstName\": \"" + firstName + "\",");
        jsonBody.append("\"lastName\": \"" + lastName + "\",");
        jsonBody.append("\"email\": \"" + email + "\",");
        jsonBody.append("\"password\": \"" + password + "\",");
        jsonBody.append("\"phone\": \"" + phone + "\",");
        jsonBody.append("\"userStatus\": " + userStatus);
        jsonBody.append("}");
        */
        // Realizar o Teste
        given()                                                      // Dado que
                .contentType("application/json")                  // o Tipo de Conteúdo
                .log().all()                                        // Mostre tudo
                .body(jsonBody.toString())                          // Corpo da requisição
        .when()                                                     // Quando
                .post("https://petstore.swagger.io/v2/user")     // Endpoint // Onde
        .then()                                                     // Então
                .log().all()                                        // mostre tudo na volta
                .statusCode(200)                                 // Comunicação ida e volta - OK
                .body("code", is(200))                     // tag code é 200
                .body("type", is("unknown"))               // tag type é "unknown"
                .body("message", is(id));                         // Message é o Id
    }

}

