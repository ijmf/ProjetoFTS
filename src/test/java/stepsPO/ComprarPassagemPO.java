package stepsPO;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import pageObject.FlightsPage;
import pageObject.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComprarPassagemPO {

    private WebDriver driver;
    private HomePage homePage;
    private FlightsPage flightsPage;

    @Before // Inicializa o WebDriver antes de cada cenário
    public void setup() {
        WebDriverManager.edgedriver().setup(); // Configure o WebDriver
        driver = new EdgeDriver(); // Inicialize o EdgeDriver
        driver.manage().window().maximize(); // Maximize a janela
        homePage = new HomePage(driver); // Inicialize homePage aqui
    }

    @Given("que acesso a pagina inicial PO")
    public void que_acesso_a_pagina_inicial_po() {
        homePage.acessarHomePage();
        assertEquals("BlazeDemo", homePage.getTitle());
    }

    @When("seleciono origem {string} e destino {string} PO")
    public void seleciono_origem_e_destino_po(String origem, String destino) {
        homePage.selecionarOrigemDestino(origem, destino);
    }

    @When("clico no botao Find Flights PO")
    public void clico_no_botao_find_flights_po() {
        homePage.clicarBtnFindFlights();
        flightsPage = new FlightsPage(driver);
    }

    @Then("exibe pagina de voos entre {string} e {string} disponiveis PO")
    public void exibe_pagina_de_voos_entre_e_disponiveis_po(String origem, String destino) {
        assertEquals("BlazeDemo - reserve", flightsPage.getTitle());
        assertEquals(String.format("Flights from %s to %s:", origem, destino), flightsPage.getFlightsHeader());
    }

    @After // Encerra o WebDriver após cada cenário
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
