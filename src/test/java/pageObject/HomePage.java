package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private By byLocal(String local) {
        return By.cssSelector("option[value=\"" + local + "\"]");
    }

    @FindBy(css = "input[value]")
    private WebElement btnFindFlights;

    // Seleção da Origem e do Destino
    public void selecionarOrigemDestino(String origem, String destino) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(byLocal(origem))).click();
        wait.until(ExpectedConditions.elementToBeClickable(byLocal(destino))).click();
    }

    // Clicar no botão Find
    public void clicarBtnFindFlights() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(btnFindFlights)).click();
    }

    public void acessarHomePage() {
        driver.get("https://blazedemo.com/");
    }
}
