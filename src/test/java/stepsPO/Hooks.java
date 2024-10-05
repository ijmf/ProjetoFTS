package stepsPO;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.concurrent.TimeUnit;

public class Hooks {

    private static WebDriver driver; // Torna o driver estático para uso em toda a classe

    // Construtor público sem argumentos
    public Hooks() {
        // Este construtor pode ser deixado vazio ou pode ser usado para inicializações gerais
    }

    @Before
    public void setUp() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}