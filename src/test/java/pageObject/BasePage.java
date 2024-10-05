package pageObject;

import org.openqa.selenium.WebDriver;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
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
}
