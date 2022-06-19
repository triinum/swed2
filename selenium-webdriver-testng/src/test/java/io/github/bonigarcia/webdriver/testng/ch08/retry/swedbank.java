 
package io.github.bonigarcia.webdriver.testng.ch08.retry;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import static org.assertj.core.api.Assertions.assertThat;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
 
public class swedbank {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = WebDriverManager.chromedriver().create();
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void accept_Cookies() {
        driver.get(
                "https://www.swedbank.ee/private/credit/loans/newSmall?language=EST");

                driver.manage().window().maximize();
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement cookies_accept_button = driver.findElement(By.xpath("//button[text()='Nõustun kõigi küpsistega']"));
                wait.until(ExpectedConditions.elementToBeClickable(cookies_accept_button)).click();

                String bodyText = driver.findElement(By.tagName("body")).getText();
                assertThat(bodyText).contains("lisaraha suuremateks ostudeks?");
    } 

    @Test
    public void loan_Amount() {
                JavascriptExecutor js = (JavascriptExecutor) driver;	
                js.executeScript("window.scrollBy(0,1000)", "");
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(By.id("small-loan-calculator"))).click();
                driver.findElement(By.xpath("//input[contains(@aria-label, 'Väärtus')]")).click();
                driver.findElement(By.xpath("//input[contains(@aria-label, 'Väärtus')]")).sendKeys(Keys.CONTROL + "a");
                driver.findElement(By.xpath("//input[contains(@aria-label, 'Väärtus')]")).sendKeys(Keys.DELETE);
                driver.findElement(By.xpath("//input[contains(@aria-label, 'Väärtus')]")).sendKeys("3000");
                String final_loan_Amount = driver.findElement(By.id("monthly-payment-result")).getText();   
                assertThat(final_loan_Amount).contains("50");
                js.executeScript("window.scrollBy(0,550)", "");
                //driver.findElement(By.xpath("//*[@id='max-check']")).click();
                //WebElement credit_Instructions = driver.findElement(By.linkText("Tutvuge"));
                //credit_Instructions.click();
                WebElement slider = driver.findElement(By.xpath("//*[@id='loan-period']/div[1]/span")); 
                Actions action = new Actions(driver);
                action.clickAndHold(slider);
                action.dragAndDropBy(slider, 30, 0).build();
                action.perform();
                assertThat(final_loan_Amount).contains("50");
        }
    


    @Test
    public void submit_Loan() {
                driver.findElement(By.xpath("//button[text()='Täidan taotluse']")).click();
                driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                assertThat(driver.getCurrentUrl()).contains("small_loan");
        }
}
    



