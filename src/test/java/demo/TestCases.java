package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.logging.Level;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;



public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation.
     * Follow `testCase01` `testCase02`... format or what is provided in
     * instructions
     */

    /*
     * Do not change the provided methods unless necessary, they will help in
     * automation and assessment
     */
    @BeforeTest
    public void startBrowser() {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();

    }

    @Test
    public void testCase01() throws InterruptedException {

        driver.get(
                "https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");
        Thread.sleep(2000);

        // --YOUR
        // NAME------------------------------------------------------------------------------------------------------------------------

        WebElement yourName = driver.findElement(By.xpath("(//input[@class='whsOnd zHQkBf'])[1]"));
        yourName.sendKeys("Crio Learner");
        Thread.sleep(2000);

        // --Why are you practicing
        // Automation?*------------------------------------------------------------------------------------------------------------------------

        long epoch = System.currentTimeMillis() / 1000; // Returns epoch in seconds.
        WebElement practicingAutomation = driver.findElement(By.xpath("//textarea[@class='KHxj8b tL9Q4c']"));
        practicingAutomation.sendKeys("I want to be the best QA Engineer!" + epoch);
        Thread.sleep(2000);

        // --How much experience do you have in Automation
        // Testing?*------------------------------------------------------------------------------------------------------------------------

        WebElement experienceInAutomation = driver.findElement(By.xpath("(//div[@class='AB7Lab Id5V1'])[1]"));
        experienceInAutomation.click();
        Thread.sleep(2000);

        // --Which of the following have you learned in Crio.Do for Automation
        // Testing?------------------------------------------------

        // WebElement learnedInAutomation = driver
        //         .findElement(By.xpath("(//span[@class='aDTYNe snByac n5vBHf OIC90c'])[1]"));
        // learnedInAutomation.click();

        for (int i = 1; i < 5; i++) {
        if (i == 1 || i == 3 || i == 4) {
        WebElement learnedInAutomation = driver
        .findElement(By.xpath("(//span[@class='aDTYNe snByac n5vBHf OIC90c'])["+i+"]"));
        learnedInAutomation.click();
        }
        }
        Thread.sleep(2000);

        // --How should you be
        // addressed?------------------------------------------------------------------------------------------------------------------------

        WebElement addressedToBe = driver.findElement(By.xpath("//div[@jsname='wQNmvb']"));
        addressedToBe.sendKeys("M"+ Keys.ENTER);
        // Select dropdown = new Select(addressedToBe);
        // dropdown.selectByVisibleText("Ms");
        // Thread.sleep(2000);

        // --What was the date 7 days
        // ago?------------------------------------------------------------------------------------------------------------------------

        LocalDate currentDate = LocalDate.now();
         System.out.println("Before Formatting: " + currentDate);
        DateTimeFormatter formattedCurrentDate = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate = currentDate.format(formattedCurrentDate);
         System.out.println("After Formatting: " + formattedDate);
        String[] dateSeperation = formattedDate.split("/");
        int dateToBeChanged = Integer.parseInt(dateSeperation[1]);
        dateToBeChanged = dateToBeChanged - 7;
         System.out.println("changed date is "+dateToBeChanged);
        String str = Integer.toString(dateToBeChanged);
        dateSeperation[1] = str;
        String result = String.join("/", dateSeperation);
        WebElement date7DaysAgo = driver.findElement(By.xpath("//input[@type='date']"));
        date7DaysAgo.sendKeys(result);
        Thread.sleep(2000);

        // --What is the time right
        // now?------------------------------------------------------------------------------------------------------------------------

        LocalTime currentTime = LocalTime.now();
        String[] seperatedTime = currentTime.toString().split(":");
        // System.out.println(seperatedTime[0]);
        // System.out.println(seperatedTime[1]);
        // System.out.println(currentTime);
        WebElement timeHour = driver.findElement(By.xpath("//input[@aria-label='Hour']"));
        timeHour.sendKeys(seperatedTime[0]);
        Thread.sleep(1000);
        WebElement timeMinute = driver.findElement(By.xpath("//input[@aria-label='Minute']"));
        timeMinute.sendKeys(seperatedTime[1]);
        Thread.sleep(1000);
        // WebElement amPm = driver.findElement(By.xpath("(//span[text()='AM'])[2]"));
        // amPm.click();
        // Thread.sleep(2000);

        // --Submit------------------------------------------------------------------------------------------------------------------------
        WebElement submitBtn = driver.findElement(By.xpath("//span[text()='Submit']"));
        submitBtn.click();
        //Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='pdLVYe LgNcQe']"))); 
        


        // --SuccessMesaage------------------------------------------------------------------------------------------------------------------------
      
         wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Thanks for your response, Automation Wizard!']"))); 
        WebElement successMesaage = driver
                .findElement(By.xpath("//div[text()='Thanks for your response, Automation Wizard!']"));
        System.out.println(successMesaage.getText());
        Thread.sleep(2000);

    }

    @AfterTest
    public void endTest() {
        driver.close();
        driver.quit();

    }
}