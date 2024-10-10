//package steps;
//
//import io.appium.java_client.TouchAction;
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.touch.offset.PointOption;
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import io.cucumber.java.pt.Dado;
//import io.cucumber.java.pt.Entao;
//import io.cucumber.java.pt.Quando;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.DesiredCapabilities;
//
//
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.time.Duration;
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.testng.Assert.assertEquals;
//
//public class SelecionarProduto {
//
//    private AndroidDriver driver;
//
//    @Before
//    public void setUp() throws MalformedURLException {
//
//        Map<String, Object> sauceOptions = new HashMap<>();
//        sauceOptions.put("name", "Selecionar Produto");
//
//        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//        desiredCapabilities.setCapability("platformName", "Android");
//        desiredCapabilities.setCapability("appium:platformVersion", "9.0");
//        desiredCapabilities.setCapability("appium:deviceName", "Samsung Galaxy S9 FHD GoogleAPI Emulator");
//        desiredCapabilities.setCapability("appium:deviceOrientation", "portrait");
//        desiredCapabilities.setCapability("appium:app", "storage:filename=mda-2.0.0-21.apk");
//        desiredCapabilities.setCapability("appium:appPackage", "com.saucelabs.mydemoapp.android");
//        desiredCapabilities.setCapability("appium:appActivity", "com.saucelabs.mydemoapp.android.view.activities.SplashActivity");
//        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
//        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
//        desiredCapabilities.setCapability("sauce:options", sauceOptions );
//        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
//        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);
//
//        URL remoteUrl = new URL("https://oauth-junio.sttr-da57c:42799393-2052-46fb-bbd5-f16def590ca0@ondemand.us-west-1.saucelabs.com:443/wd/hub");
//
//        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
//        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
//    }
//
//    @After
//    public void tearDown() {
//        driver.quit();
//    }
//
//    @Given("que preciso comprar um presente")
//    public void que_preciso_comprar_um_presente() {
//        // O app da loja é aberto no BeforeClass
//    }
//    @When("clico no {string}")
//    public void clico_no(String nomeProduto) {
//        // Tela Principal
//        WebElement imgProduto = driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"" + nomeProduto + "\"]"));
//        imgProduto.click();
//    }
//    @Then("exibe a pagina com o nome do {string} e {string}")
//    public void exibe_a_pagina_com_o_nome_do_e(String nomeProduto, String precoProduto) {
//        // Tela do Produto
//
//        WebElement lblNomeProduto = driver.findElement(By.id("com.saucelabs.mydemoapp.android:id/productTV"));
//        assertEquals(lblNomeProduto.getText(),nomeProduto);
//
//        WebElement lblPrecoProduto = driver.findElement(By.id("com.saucelabs.mydemoapp.android:id/priceTV"));
//        assertEquals(lblPrecoProduto.getText(), precoProduto);
//    }
//
//}