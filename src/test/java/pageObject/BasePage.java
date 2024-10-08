package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Timeout padrão de 10 segundos
    }

    /**
     * Retorna o título da página atual.
     *
     * @return String contendo o título da página.
     */
    public String getTitle() {
        return driver.getTitle();
    }

    /**
     * Retorna a instância do WebDriver.
     *
     * @return WebDriver
     */
    protected WebDriver getDriver() {
        return driver;
    }

    /**
     * Aguarda a visibilidade de um elemento na página.
     *
     * @param locator O localizador do elemento.
     */
    protected void waitForElementVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
