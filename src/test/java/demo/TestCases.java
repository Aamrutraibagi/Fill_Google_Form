package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;
    By formHeader = By.cssSelector(".F9yp7e");
    By nameField = By.xpath("(//input[@type=\"text\"])[1]");
    By questionField= By.xpath("//textarea[@class=\"KHxj8b tL9Q4c\"]");
    By experienceQuestion= By.xpath("//span[text()=\"How much experience do you have in Automation Testing?\"]");
    By ListOfExperienceOptions = By.xpath("//span[@class=\"aDTYNe snByac OvPDhc OIC90c\"]");
    By LanguageQuestion= By.xpath("//span[text()=\"Which of the following have you learned in Crio.Do for Automation Testing?\"]");
    By ListOfLanguageOptions = By.xpath("//span[@class=\"aDTYNe snByac n5vBHf OIC90c\"]");
    By AddressQuestion= By.xpath("//span[text()=\"How should you be addressed?\"]");
    By dropdown= By.xpath("//div[@class=\"e2CuFe eU809d\"]");
    By DateQuestion= By.xpath("//span[text()=\"What was the date 7 days ago?\"]");
    By dateField=By.xpath("//input[@type=\"date\"]");
    By TimeQuestion= By.xpath("//span[text()=\"What was the date 7 days ago?\"]");
    By HoursField= By.xpath("(//div[text()=\"Time\"]/parent::div/parent::div[@class=\"ocBCTb\"]//input[@class=\"whsOnd zHQkBf\"])[1]");
    By MinutesField= By.xpath("(//div[text()=\"Time\"]/parent::div/parent::div[@class=\"ocBCTb\"]//input[@class=\"whsOnd zHQkBf\"])[2]");
    By submitButton = By.xpath("//span[text()=\"Submit\"]");
    By responseMessage = By.xpath("//div[text()=\"Thanks for your response, Automation Wizard!\"]");
    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */
    String expected1= "Java";
    String expected2= "Selenium";
    String expected3= "Springboot";
    String expected4= "TestNG";

    @Test
    public void testCase01() throws InterruptedException {
        Wrappers.navigateToUrl(driver, "https://forms.gle/wjPkzeSEk1CM7KgGA");
        Wrappers.waitForPageLoad(driver);
        Wrappers.VerifyText(driver, formHeader, "Automate Google Forms");
        Wrappers.enterText(driver, nameField, "Crio Learner");
        Wrappers.enterText(driver, questionField, "I want to be the best QA Engineer! "+Wrappers.getCurrentTimestamp());
        Wrappers.waitForElement(driver, questionField, 10);
        Wrappers.waitForElement(driver, experienceQuestion, 10);
        // Select experience options
        List<WebElement> options = driver.findElements(ListOfExperienceOptions);
        if (options.size() > 0) {
            for (WebElement option : options) {
                System.out.println("Experience option: " + option.getText());
                if (option.getText().contains("3 - 5")) {
                    Wrappers.clickUsingJavaScript(driver, option);
                    System.out.println("Selected experience option: " + option.getText());
                    break;
                }
            }
        } else {
            System.out.println("Experience question not found.");
        }

        Wrappers.waitForElement(driver, LanguageQuestion, 10);

        ArrayList<String> expectedOptions= new ArrayList<>();
        expectedOptions.add(expected1);
        expectedOptions.add(expected2);
        expectedOptions.add(expected4);
        List<WebElement> Langaugeoptions = driver.findElements(ListOfLanguageOptions);
        if (Langaugeoptions.size() > 0) {
            for (int i = 0; i < Langaugeoptions.size(); i++) {
                System.out.println("Language option: " + Langaugeoptions.get(i).getText());
                if (expectedOptions.contains(Langaugeoptions.get(i).getText())) {
                    Wrappers.clickUsingJavaScript(driver, Langaugeoptions.get(i));
                    System.out.println("Selected Language option: " + Langaugeoptions.get(i).getText());
                }
            }
        } else {
            System.out.println("Language question not found.");
        }

        Wrappers.waitForElement(driver, AddressQuestion, 10);
        Wrappers.dropdownByVisibleText(driver, dropdown, "Mr");
        Wrappers.waitForElement(driver, DateQuestion, 10);
        Wrappers.selectDateBeforeDaysFromToday(driver, dateField, 7);
        Wrappers.waitForElement(driver, TimeQuestion, 10);
        Wrappers.enterText(driver, HoursField, "10");
        Wrappers.enterText(driver, MinutesField, "30");
        Wrappers.clickElement(driver, submitButton);
        Wrappers.waitForElement(driver, responseMessage, 10);
        Thread.sleep(5000); // Wait for the response message to appear
        Wrappers.VerifyText(driver, responseMessage, "Thanks for your response, Automation Wizard!");   


     }

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
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

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}