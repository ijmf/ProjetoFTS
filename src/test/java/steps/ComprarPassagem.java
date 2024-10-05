package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.text.MessageFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComprarPassagem {

    private WebDriver driver;

    @Before // Inicializa o WebDriver antes de cada cenário
    public void setUp() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        System.out.println("Driver initialized successfully.");
    }

    @Given("que acesso a pagina inicial")
    public void que_acesso_a_pagina_inicial() {
        driver.get("https://blazedemo.com/");
        assertEquals("BlazeDemo", driver.getTitle());
    }

    @When("seleciono origem {string} e destino {string}")
    public void seleciono_origem_e_destino(String origem, String destino) {
        final String byOrigem = "option[value=\"" + origem + "\"]";
        final String byDestino = "option[value=\"" + destino + "\"]";

        driver.findElement(By.cssSelector(byOrigem)).click();
        driver.findElement(By.cssSelector(byDestino)).click();
    }

    @When("clico no botao Find Flights")
    public void clico_no_botao_find_flights() {
        driver.findElement(By.cssSelector("input[value='Find Flights']")).click();
    }

    @Then("exibe pagina de voos entre {string} e {string} disponiveis")
    public void exibe_pagina_de_voos_entre_e_disponiveis(String origem, String destino) {
        assertEquals("BlazeDemo - reserve", driver.getTitle());
        String headerText = driver.findElement(By.cssSelector("div.container h3")).getText();
        assertEquals(MessageFormat.format("Flights from {0} to {1}:", origem, destino), headerText);
    }

    @After // Encerra o WebDriver após cada cenário
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Driver closed successfully.");
        }
    }
}
