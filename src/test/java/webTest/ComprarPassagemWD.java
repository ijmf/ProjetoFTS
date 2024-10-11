package webTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComprarPassagemWD {

    private WebDriver driver; // Variável de instância
    private WebDriverWait wait; // Objeto de espera
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @BeforeEach
    public void setUp() {
        // Configurações do Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        // Aponta onde está o Chrome Driver
        System.setProperty("webdriver.chrome.driver", "drivers/chrome/chromedriver.exe");
        driver = new ChromeDriver(options);
        js = (JavascriptExecutor) driver;
        vars = new HashMap<>();

        // Inicializa o objeto de espera
        wait = new WebDriverWait(driver, Duration.ofSeconds(3)); // Reduzido para 5 segundos
        driver.manage().window().maximize(); // Maximiza a janela do navegador
    }

    @AfterEach
    public void tearDown() {
        driver.quit(); // Fecha o navegador
    }

    @Test
    public void comprarPassagemWD() {
        driver.get("https://www.blazedemo.com");

        // Seleciona a cidade de origem e destino
        wait.until(ExpectedConditions.elementToBeClickable(By.name("fromPort"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[. = 'São Paolo']"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.name("toPort"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[. = 'Berlin']"))).click();

        // Clica no botão "Find Flights"
        driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

        // Valida a frase que indica que o voo é de São Paulo para Berlin
        assertEquals("Flights from São Paolo to Berlin:", driver.findElement(By.cssSelector("h3")).getText());
    }
}
