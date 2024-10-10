package webTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EveclassLogin {
    private WebDriver driver; // Variável de instância
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @BeforeEach
    public void setUp() {

        // Defina o caminho do Edge WebDriver
        System.setProperty("webdriver.edge.driver", "drivers/edge/msedgedriver.exe"); // Aponta onde está o Edge

        // Inicia o EdgeDriver
        driver = new EdgeDriver(); // Inicia
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(90000));
        // Inicializa o JavascriptExecutor
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();

    }

//    @AfterEach
//    public void tearDown() {
//        driver.quit(); // Fecha o navegador
//    }

    @Test
    public void testeLogin() {
        driver.get("https://testando.eveclass.com/");
        driver.findElement(By.id("support-action")).click();

    }
}
