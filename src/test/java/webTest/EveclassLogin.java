package webTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EveclassLogin {
    private WebDriver driver; // Instância do WebDriver para controlar o navegador
    private WebDriverWait wait; // Objeto para gerenciar esperas explícitas
    private Map<String, Object> vars; // Mapa para armazenar variáveis que podem ser usadas no teste
    private JavascriptExecutor js; // Para executar scripts JavaScript no navegador

    @BeforeEach
    public void setUp() {
        // Configurações do Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*"); // Permite conexões remotas (importante para algumas configurações de teste)

        // Define o caminho do ChromeDriver
        System.setProperty("webdriver.chrome.driver", "drivers/chrome/chromedriver.exe");
        driver = new ChromeDriver(options); // Inicializa o ChromeDriver com as opções configuradas
        js = (JavascriptExecutor) driver; // Inicializa o JavascriptExecutor
        vars = new HashMap<>(); // Inicializa o mapa de variáveis

        // Inicializa o objeto de espera com um tempo máximo de 3 segundos
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.manage().window().maximize(); // Maximiza a janela do navegador para melhor visibilidade
    }

    @AfterEach
    public void tearDown() {
        driver.quit(); // Fecha o navegador após a execução do teste
    }

    @Test
    public void testeLogin() {
        // Acessa a URL do site
        driver.get("https://testando.eveclass.com/pt");

        // Clica no botão de suporte
        driver.findElement(By.id("support-action")).click();

        // Atualiza a página para garantir que todos os elementos estejam carregados
        driver.navigate().refresh();

        // Espera até que o cabeçalho "Entrar" esteja visível
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".auth-header h1")));

        // Preenche o campo de email
        driver.findElement(By.cssSelector("input[type=\"email\"]")).sendKeys("correia@iterasys.com.br");

        // Preenche o campo de senha
        driver.findElement(By.cssSelector("input[type=\"password\"]")).sendKeys("Z0br3M@drugar");

        // Clica no botão "Entrar"
        driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();

        // Espera de forma sincrona por 5 segundos após o login
        synchronized (driver) {
            try {
                driver.wait(5000); // Aguarda 5 segundos para processar a resposta do servidor
            } catch (Exception e) {
                e.printStackTrace(); // Imprime qualquer exceção que ocorra
            }
        }
    }
}
