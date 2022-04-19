import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class FirstTest {

    @Test
    public void checkMobileTopUPinPrivat24(){
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        WebDriver driver =new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);

        By inputPhoneNumber = By.xpath("//input[@data-qa-node='phone-number']");
        By inputAmount = By.xpath("//input[@data-qa-node='amount']");
        By buttonAmount100 = By.xpath("//button[contains(text(), '100')]");
        By inputCardNumber = By.xpath("//input[@data-qa-node='numberdebitSource']");
        By cardExpDate = By.xpath("//input[@data-qa-node='expiredebitSource']");
        By cardCvv = By.xpath("//input[@data-qa-node='cvvdebitSource']");
        By submitButton = By.xpath("//button[@data-qa-node='submit']");


        driver.get("https://next.privat24.ua/mobile/");

        driver.findElement(inputPhoneNumber).sendKeys("636595378");
        driver.findElement(inputAmount).sendKeys("56");
        driver.findElement(inputAmount).clear();
        driver.findElement(buttonAmount100).click();
        driver.findElement(inputCardNumber).sendKeys("4004159115449003");
        driver.findElement(cardExpDate).sendKeys("1022");
        driver.findElement(cardCvv).sendKeys("111");
        driver.findElement(submitButton).click();

        By actualPhoneNumber = By.xpath("//div[@data-qa-node='details']");
        By actualCardNumber = By.xpath("//td[@data-qa-node='card']");
        By actualAmount = By.xpath("//div[@data-qa-node='amount']");

        Assert.assertEquals("Поповнення телефону. На номер +380636595378",
                driver.findElement(actualPhoneNumber).getText());
        String actualString = driver.findElement(actualCardNumber).getText();
        Assert.assertTrue(actualString.contains("4004"));
        Assert.assertEquals("100 UAH",
                driver.findElement(actualAmount).getText());

        driver.close();
    }
}
