package steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Teste {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.edge.driver", "drivers/edge/msedgedriver.exe"); // Atualize o caminho
        driver = new EdgeDriver();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void que_acesso_a_pagina_inicial() {
        if (driver == null) {
            throw new IllegalStateException("O WebDriver não foi inicializado!");
        }

        driver.get("https://blazedemo.com/"); // Coloque a URL da página inicial aqui
    }
}
