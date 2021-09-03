import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class EmailClient {
    private WebDriver driver;

    @Before
    public void login() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://gmail.com/");
        driver.findElement(By.xpath("//input[@id='identifierId']")).sendKeys("testbrommer@gmail.com");
        driver.findElement(By.xpath("//div[@id='identifierNext']")).click();
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("qazwsx!@#");
        driver.findElement(By.xpath("//div[@id='passwordNext']")).click();
        Thread.sleep(10);
    }

    @Test
    public void checkMessageCount() {
        int messageCount = driver.findElements(By.xpath("//*[contains(@class,'zA')]")).size();
        System.out.println("Total messages in the mail is " + messageCount);
    }

    @Test
    public void checkMessageBySubject(){
        String subject = "test";
        int messagesBySubject = driver.findElements(By.xpath("//*[@class='y6']//span[text()='" + subject + "']")).size();
        System.out.println("Total messages with subject " + subject + " is " + messagesBySubject) ;
    }

    @Test
    public void getEmailMessageBySubject() {
        String subject = "test";
        int messagesBySubject = driver.findElements(By.xpath("//*[@class='y6']//span[text()='" + subject + "']")).size();
        List<WebElement> message = driver.findElements(By.xpath("//*[@class='y6']//span[text()='" + subject + "']"));

            for(int i=0;i<message.size();i++){
                        if(message.get(i).getText().equals(subject)){
                        System.out.println("We have " + messagesBySubject + " mails " + " with subject " + " / " + subject + " / ");
                        break;
                    }
                        else{
                        System.out.println("No mails with subject " + " " + subject + " ");
                    }
        }
        }

    @After
    public void after () {
        driver.quit();
            }
        }


