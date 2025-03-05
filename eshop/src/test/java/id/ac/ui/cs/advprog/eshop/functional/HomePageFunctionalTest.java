package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setUpTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void userCanCreateProduct(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/products/create");

        WebElement nameInput = driver.findElement(By.id("productName"));
        WebElement quantityInput = driver.findElement(By.id("productQuantity"));
        WebElement submitButton = driver.findElement(By.id("submitProduct"));

        nameInput.sendKeys("Smartphone");
        quantityInput.sendKeys("20");
        submitButton.click();

        Thread.sleep(2000); // Menunggu halaman reload

        WebElement productList = driver.findElement(By.id("productList"));
        assertTrue(productList.getText().contains("Smartphone"));
    }
}
