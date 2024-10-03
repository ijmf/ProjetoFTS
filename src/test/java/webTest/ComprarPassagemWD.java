package webTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComprarPassagemWD {
    private WebDriver driver; // Variável de instância
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @BeforeEach
    public void setUp() {
//        System.setProperty("webdriver.gecko.driver", "drivers/firefox/geckodriver.exe"); // Altere para o caminho correto
//        FirefoxOptions options = new FirefoxOptions();
//        //options.setCapability("marionette", true); // Habilita o marionette
//        driver = new FirefoxDriver(options);
//        driver.manage().window().maximize();

        // Defina o caminho do Edge WebDriver
        System.setProperty("webdriver.edge.driver", "drivers/edge/msedgedriver.exe"); // Aponta onde está o Edge

        // Inicia o EdgeDriver
        driver = new EdgeDriver(); // Inicia

        // Inicializa o JavascriptExecutor
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();

    }

    @AfterEach
    public void tearDown() {
        driver.quit(); // Fecha o navegador
    }

    @Test
    public void comprarPassagemWD() {
        driver.get("https://www.blazedemo.com");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Espera de 10 segundos

        wait.until(ExpectedConditions.elementToBeClickable(By.name("fromPort"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[. = 'São Paolo']"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.name("toPort"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[. = 'Berlin']"))).click();

        // Apertar o botão Find Flights
        driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

        // Validar a frase que indica que o vôo é de São Paolo para Berlin
        assertEquals("Flights from São Paolo to Berlin:", driver.findElement(By.cssSelector("h3")).getText());
    }
}
